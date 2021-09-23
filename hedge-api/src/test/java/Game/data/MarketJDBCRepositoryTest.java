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
    void findByGameId() {
        List<Market> marketList = repository.findByGameId(1);
        assertEquals(2, marketList.size());
    }

    @Test
    void findPortfolio() {
        List<Market> marketList = repository.findPortfolio(1, 1);
        assertEquals(2, marketList.size());
    }

    @Test
    void findByCompanyId() {
        List<Market> marketList = repository.findByCompanyId(1, 1);
        assertEquals(1, marketList.size());
    }

//    @Test
//    void addMarket() {
//
//        Market market = new Market(createCompany(), 25, 1, 3, 1, 10, false, false);
//        repository.addMarket(market);
//        assertEquals(3, repository.findPortfolio(1, 1).size());
//    }
//
//    @Test
//    void setBankrupt() {
//        Market market = new Market(createCompany(), 25, 1, 3, 1, 10, false, true);
//        repository.setBankrupt(market);
//        assertEquals(3, repository.findPortfolio(1, 1).size());
//        assertEquals(1, repository.findByCompanyId(1, 1).size());
//        List<Market> data = repository.findByCompanyId(1, 1);
//        assertTrue(data.get(0).getBankrupt());
//    }

    @Test
    void deleteMarket() {
        assertTrue(repository.deleteMarket(1));
    }

    private Company createCompany() {
        Company company = new Company();
        company.setCompanyId(3);

        return company;
    }
}