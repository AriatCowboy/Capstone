package Game.model;

public class Market {
    int companyId;
    int price;
    int yearNumber;
    int marketId;
    int gameId;
    int stockPurchased;
    Boolean isLongInvestment;

    public Market() {
    }

    public Market(int companyId, int price, int yearNumber, int marketId, int gameId, int stockPurchased, Boolean isLongInvestment) {
        this.companyId = companyId;
        this.price = price;
        this.yearNumber = yearNumber;
        this.marketId = marketId;
        this.gameId = gameId;
        this.stockPurchased = stockPurchased;
        this.isLongInvestment = isLongInvestment;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getYearNumber() {
        return yearNumber;
    }

    public void setYearNumber(int yearNumber) {
        this.yearNumber = yearNumber;
    }

    public int getMarketId() {
        return marketId;
    }

    public void setMarketId(int marketId) {
        this.marketId = marketId;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public int getStockPurchased() {
        return stockPurchased;
    }

    public void setStockPurchased(int stockPurchased) {
        this.stockPurchased = stockPurchased;
    }

    public Boolean getLongInvestment() {
        return isLongInvestment;
    }

    public void setLongInvestment(Boolean longInvestment) {
        this.isLongInvestment = longInvestment;
    }
}
