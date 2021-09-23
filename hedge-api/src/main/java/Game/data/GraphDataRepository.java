package Game.data;

import Game.model.GraphData;

import java.util.List;

public interface GraphDataRepository {
    List<GraphData> getGraphData(int gameId);
    boolean addGraphData(int graphDataId, int gameId, int companyId, int purchasedPrice, int amountPurchased, int year, int currentPrice);
    boolean updateCurrentPrice(int gameId, int price, int companyId);
}
