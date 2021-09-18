package Game.controller;

import Game.domain.LeaderBoardService;
import Game.domain.Result;
import Game.model.LeaderBoard;
import Game.utility.JwtConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

@RestController
@RequestMapping("/api/leaderboard")
public class LeaderBoardController {

    @Autowired
    JwtConverter jwtConverter;

    private final LeaderBoardService service;

    public LeaderBoardController(LeaderBoardService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<Object> findAll(@RequestHeader(value = "Authorization", required = false) String authorizationHeader) {
        Result<List<LeaderBoard>> result = service.findAll();
        if (result.isSuccess()) {
            return ResponseEntity.ok(result.getPayload());
        }
        return ErrorResponse.build(result);
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