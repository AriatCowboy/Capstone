package Game.model;

import java.util.List;

public class Game {
    int gameId;
    String userId;
    int lastYear;
    List<Market> markets;

    public Game(){}

    public Game(int gameId, String userId, int lastYear, List<Market> markets) {
        this.gameId = gameId;
        this.userId = userId;
        this.lastYear = lastYear;
        this.markets = markets;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getLastYear() {
        return lastYear;
    }

    public void setLastYear(int lastYear) {
        this.lastYear = lastYear;
    }

    public List<Market> getMarkets() {
        return markets;
    }

    public void setMarkets(List<Market> markets) {
        this.markets = markets;
    }
}
