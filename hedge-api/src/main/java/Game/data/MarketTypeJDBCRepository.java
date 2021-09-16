package Game.data;

import Game.data.mappers.MarketTypeMapper;
import Game.model.MarketType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class MarketTypeJDBCRepository implements MarketTypeRepository{

    private final JdbcTemplate jdbcTemplate;
    public MarketTypeJDBCRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public MarketType findRoll (int roll, int companyId){
        final String sql = "select * "
                + "from market_type "
                + "where roll = ? and company_id = ?;";

        return jdbcTemplate.query(sql, new MarketTypeMapper(), roll, companyId).stream()
                .findFirst().orElse(null);
    }
}
