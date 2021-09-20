package Game.data.mappers;

import Game.data.MarketRepository;
import Game.model.Market;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MarketMapper implements RowMapper<Market> {


    @Override
    public Market mapRow(ResultSet resultSet, int i) throws SQLException {
        Market market = new Market();
        market.setPrice(resultSet.getInt("price"));
        market.setYearNumber(resultSet.getInt("year_num"));
        market.setMarketId(resultSet.getInt("market_id"));
        market.setGameId(resultSet.getInt("game_id"));
        market.setStockPurchasedTotal(resultSet.getInt("stock_purchased_total"));
        market.setStockPurchasedYear(resultSet.getInt("stock_purchased_year"));
        market.setLongInvestment(resultSet.getBoolean("long"));
        market.setBankrupt(resultSet.getBoolean("is_bankrupt"));

        CompanyMapper companyMapper = new CompanyMapper();
        market.setCompany(companyMapper.mapRow(resultSet, i));
        return market;
    }
}
