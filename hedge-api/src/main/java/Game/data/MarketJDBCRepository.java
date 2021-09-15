package Game.data;

import Game.model.Market;

import java.util.List;

public class MarketJDBCRepository implements MarketRepository{
    public List<Market> findByGameId(int gameId){
        return null;
    }
    public List<Market> findPortfolio(int gameId, int yearNumber){
        return null;
    }
    public List<Market> findByCompanyId (int companyId, int gameId){
        return null;
    }
    public boolean addMarket (Market market){
        return false;
    }
    public boolean setBankrupt(boolean bankrupt, int gameId){
        return false;
    }
    public boolean deleteMarket (int gameId){
        return false;
    }
    public boolean addCompany (int companyId, int gameId){
        return false;
    }
}
