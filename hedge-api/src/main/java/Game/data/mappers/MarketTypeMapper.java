package Game.data.mappers;

import Game.model.MarketType;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MarketTypeMapper implements RowMapper<MarketType> {
    @Override
    public MarketType mapRow(ResultSet resultSet, int i) throws SQLException {
        MarketType marketType = new MarketType();
        marketType.setMarketId(resultSet.getInt("market_id"));
        marketType.setRoll(resultSet.getInt("roll"));
        marketType.setCompanyId(resultSet.getInt("company_id"));
        marketType.setBullModify(resultSet.getInt("bull_modify"));
        marketType.setBearModify(resultSet.getInt("bear_modify"));
        return marketType;
    }
}
