package Game.data;

import Game.model.Company;

import java.util.List;

public interface CompanyRepository {
    List<Company> findAll();
    Company findByID(int CompanyId);
    int findCompanyPriceById(int CompanyId);
}
