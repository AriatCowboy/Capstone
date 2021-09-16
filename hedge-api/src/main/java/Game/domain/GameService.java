package Game.domain;

import Game.data.CompanyRepository;
import Game.data.GameRepository;
import Game.model.Game;
import org.springframework.stereotype.Service;

@Service
public class GameService {

    private final GameRepository repository;

    public GameService(GameRepository repository) {
        this.repository = repository;
    }

    public Game findGameById (int gameId){
        if (gameId <= 0) {
            return null;
        }

        return repository.findGameById(gameId);
    }
//    @transactional for delete game

}
