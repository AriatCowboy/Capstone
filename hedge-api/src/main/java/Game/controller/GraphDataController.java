package Game.controller;


import Game.domain.GameService;
import Game.domain.GraphDataService;
import Game.model.AppUser;
import Game.model.GraphData;
import Game.utility.JwtConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:3000", "http://127.0.0.1:5501"})
@RequestMapping("/api/graphdata")
public class GraphDataController {

    @Autowired
    JwtConverter jwtConverter;

    private final GraphDataService service;
    private final GameService gameService;

    public GraphDataController(GraphDataService service, GameService gameService) {
        this.service = service;
        this.gameService = gameService;
    }

    @GetMapping()
    public ResponseEntity<List<GraphData>> getGraphData(@RequestHeader(value = "Authorization", required = false) String authorizationHeader) {
        AppUser user = null;

        if (authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.substring(7);
            user = jwtConverter.getUserFromToken(token);
        }

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        List<GraphData> graphData = service.getGraphData(gameService.findGameByUserID(user.getId()).getGameId());
        if (graphData == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(graphData);
    }
}
