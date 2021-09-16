package Game.data;

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
        assertEquals(2, repository.findByGameId(1).size());
    }

    @Test
    void findPortfolio() {
        assertEquals(2, repository.findPortfolio(1, 1).size());
    }

    @Test
    void findByCompanyId() {
        assertEquals(1, repository.findByCompanyId(1, 1).size());
    }

    @Test
    void addMarket() {
        Market market = new Market(3, 25, 1, 3, 1, 10, false, false);
        repository.addMarket(market);
        assertEquals(3, repository.findPortfolio(1, 1).size());
    }

    @Test
    void setBankrupt() {
        Market market = new Market(3, 25, 1, 3, 1, 10, false, true);
        repository.setBankrupt(market);
        assertEquals(3, repository.findPortfolio(1, 1).size());
        assertEquals(1, repository.findByCompanyId(1, 1).size());
        List<Market> data = repository.findByCompanyId(1, 1);
        assertTrue(data.get(0).getBankrupt());
    }

    @Test
    void deleteMarket() {
        assertTrue(repository.deleteMarket(1));
    }
}