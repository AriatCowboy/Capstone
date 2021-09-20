package Game.model;

public class Market {
    Company company;
    int price;
    int yearNumber;
    int marketId;
    int gameId;
    int stockPurchasedYear;
    int stockPurchasedTotal;
    Boolean isLongInvestment;
    Boolean isBankrupt;

    public Market() {
    }

    public Market(Company company, int price, int yearNumber, int marketId, int gameId, int stockPurchasedYear, int stockPurchasedTotal, Boolean isLongInvestment, Boolean isBankrupt) {
        this.company = company;
        this.price = price;
        this.yearNumber = yearNumber;
        this.marketId = marketId;
        this.gameId = gameId;
        this.stockPurchasedYear = stockPurchasedYear;
        this.stockPurchasedTotal = stockPurchasedTotal;
        this.isLongInvestment = isLongInvestment;
        this.isBankrupt = isBankrupt;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
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

    public int getStockPurchasedYear() {
        return stockPurchasedYear;
    }

    public void setStockPurchasedYear(int stockPurchasedYear) {
        this.stockPurchasedYear = stockPurchasedYear;
    }

    public int getStockPurchasedTotal() {
        return stockPurchasedTotal;
    }

    public void setStockPurchasedTotal(int stockPurchasedTotal) {
        this.stockPurchasedTotal = stockPurchasedTotal;
    }

    public Boolean getLongInvestment() {
        return isLongInvestment;
    }

    public void setLongInvestment(Boolean longInvestment) {
        isLongInvestment = longInvestment;
    }

    public Boolean getBankrupt() {
        return isBankrupt;
    }

    public void setBankrupt(Boolean bankrupt) {
        isBankrupt = bankrupt;
    }
}

