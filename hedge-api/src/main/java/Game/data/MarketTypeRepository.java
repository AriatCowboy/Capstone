package Game.data;

import Game.model.MarketType;

public interface MarketTypeRepository{
    MarketType findRoll (int roll, int companyId);
}
