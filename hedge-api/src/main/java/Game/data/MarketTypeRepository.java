package Game.data;

public interface MarketTypeRepository{
    int findRoll (int roll, int companyId, Boolean isBull);
}
