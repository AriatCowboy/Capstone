package Game.domain;

import Game.data.MarketTypeRepository;
import Game.model.MarketType;
import org.springframework.stereotype.Service;

@Service
public class MarketTypeService {
    private final MarketTypeRepository repository;
    public MarketTypeService(MarketTypeRepository repository) {
        this.repository = repository;
    }
    public Result<MarketType> findRoll(int roll, int companyId){
        Result<MarketType> result = new Result<>();
        if (roll > 0 && roll < 21){
            if (companyId > 0 && companyId < 27) {
                MarketType marketType = repository.findRoll(roll, companyId);
                result.setPayload(marketType);
            } else {
                result.addMessage("companyId must be a number 1 through 26.", ResultType.INVALID);
            }
        } else {
            result.addMessage("Roll must be a number 1 through 20.", ResultType.INVALID);
        }
        return result;
    }
}
