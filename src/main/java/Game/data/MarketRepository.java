package Game.data;

import Game.model.Market;

import java.util.List;

public interface MarketRepository {
    List<Market> findByGameId(int gameId);
    List<Market> findPortfolio(int gameId, int yearNumber);
    List<Market> findByCompanyId (int companyId, int gameId);
    boolean addMarket (Market market);
    boolean setBankrupt(boolean bankrupt, int gameId);
    boolean deleteMarket (int gameId);
    boolean addCompany (int companyId, int gameId);
}
