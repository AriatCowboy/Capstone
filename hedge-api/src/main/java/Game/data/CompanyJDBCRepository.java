package Game.data;

import Game.data.mappers.CompanyMapper;
import Game.model.Company;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CompanyJDBCRepository implements CompanyRepository{
    private final JdbcTemplate jdbcTemplate;
    public CompanyJDBCRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Company> findAll(){
        final String sql = "select * from company c join company_type ct on ct.company_type_id = c.company_type_id;";
        return jdbcTemplate.query(sql, new CompanyMapper());
    }

    public Company findById(int companyId){

        final String sql = "select company_id, name, stock_price, risk, stock_total, dividend, c.company_type_id, company_type_name from company c join company_type ct on ct.company_type_id = c.company_type_id where company_id = ?;";

        return jdbcTemplate.query(sql, new CompanyMapper(), companyId).stream()
                .findAny().orElse(null);
    }
}
