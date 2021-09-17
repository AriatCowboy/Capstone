package Game.controller;


import Game.domain.GameService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/gamestart")
public class GameStartController {

    private final GameService service;

    public GameStartController(GameService service) {
        this.service = service;
    }


}
