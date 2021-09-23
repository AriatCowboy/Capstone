package Game.data.mappers;

import Game.model.GraphData;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GraphDataMapper implements RowMapper<GraphData> {
    @Override
    public GraphData mapRow(ResultSet resultSet, int i) throws SQLException {
        GraphData graphData = new GraphData();
        graphData.setGraphDataId(resultSet.getInt("graph_data_id"));
        graphData.setGameId(resultSet.getInt("game_id"));
        graphData.setCompanyId(resultSet.getInt("company_id"));
        graphData.setPurchasedPrice(resultSet.getInt("purchased_price"));
        graphData.setAmountPurchased(resultSet.getInt("amount_purchased"));
        graphData.setYear(resultSet.getInt("year"));
        graphData.setCurrentPrice(resultSet.getInt("current_price"));
        return graphData;
    }
}