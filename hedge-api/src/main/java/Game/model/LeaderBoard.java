package Game.model;

public class LeaderBoard {
    String username;
    int score;

    public LeaderBoard(){}

    public LeaderBoard(String username, int score) {
        this.username = username;
        this.score = score;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }
}
