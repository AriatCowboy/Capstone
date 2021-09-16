package Game.domain;

import Game.data.GameRepository;
import Game.data.MarketRepository;
import Game.model.Game;
import org.springframework.stereotype.Service;

@Service
public class GameService {

    private final GameRepository repository;
    private final MarketRepository marketRepository;

    public GameService(GameRepository repository, MarketRepository marketRepository) {
        this.repository = repository;
        this.marketRepository = marketRepository;
    }

    public Game findGameById (int gameId){
        if (gameId <= 0) {
            return null;
        }
        return repository.findGameById(gameId);
    }
    public Game findGameByUserID (int userId){
        if (userId <= 0) {
            return null;
        }
        return repository.findGameByUserID(userId);
    }
    public Result<Game> addGame(Game game){
        Result<Game> result = validate(game);
        if (!result.isSuccess()){
            return result;
        }
        game = repository.addGame(game);
        result.setPayload(game);
        return result;
    }
    public Result<Boolean> updateGameState(Game game){
        Result<Game> result = validate(game);
        Result<Boolean> resultboolean = new Result<>();
        if (!result.isSuccess()){
            for (int i = 0; i < result.getMessages().size(); i++){
                resultboolean.addMessage(result.getMessages().get(i), result.getType());
            }
        }
        if (game.getGameId() <= 0){
            resultboolean.addMessage("GameId must be greater than 0.", ResultType.INVALID);
        }
        resultboolean.setPayload(repository.updateGameState(game));
        return resultboolean;
    }
    public boolean deleteGame (int gameId){
        boolean result = marketRepository.deletemarket(gameId);
        if (result){
            return repository.deleteGame(gameId);
        } else {
            return false;
        }
    }

    private Result<Game> validate(Game game){
        Result<Game> result = new Result<>();
        if(game == null){
            result.addMessage("game cannot be null", ResultType.INVALID);
            return result;
        }
        if (game.getUserId() <= 0){
            result.addMessage("User Id cannot be less than 0.", ResultType.INVALID);
            return result;
        }
        if (game.getLastYear() <= 0){
            result.addMessage("Turn/LastYear cannot be less than 0.", ResultType.INVALID);
            return result;
        }
        return result;
    }
}
