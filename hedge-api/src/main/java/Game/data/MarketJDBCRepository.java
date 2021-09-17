package Game.data;

import Game.data.mappers.MarketMapper;
import Game.model.Market;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Repository
public class MarketJDBCRepository implements MarketRepository{

    private final JdbcTemplate jdbcTemplate;

    public MarketJDBCRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Market> findByGameId(int gameId){
        final String sql = "select * from market m "
        + "inner join company c on m.company_id = c.company_id "
        + "inner join company_type ct on c.company_type_id = ct.company_type_id "
        + "where m.game_id = ?;";
        return new ArrayList<>(jdbcTemplate.query(sql, new MarketMapper(), gameId));
    }

    public List<Market> findPortfolio(int gameId, int yearNumber){
        final String sql = "select * from market m "
        + "inner join company c on m.company_id = c.company_id "
        + "inner join company_type ct on c.company_type_id = ct.company_type_id "
        + "where m.game_id = ? and m.year_num = ?;";
        return new ArrayList<>(jdbcTemplate.query(sql, new MarketMapper(), gameId, yearNumber));
    }

    public List<Market> findByCompanyId (int companyId, int gameId){
        final String sql = "select * from market m "
        + "inner join company c on m.company_id = c.company_id "
        + "inner join company_type ct on c.company_type_id = ct.company_type_id "
        + "where m.game_id = ? and m.company_id = ?;";
        return new ArrayList<>(jdbcTemplate.query(sql, new MarketMapper(), gameId, companyId));
    }

    public boolean addMarket (Market market){
        final String sql = "insert into market (company_id, price, year_num, game_id, stock_purchased, `long`, `is_bankrupt`) values (?,?,?,?,?,?,?);";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        int rowsAffected = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, market.getCompany().getCompanyId());
            ps.setInt(2, market.getPrice());
            ps.setInt(3, market.getYearNumber());
            ps.setInt(4, market.getGameId());
            ps.setInt(5, market.getStockPurchased());
            ps.setBoolean(6, market.getLongInvestment());
            ps.setBoolean(7, market.getBankrupt());
            return ps;
        }, keyHolder);
        return rowsAffected > 0;
    }

    public boolean setBankrupt(Market market){
        addMarket(market);
        final String sql = "Update market set is_bankrupt = true where game_id = ? and company_id = ?;";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        int rowsAffected = 0;
        rowsAffected = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, market.getCompany().getCompanyId());
            ps.setInt(2, market.getGameId());
            return ps;
        }, keyHolder) + rowsAffected;
        return rowsAffected > 0;
    }

    public boolean deleteMarket (int gameId){
        return jdbcTemplate.update("delete from market where game_id = ?", gameId) > 0;
    }

}
