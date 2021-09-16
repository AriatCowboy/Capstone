package Game.data;

import Game.data.mappers.GameMapper;
import Game.model.Game;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.Statement;

@Repository
public class GameJDBCRepository implements GameRepository{
    private final JdbcTemplate jdbcTemplate;

    public GameJDBCRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Game findGameByUserID (int userId){
        final String sql = "select game_id, user_id, year_number "
                + "from game "
                + "where user_id = ?;";

        return jdbcTemplate.query(sql, new GameMapper(), userId).stream()
                .findFirst().orElse(null);

    }

    @Override
    public Game addGame (Game game){
        final String sql = "insert into game (user_id, year_number) "
                + " values (?,?);";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        int rowsAffected = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, game.getUserId());
            ps.setInt(2, game.getLastYear());
            return ps;
        }, keyHolder);

        if (rowsAffected <= 0) {
            return null;
        }

        game.setGameId(keyHolder.getKey().intValue());
        return game;
    }

    @Override
    public Boolean updateGameState (Game game){
        final String sql = "update game set "
                + "year_number = ? "
                + "where user_id = ?;";

        return jdbcTemplate.update(sql,
                game.getLastYear(),
                game.getUserId()) > 0;
    }

    @Override
    public Boolean deleteGame (int gameId){
        return jdbcTemplate.update("delete from game where game_id = ?;", gameId) > 0;
    }
}
