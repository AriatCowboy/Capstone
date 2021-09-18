package Game.controller;


import Game.domain.GameService;
import Game.model.Game;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/gamestart")
public class GameStartController {

    private final GameService service;

    public GameStartController(GameService service) {
        this.service = service;
    }

    @GetMapping
    public Game getGame(@RequestBody String id){
        return service.findGameByUserID(id);
    }
}
