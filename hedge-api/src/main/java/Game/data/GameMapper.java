package Game.data.mappers;

import Game.model.Game;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GameMapper implements RowMapper<Game> {

    @Override
    public Game mapRow(ResultSet resultSet, int i) throws SQLException {
        Game game = new Game();
        game.setGameId(resultSet.getInt("game_id"));
        game.setLastYear(resultSet.getInt("year_number"));
        game.setUserId(resultSet.getInt("user_id"));
        System.out.println("hello");
        return game;
    }
}
