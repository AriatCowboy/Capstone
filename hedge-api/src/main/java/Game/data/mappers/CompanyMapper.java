package Game.data.mappers;

import Game.model.Company;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CompanyMapper implements RowMapper<Company> {

    @Override
    public Company mapRow(ResultSet resultSet, int i) throws SQLException {
        Company company = new Company();
        company.setCompanyId(resultSet.getInt("company_id"));
        company.setCompanyTypeName(resultSet.getString("company_type_name"));
        company.setName(resultSet.getString("name"));
        company.setDefaultStockPrice(resultSet.getInt("stock_price"));
        company.setRisk(resultSet.getString("risk"));
        company.setStockTotal(resultSet.getInt("stock_total"));
        company.setDividend(resultSet.getInt("dividend"));
        return company;
    }
}
