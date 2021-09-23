package Game.domain;

import Game.data.GraphDataRepository;
import Game.model.GraphData;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GraphDataService {
    private final GraphDataRepository repository;
    public GraphDataService(GraphDataRepository repository) {
        this.repository = repository;
    }
    public List<GraphData> getGraphData(int gameId) {
        return repository.getGraphData(gameId);
    }
    public boolean addGraphData(int graphDataId, int gameId, int companyId, int purchasedPrice, int amountPurchased, int year){
        return repository.addGraphData(graphDataId, gameId, companyId, purchasedPrice, amountPurchased, year, 0);
    }
    public boolean updateCurrentPrice(int gameId, int price, int companyId){
        return repository.updateCurrentPrice(gameId, price, companyId);

    }
}
