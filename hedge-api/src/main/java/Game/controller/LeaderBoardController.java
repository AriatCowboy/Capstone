package Game.controller;

import Game.domain.LeaderBoardService;
import Game.domain.Result;
import Game.model.AppUser;
import Game.model.Game;
import Game.model.LeaderBoard;
import Game.utility.JwtConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:3000", "http://127.0.0.1:5501"})
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

    @PostMapping("/addscore")
    public ResponseEntity<Object> addHighScore(@RequestHeader(value = "Authorization", required = false) String authorizationHeader, @RequestBody int score){
        AppUser user = null;

        if (authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.substring(7);
            user = jwtConverter.getUserFromToken(token);
        }

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        Result<Boolean> result = service.addHighScore(user.getUsername(), score);
        if (result.isSuccess()) {
            return new ResponseEntity<>(result.getPayload(), HttpStatus.CREATED);
        }
        return ErrorResponse.build(result);
    }
}