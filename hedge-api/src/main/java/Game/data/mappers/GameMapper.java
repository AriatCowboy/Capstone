package Game.data.mappers;

import Game.data.MarketJDBCRepository;
import Game.data.MarketRepository;
import Game.model.Game;
import Game.model.Market;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GameMapper implements RowMapper<Game> {

    @Override
    public Game mapRow(ResultSet resultSet, int i) throws SQLException {
        Game game = new Game();
        game.setGameId(resultSet.getInt("game_id"));
        game.setLastYear(resultSet.getInt("year_number"));
        game.setUserId(resultSet.getString("user_id"));
        return game;
    }
}
