package Game.domain;

import Game.data.GameRepository;
import Game.model.Game;
import Game.model.Market;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameService {

    private final GameRepository repository;
    private final MarketService marketService;

    public GameService(GameRepository repository, MarketService marketService) {
        this.repository = repository;
        this.marketService = marketService;
    }

    public Game findGameById (int gameId){
        if (gameId <= 0) {
            return null;
        }
        Game game = repository.findGameById(gameId);
        if (game != null) {
            game.setMarkets(marketService.findByGameId(game.getGameId()));
        }
        return game;
    }
    public Game findGameByUserID (String userId){
        if (userId == null) {
            return null;
        }
        Game game = repository.findGameByUserID(userId);
        if (game != null) {
            game.setMarkets(marketService.findByGameId(game.getGameId()));
        }
        return game;
    }

    public Game startGame(String userId) {
        Game game = findGameByUserID(userId);

        // If new game
        if (game == null) {
            game = new Game();
            game.setYear(1);
            game.setUserId(userId);
            Result<Game> gameResult = addGame(game);
            game = gameResult.getPayload();
            game.setMarkets(marketService.startNewGame(game));
        } else {
           game = nextRound(game);
        }

        // If previous game but no rounds ran
        if (game.getMarkets().size() == 0) {
            game.setMarkets(marketService.startNewGame(game));
        }

        return game;
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

    public Game nextRound(Game game) {
        updateGameState(game);

        for (Market m : game.getMarkets()) {
            if  (m.getMarketId() == 0) {
                marketService.addMarket(m);
            }
        }
        game.setYear(game.getYear() + 1);

        game.setMarkets(marketService.findByGameId(game.getGameId()));
        List<Market> marketList = marketService.generateThisYearMarket(game.getYear(), game.getGameId());
        List<Market> gameMarketList = game.getMarkets();
        gameMarketList.addAll(marketList);
        game.setMarkets(gameMarketList);



        return game;
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
    public boolean deleteGame (String userId){
        Game game = findGameByUserID(userId);

        if (game == null) {
            return false;
        }

        marketService.deleteMarket(game.getGameId());

        return repository.deleteGame(game.getGameId());
    }

    private Result<Game> validate(Game game){
        Result<Game> result = new Result<>();
        if(game == null){
            result.addMessage("game cannot be null", ResultType.INVALID);
            return result;
        }
        if (game.getUserId() == null){
            result.addMessage("User Id cannot be less than 0.", ResultType.INVALID);
            return result;
        }
        if (game.getYear() <= 0){
            result.addMessage("Turn/LastYear cannot be less than 0.", ResultType.INVALID);
            return result;
        }
        return result;
    }
}
