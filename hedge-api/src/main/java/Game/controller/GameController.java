package Game.controller;

import Game.domain.GameService;
import Game.domain.LeaderBoardService;
import Game.model.AppUser;
import Game.model.Game;
import Game.utility.JwtConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = {"http://localhost:3000", "http://127.0.0.1:5501"})
@RequestMapping("/api/game")
public class GameController {

    @Autowired
    JwtConverter jwtConverter;

    private final GameService service;

    public GameController(GameService service) {
        this.service = service;
    }

    @GetMapping()
    public ResponseEntity<?> startGame(@RequestHeader(value = "Authorization", required = false) String authorizationHeader) {
        AppUser user = null;

        if (authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.substring(7);
            user = jwtConverter.getUserFromToken(token);
        }

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        Game game = service.startGame(user.getId());

        return new ResponseEntity<>(game, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<?> nextRound(@RequestHeader(value = "Authorization", required = false) String authorizationHeader, @RequestBody Game game) {
        AppUser user = null;

        if (authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.substring(7);
            user = jwtConverter.getUserFromToken(token);
        }

        if (user == null || !user.getId().equals(game.getUserId())) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        game = service.nextRound(game);

        return new ResponseEntity<>(game, HttpStatus.OK);
    }

    @DeleteMapping()
    public ResponseEntity<Void> deleteGame(@RequestHeader(value = "Authorization", required = false) String authorizationHeader) {
        AppUser user = null;

        if (authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.substring(7);
            user = jwtConverter.getUserFromToken(token);
        }

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        service.deleteGame(user.getId());

        return ResponseEntity.noContent().build();
    }
}
