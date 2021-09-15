package Game.data;

import Game.model.LeaderBoard;

import java.util.List;

public interface LeaderBoardRepository {
    List<LeaderBoard> findAll();
    Boolean addHighScore(String username, int score);
}