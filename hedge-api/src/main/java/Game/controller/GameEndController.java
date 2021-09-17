package Game.controller;

import Game.domain.GameService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/gameend")
public class GameEndController {

    private final GameService service;

    public GameEndController(GameService service) {
        this.service = service;
    }
}
