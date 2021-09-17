package Game.controller;

import Game.domain.LeaderBoardService;
import Game.domain.Result;
import Game.model.LeaderBoard;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/leaderboard")
public class LeaderBoardController {

    private final LeaderBoardService service;

    public LeaderBoardController(LeaderBoardService service) {
        this.service = service;
    }

    @GetMapping
    public List<LeaderBoard> findAll() {
        return service.findAll();
    }

    @GetMapping
    public ResponseEntity<Object> addHighScore(String username, int score){
        Result<Boolean> result = service.addHighScore(username, score);
        if (result.isSuccess()) {
            return new ResponseEntity<>(result.getPayload(), HttpStatus.CREATED);
        }
        return ErrorResponse.build(result);
    }
}
