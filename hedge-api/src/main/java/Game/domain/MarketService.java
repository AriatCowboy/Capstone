package Game.domain;

import Game.data.MarketRepository;
import Game.model.Company;
import Game.model.Game;
import Game.model.Market;
import Game.model.MarketType;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class MarketService {

    private final MarketRepository repository;
    private final MarketTypeService marketTypeService;
    private final CompanyService companyService;
    private final GraphDataService graphDataService;

    public MarketService(MarketRepository repository, MarketTypeService marketTypeService, CompanyService companyService, GraphDataService graphDataService) {
        this.repository = repository;
        this.marketTypeService = marketTypeService;
        this.companyService = companyService;
        this.graphDataService = graphDataService;
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

    public List<Market> startNewGame (Game game) {
        List<Market> marketList = new ArrayList<>();

        while (marketList.size() != 10) {
            marketList = addCompanyToMarket(marketList, game.getYear(), game.getGameId());
        }

        return marketList;
    }

    public List<Market> generateThisYearMarket(int currentYear, int gameId) {
        if (gameId <= 0 || currentYear <= 0) {
            return new ArrayList<>();
        }

        List<Market> marketListTemp = findPortfolio(gameId, currentYear - 1);
        List<Market> marketList = new ArrayList<>();
        int newlyBankruptCompanies = 0;

        for (Market m : marketListTemp) {
            m.setYearNumber(m.getYearNumber() + 1);

            int roll = generateRandom(1, 20);

            Result<MarketType> marketType = marketTypeService.findRoll(roll, m.getCompany().getCompanyId());
            int modifier = 0;
            if (isBullMarket()) {
                modifier = marketType.getPayload().getBullModify();
            } else {
                modifier = marketType.getPayload().getBearModify();
            }
            m.setLastYearPrice(m.getPrice());
            m.setPrice(m.getPrice() + modifier);
            m.setStockPurchasedYear(0);
            m.setMarketId(0);
            List<Market> markets = repository.findByCompanyId(m.getCompany().getCompanyId(), m.getGameId());
            for (Market x : markets){
                if (m.getYearNumber() - 1 == x.getYearNumber()){
                    graphDataService.updateCurrentPrice(m.getGameId(), m.getPrice(), m.getCompany().getCompanyId());
                }
            }
            if (m.getPrice() <= 0 && !m.getBankrupt()) {
//                m.setPrice(0);
//                m.setBankrupt(true);
//                setBankrupt(m);]
                newlyBankruptCompanies++;
            } else {
                marketList.add(m);
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

        market.setStockPurchasedTotal(market.getStockPurchasedTotal() + market.getStockPurchasedYear());

        if(!repository.addMarket(market)) {
            result.addMessage("There was in internal error.", ResultType.INVALID);
            return result;
        }
        if (market.getStockPurchasedYear() > 0){
            int gameId = market.getGameId();
            int amountPurchased = market.getStockPurchasedYear();
            int price = market.getPrice();
            int year = market.getYearNumber();
            int companyId = market.getCompany().getCompanyId();
            graphDataService.addGraphData(0, gameId, companyId, price, amountPurchased, year);
        }

        result.setPayload(market);
        return result;
    }

    public boolean setBankrupt(Market market) {
        if (market.getCompany().getCompanyId() <= 0 || market.getCompany().getCompanyId() > 26 || market.getGameId() < 0) {
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
            instances = 0;
            rand = generateRandom(1, 26);
            for (Market m : marketList) {
                if (m.getCompany().getCompanyId() == rand) {
                    instances++;
                }
            }
            if (instances == 0) {
                Company company = companyService.findById(rand).getPayload();
                Market newMarket = new Market(company, 25, yearNum, 0, gameId, 0, 0, false, false);
                newMarket.setLastYearPrice(newMarket.getPrice());
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

        if (market.getCompany().getCompanyId() <= 0 || market.getCompany().getCompanyId() > 26) {
            result.addMessage("This company does not exist.", ResultType.NOT_FOUND);
            return result;
        }

        if (market.getYearNumber() < 0) {
            result.addMessage("There is a yearNumber error.", ResultType.INVALID);
            return result;
        }

        if (market.getStockPurchasedTotal() < 0) {
            result.addMessage("Cannot have negative stocks.", ResultType.INVALID);
            return result;
        }

        if (market.getPrice() < 0) {
            result.addMessage("Cannot have negative price.", ResultType.INVALID);
            return result;
        }

//        if (gameService.findGameById(market.getGameId()) == null) {
//            result.addMessage("There is not a game with this ID.", ResultType.NOT_FOUND);
//            return result;
//        }

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
