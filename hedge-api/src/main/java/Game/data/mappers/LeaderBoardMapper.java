package Game.data.mappers;

import Game.model.LeaderBoard;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LeaderBoardMapper implements RowMapper<LeaderBoard> {

    @Override
    public LeaderBoard mapRow(ResultSet resultSet, int i) throws SQLException {
        LeaderBoard leaderboard = new LeaderBoard();
        leaderboard.setUsername(resultSet.getString("user_name"));
        leaderboard.setScore(resultSet.getInt("score"));
        return leaderboard;
    }
}