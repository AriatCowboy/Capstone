package Game.data;
import Game.data.mappers.LeaderBoardMapper;
import Game.model.LeaderBoard;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class LeaderBoardJDBCRepository implements LeaderBoardRepository{
    private final JdbcTemplate jdbcTemplate;

    public LeaderBoardJDBCRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    @Override
    public List<LeaderBoard> findAll(){
        final String sql = "select * from leaderboard ORDER BY score DESC limit 10;";
        return jdbcTemplate.query(sql, new LeaderBoardMapper());
    }
    @Override
    public Boolean addHighScore(String username, int score){

        final String sql = "insert into leaderboard (user_name, score) values (?,?);";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        int rowsAffected = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, username);
            ps.setInt(2, score);
            return ps;
        }, keyHolder);
        return rowsAffected > 0;
    }
}