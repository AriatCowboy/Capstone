package Game.data.mappers;

import Game.model.Company;
import Game.model.Market;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MarketMapper implements RowMapper<Market> {
    @Override
    public Market mapRow(ResultSet resultSet, int i) throws SQLException {
        Market market = new Market();
        market.setCompanyId(resultSet.getInt("company_id"));
        market.setPrice(resultSet.getInt("price"));
        market.setYearNumber(resultSet.getInt("year_num"));
        market.setMarketId(resultSet.getInt("market_id"));
        market.setGameId(resultSet.getInt("game_id"));
        market.setStockPurchased(resultSet.getInt("stock_purchased"));
        market.setLongInvestment(resultSet.getBoolean("long"));
        return market;
    }
}
