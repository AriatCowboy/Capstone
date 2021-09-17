package Game.controller;

import Game.domain.Result;
import Game.domain.ResultType;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.format.DateTimeParseException;

@ControllerAdvice
public class GlobalExceptionHandler {

//    @ExceptionHandler(HttpMessageNotReadableException.class)
//    public ResponseEntity<Void> handleException(HttpMessageNotReadableException ex) {
    // log this error...
//        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//    }
//
//    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
//    public ResponseEntity<Void> handleException(HttpMediaTypeNotSupportedException ex) {
    // log this error...
//        return new ResponseEntity<>(HttpStatus.UNSUPPORTED_MEDIA_TYPE);
//    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponse> handleException(DataIntegrityViolationException ex) {
        return ErrorResponse.build("Something went wrong in the database. " +
                "Please ensure that any referenced records exist. Your request failed. :(");

//        return new ResponseEntity<>(
//                new ErrorResponse("Something went wrong in the database. " +
//                        "Please ensure that any referenced records exist. Your request failed. :("),
//                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<?> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        // This exception handler allows a 400 Bad Request
        // to be returned to the client with a more meaningful message
        // than what is returned by default by Spring MVC.
        if (ex.contains(DateTimeParseException.class)) {
            Result<Object> result = new Result<>();
            result.addMessage("Unable to parse a provided string value to a date/time value; date/time string values must be provided in the format yyyy-mm-dd", ResultType.INVALID);
            return ErrorResponse.build(result);
        }

        throw ex;
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception ex) throws Exception {
        // log this error...

        if (ex instanceof HttpMessageNotReadableException || ex instanceof HttpMediaTypeNotSupportedException) {
            throw ex; // return
        }

//        // Ignore any exception type that is decorated with the ResponseStatus annotation...
//        if (AnnotationUtils.findAnnotation(ex.getClass(), ResponseStatus.class) != null) {
//            throw ex;
//        }

        return ErrorResponse.build("Something went wrong on our end. Your request failed. :(");

//        return new ResponseEntity<>(
//                new ErrorResponse("Something went wrong on our end. Your request failed. :("),
//                HttpStatus.INTERNAL_SERVER_ERROR);
    }
}