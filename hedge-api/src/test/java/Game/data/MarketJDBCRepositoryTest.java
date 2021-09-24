package Game.data;

import Game.model.Company;
import Game.model.Market;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.function.BooleanSupplier;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class MarketJDBCRepositoryTest {

    @Autowired
    MarketJDBCRepository repository;

    @Autowired
    KnownGoodState knownGoodState;

    @BeforeEach
    void setup() {
        knownGoodState.set();
    }

    @Test
    void shouldFindByGameId() {
        List<Market> marketList = repository.findByGameId(1);
        assertEquals(2, marketList.size());
    }

    @Test
    void shouldNotFindByGameId() {
        List<Market> marketList = repository.findByGameId(5);
        assertEquals(0, marketList.size());
    }

    @Test
    void shouldFindPortfolio() {
        List<Market> marketList = repository.findPortfolio(1, 1);
        assertEquals(2, marketList.size());
    }

    @Test
    void shouldNotFindPortfolio() {
        List<Market> marketList = repository.findPortfolio(4, 1);
        assertEquals(0, marketList.size());
    }

    @Test
    void shouldFindByCompanyId() {
        List<Market> marketList = repository.findByCompanyId(1, 1);
        assertEquals(1, marketList.size());
    }

    @Test
    void shouldNotFindByCompanyId() {
        List<Market> marketList = repository.findByCompanyId(21, 1);
        assertEquals(0, marketList.size());
    }

    @Test
    void shouldAddMarket() {
        Market market = new Market(createCompany(), 25, 1, 3, 1, 1, 1, false, false);
        assertTrue(repository.addMarket(market));
        assertEquals(3, repository.findPortfolio(1, 1).size());
    }

    @Test
    void shouldSetBankrupt() {
        Company company = createCompany();
        company.setCompanyId(1);
        Market market = new Market(company, 25, 1, 1, 1, 0, 0, false, true);
        assertTrue(repository.setBankrupt(market));
        List<Market> data = repository.findByCompanyId(1, 1);
        assertTrue(data.get(0).getBankrupt());
    }

    @Test
    void shouldNotSetBankrupt() {
        Market market = new Market(createCompany(), 25, 1, 1, 1, 0, 0, false, true);
        assertFalse(repository.setBankrupt(market));
    }

    @Test
    void shouldDeleteMarket() {
        assertTrue(repository.deleteMarket(1));
    }

    @Test
    void shouldNotDeleteMarket() {
        assertFalse(repository.deleteMarket(5));
    }

    private Company createCompany() {
        Company company = new Company();
        company.setCompanyId(3);

        return company;
    }
}