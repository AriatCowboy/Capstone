package Game.model;

public class GraphData {
    int graphDataId;
    int gameId;
    int companyId;
    int purchasedPrice;
    int amountPurchased;
    int year;
    int currentPrice;

    public GraphData(){

    }
    public GraphData(int graphDataId, int gameId, int companyId, int purchasedPrice, int amountPurchased, int year, int currentPrice) {
        this.graphDataId = graphDataId;
        this.gameId = gameId;
        this.companyId = companyId;
        this.purchasedPrice = purchasedPrice;
        this.amountPurchased = amountPurchased;
        this.year = year;
        this.currentPrice = currentPrice;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public int getPurchasedPrice() {
        return purchasedPrice;
    }

    public void setPurchasedPrice(int purchasedPrice) {
        this.purchasedPrice = purchasedPrice;
    }

    public int getAmountPurchased() {
        return amountPurchased;
    }

    public void setAmountPurchased(int amountPurchased) {
        this.amountPurchased = amountPurchased;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(int currentPrice) {
        this.currentPrice = currentPrice;
    }

    public int getGraphDataId() {
        return graphDataId;
    }

    public void setGraphDataId(int graphDataId) {
        this.graphDataId = graphDataId;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }
}
