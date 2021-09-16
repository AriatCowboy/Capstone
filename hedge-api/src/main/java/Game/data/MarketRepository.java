package Game.data;

import Game.model.Market;

import java.util.List;

public interface MarketRepository {
    List<Market> findByGameId(int gameId);
    List<Market> findPortfolio(int gameId, int yearNumber);
    List<Market> findByCompanyId (int companyId, int gameId);
    boolean addMarket (Market market);
    boolean setBankrupt(Market market);
    boolean deleteMarket (int gameId);
//    boolean addCompany (Market market);
}
