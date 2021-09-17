package Game.domain;

import Game.data.MarketRepository;
import Game.model.Market;
import Game.model.MarketType;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class MarketService {

    private final MarketRepository repository;
    private final GameService gameService;
    private final MarketTypeService marketTypeService;

    public MarketService(MarketRepository repository, GameService gameService, MarketTypeService marketTypeService) {
        this.repository = repository;
        this.gameService = gameService;
        this.marketTypeService = marketTypeService;
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

    public List<Market> generateThisYearMarket(int currentYear, int gameId) {
        if (gameId <= 0 || currentYear <= 0) {
            return new ArrayList<>();
        }

        List<Market> marketList = repository.findPortfolio(currentYear - 1, gameId);

        int newlyBankruptCompanies = 0;

        for (Market m : marketList) {
            m.setYearNumber(m.getYearNumber() + 1);
            m.setStockPurchased(0);

            int roll = generateRandom(1, 20);

            Result<MarketType> marketType = marketTypeService.findRoll(roll, m.getCompanyId());
            int modifier = 0;
            if (isBullMarket()) {
                modifier = marketType.getPayload().getBullModify();
            } else {
                modifier = marketType.getPayload().getBearModify();
            }

            m.setPrice(m.getPrice() + modifier);

            if (m.getPrice() <= 0 && !m.getBankrupt()) {
                m.setPrice(0);
                m.setBankrupt(true);
                setBankrupt(m);
                newlyBankruptCompanies++;
            }
        }

        for(int i = 0; i < newlyBankruptCompanies; i++) {
            marketList = addCompanyToMarket(marketList, currentYear, gameId);
        }

        return marketList;
    }

    public Result<Market> addMarket (Market market) {

        Result<Market> result = new Result<>();

        if (market.getMarketId() != 0) {
            result.addMessage("marketId cannot be set for `add` operation", ResultType.INVALID);
            return result;
        }

        result = validateMarket(market);
        if (!result.isSuccess()) {
            return result;
        }

        if(!repository.addMarket(market)) {
            result.addMessage("There was in internal error.", ResultType.INVALID);
            return result;
        }

        result.setPayload(market);
        return result;
    }

    public boolean setBankrupt(Market market) {
        if (market.getCompanyId() <= 0 || market.getCompanyId() > 26 || market.getGameId() < 0) {
            return false;
        }

        return repository.setBankrupt(market);
    }

    public boolean deleteMarket(int gameId) {
        if (gameId <= 0) {
            return false;
        }

        return repository.deleteMarket(gameId);
    }

    private List<Market> addCompanyToMarket(List<Market> marketList, int yearNum, int gameId) {
        int rand = 0;

        boolean keepGoing = true;
        int instances = 0;
        do {
            rand = generateRandom(1, 26);
            for (Market m : marketList) {
                if (m.getCompanyId() == rand) {
                    instances++;
                }
            }
            if (instances == 0) {
                Market newMarket = new Market(rand, 25, yearNum, 0, gameId, 0, false, false);
                marketList.add(newMarket);
                keepGoing = false;
            }
        } while (keepGoing);

        return marketList;
    }

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

        if (market.getPrice() < 0) {
            result.addMessage("Cannot have negative price.", ResultType.INVALID);
            return result;
        }

        if (gameService.findGameById(market.getGameId()) == null) {
            result.addMessage("There is not a game with this ID.", ResultType.NOT_FOUND);
            return result;
        }

        return result;
    }

    private int generateRandom(int min, int max) {
        Random rand = new Random();

        return rand.nextInt((max - min) + 1) + min;
    }

    private boolean isBullMarket() {
        return (Math.random() < 0.5);
    }

}
