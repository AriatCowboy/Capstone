package Game.domain;

import Game.data.CompanyRepository;
import Game.model.Company;
import Game.model.LeaderBoard;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class CompanyServiceTest {

    @Autowired
    CompanyService service;

    @MockBean
    CompanyRepository repository;

    @Test
    void findAll() {
        when(repository.findAll()).thenReturn(createCompanyList());
        List<Company> companies = service.findAll();
        assertEquals(1, companies.size());
    }

    @Test
    void shouldFindById() {
        when(repository.findById(1)).thenReturn(createCompany());
        Result<Company> company = service.findById(1);
        assertTrue(company.isSuccess());
    }

    @Test
    void shouldNotFindById() {
        Result<Company> company = service.findById(-1);
        assertFalse(company.isSuccess());
        company = service.findById(27);
        assertFalse(company.isSuccess());
    }

    List<Company> createCompanyList() {
        List<Company> companyList = new ArrayList<>();
        Company company = new Company();
        company.setCompanyId(1);
        companyList.add(company);
        return companyList;
    }

    Company createCompany() {
        Company company = new Company();
        company.setCompanyId(1);
        company.setName("Test");
        return company;
    }
}