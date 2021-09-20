package Game.data;

import Game.data.mappers.GameMapper;
import Game.model.Game;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;

@Repository
public class GameJDBCRepository implements GameRepository{
    private final JdbcTemplate jdbcTemplate;

    public GameJDBCRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Game findGameById (int gameId){
        final String sql = "select g.game_id, g.user_id, g.year_number, g.score "
                + "from game g "
                + "where g.game_id = ?;";

        return jdbcTemplate.query(sql, new GameMapper(), gameId).stream()
                .findFirst().orElse(null);

    }

    @Override
    public Game findGameByUserID (String userId){
        final String sql = "select game_id, user_id, year_number, score "
                + "from game "
                + "where user_id = ?;";

        return jdbcTemplate.query(sql, new GameMapper(), userId).stream()
                .findFirst().orElse(null);

    }

    @Override
    public Game addGame (Game game){
        final String sql = "insert into game (user_id, year_number, score) "
                + " values (?,?,?);";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        int rowsAffected = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, game.getUserId());
            ps.setInt(2, game.getYear());
            ps.setInt(3, game.getScore());
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
                + "year_number = ?, "
                + "score = ?, "
                + "where user_id = ?;";

        return jdbcTemplate.update(sql,
                game.getYear(),
                game.getScore(),
                game.getUserId()) > 0;
    }

    @Override
    public Boolean deleteGame (int gameId){
        return jdbcTemplate.update("delete from game where game_id = ?;", gameId) > 0;
    }
}
