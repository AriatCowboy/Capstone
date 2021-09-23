package Game.data;

import Game.data.mappers.GraphDataMapper;
import Game.model.GraphData;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Repository
public class GraphDataJDBCRepository implements GraphDataRepository{
    private final JdbcTemplate jdbcTemplate;

    public GraphDataJDBCRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<GraphData> getGraphData(int gameId){
        final String sql = "select * from graph_data where game_id = ?;";
        return new ArrayList<>(jdbcTemplate.query(sql, new GraphDataMapper(), gameId));
    }
    @Override
    public boolean updateCurrentPrice(int gameId, int price, int companyId){
        final String sql = "update graph_data set current_price = ? where game_id = ? and company_id = ? and current_price = 0;";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        int rowsAffected = 0;
        rowsAffected = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, price);
            ps.setInt(2, gameId);
            ps.setInt(3, companyId);
            return ps;
        }, keyHolder) + rowsAffected;
        return rowsAffected > 0;
    }
    @Override
    public boolean addGraphData(int graphDataId, int gameId, int companyId, int purchasedPrice, int amountPurchased, int year, int currentPrice){
        final String sql = "insert into graph_data (graph_data_id, game_id, company_id, purchased_price, amount_purchased, year, current_Price) values(?,?,?,?,?,?,?);";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        int rowsAffected = 0;
        rowsAffected = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, graphDataId);
            ps.setInt(2, gameId);
            ps.setInt(3, companyId);
            ps.setInt(4, purchasedPrice);
            ps.setInt(5, amountPurchased);
            ps.setInt(6, year);
            ps.setInt(7, currentPrice);
            return ps;
        }, keyHolder) + rowsAffected;
        return rowsAffected > 0;
    }
}
