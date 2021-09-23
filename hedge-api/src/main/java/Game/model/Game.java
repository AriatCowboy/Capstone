package Game.model;

import java.util.List;

public class Game {
    int gameId;
    String userId;
    int year;
    int score;
    List<Market> markets;

    public Game() {
    }

    public Game(int gameId, String userId, int year, int score, List<Market> markets) {
        this.gameId = gameId;
        this.userId = userId;
        this.year = year;
        this.score = score;
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

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public List<Market> getMarkets() {
        return markets;
    }

    public void setMarkets(List<Market> markets) {
        this.markets = markets;
    }
}

