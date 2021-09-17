package Game.model;

public class Game {
    int gameId;
    int userId;
    int lastYear;
    int currentMarketId;

    public Game(){}

    public Game(int gameId, int userId, int lastYear, int currentMarketId) {
        this.gameId = gameId;
        this.userId = userId;
        this.lastYear = lastYear;
        this.currentMarketId = currentMarketId;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getLastYear() {
        return lastYear;
    }

    public void setLastYear(int lastYear) {
        this.lastYear = lastYear;
    }

    public int getCurrentMarketId() {
        return currentMarketId;
    }

    public void setCurrentMarketId(int currentMarketId) {
        this.currentMarketId = currentMarketId;
    }
}
