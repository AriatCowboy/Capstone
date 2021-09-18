package Game.controller;

import Game.domain.LeaderBoardService;
import Game.domain.Result;
import Game.model.AppUser;
import Game.model.LeaderBoard;
import Game.utility.JwtConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/leaderboard")
public class LeaderBoardController {

    @Autowired
    JwtConverter jwtConverter;

    private final LeaderBoardService service;

    public LeaderBoardController(LeaderBoardService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<?> findAll(@RequestHeader(value = "Authorization", required = false) String authorizationHeader) {

        AppUser user = null;

        if (authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.substring(7);
            user = jwtConverter.getUserFromToken(token);
        }

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        Result<List<LeaderBoard>> result = service.findAll();

        if (result.isSuccess()) {
            return new ResponseEntity<>(result.getPayload(), HttpStatus.OK);
        }
        return ErrorResponse.build(result);
    }



    @GetMapping("/")
    public ResponseEntity<?> getMapping(@RequestHeader(value = "Authorization", required = false) String authorizationHeader) {
        AppUser user = null;

        if (authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.substring(7);
            user = jwtConverter.getUserFromToken(token);
        }

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        // TODO: use user logic

        return new ResponseEntity<>(user, HttpStatus.OK);

    }







    @GetMapping("/addscore")
    public ResponseEntity<Object> addHighScore(String username, int score){
        Result<Boolean> result = service.addHighScore(username, score);
        if (result.isSuccess()) {
            return new ResponseEntity<>(result.getPayload(), HttpStatus.CREATED);
        }
        return ErrorResponse.build(result);
    }
}