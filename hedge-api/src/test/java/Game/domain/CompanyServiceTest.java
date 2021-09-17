package Game.domain;

import Game.data.CompanyRepository;
import Game.model.Company;
import Game.model.LeaderBoard;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class CompanyServiceTest {

    @Autowired
    CompanyService service;

    @Autowired
    CompanyRepository marketTypeRepository;

    @Test
    void findAll() {
        List<Company> companies = service.findAll();
        assertEquals(26, companies.size());
    }

    @Test
    void findById() {
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
}