package Game.model;

public class MarketType {
    int marketId;
    int roll;
    int bullModify;
    int bearModify;
    int companyId;

    public MarketType() {
    }

    public MarketType(int marketId, int roll, int bullModify, int bearModify, int companyId) {
        this.marketId = marketId;
        this.roll = roll;
        this.bullModify = bullModify;
        this.bearModify = bearModify;
        this.companyId = companyId;
    }

    public int getRoll() {
        return roll;
    }

    public void setRoll(int roll) {
        this.roll = roll;
    }

    public int getBullModify() {
        return bullModify;
    }

    public void setBullModify(int bullModify) {
        this.bullModify = bullModify;
    }

    public int getBearModify() {
        return bearModify;
    }

    public void setBearModify(int bearModify) {
        this.bearModify = bearModify;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public int getMarketId() {
        return marketId;
    }
    public void setMarketId(int marketId) {
        this.marketId = marketId;
    }
}
