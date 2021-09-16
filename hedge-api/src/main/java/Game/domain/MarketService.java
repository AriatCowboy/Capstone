package Game.domain;

import Game.data.MarketRepository;
import Game.model.Market;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MarketService {

    private final MarketRepository repository;
    private final GameService gameService;

    public MarketService(MarketRepository repository, GameService gameService) {
        this.repository = repository;
        this.gameService = gameService;
    }

    public List<Market> findByGameId(int gameId) {
        if (gameId <= 0) {
            return new ArrayList<>();
        }

        return repository.findByGameId(gameId);
    }

    public List<Market> findPortfolio(int gameId, int yearNumber) {
        if (gameId <= 0 || yearNumber < 0) {
            return new ArrayList<>();
        }

        return repository.findPortfolio(gameId, yearNumber);
    }

    public List<Market> findByCompanyId (int companyId, int gameId){
        if (gameId <= 0 || companyId <= 0 || companyId >26) {
            return new ArrayList<>();
        }

        return repository.findByCompanyId(companyId, gameId);
    }

//    public Result<Market> addMarket (Market market) {
//
//    }

    private Result<Market> validateMarket(Market market) {
        Result<Market> result = new Result<>();

        if (market == null) {
            result.addMessage("Market cannot be null.", ResultType.INVALID);
            return result;
        }

        if (market.getCompanyId() <= 0 || market.getCompanyId() > 26) {
            result.addMessage("This company does not exist.", ResultType.NOT_FOUND);
            return result;
        }

        if (market.getYearNumber() < 0) {
            result.addMessage("There is a yearNumber error.", ResultType.INVALID);
            return result;
        }

        if (market.getStockPurchased() < 0) {
            result.addMessage("Cannot buy negative stocks.", ResultType.INVALID);
            return result;
        }

        if (gameService.findGameById(market.getGameId()) == null) {
            result.addMessage("There is not a game with this ID.", ResultType.NOT_FOUND);
            return result;
        }

        return result;
    }

}
