package Game.data;

import Game.model.Company;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class CompanyJDBCRepositoryTest {

    @Autowired
    CompanyJDBCRepository repository;

    @Autowired
    KnownGoodState knownGoodState;

    @BeforeEach
    void setup() {
        knownGoodState.set();
    }

    @Test
    void shouldFindAll() {
        List<Company> companies = repository.findAll();
        assertEquals(26, companies.size());
    }

    @Test
    void shouldFindByID() {
        Company company = repository.findById(1);
        assertEquals("Academiic", company.getName());
        assertEquals("High", company.getRisk());
        assertEquals("Cultural", company.getCompanyTypeName());

        company = repository.findById(28);
        assertNull(company);
    }

    @Test
    void shouldNotFindById() {
        Company company = repository.findById(28);
        assertNull(company);
    }
}