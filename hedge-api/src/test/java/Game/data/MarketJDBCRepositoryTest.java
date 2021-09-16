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
    void shouldFindByGameId() {
        assertEquals(2, repository.findByGameId(1).size());
    }

    @Test
    void shouldNotFindByGameId() {
        assertEquals(0, repository.findByGameId(6).size());
    }

    @Test
    void shouldFindPortfolio() {
        assertEquals(2, repository.findPortfolio(1, 1).size());
    }

    @Test
    void shouldNotFindPortfolio() {
        assertEquals(0, repository.findPortfolio(1, 7).size());
    }

    @Test
    void shouldFindByCompanyId() {
        assertEquals(1, repository.findByCompanyId(1, 1).size());
    }

    @Test
    void shouldNotFindByCompanyId() {
        assertEquals(0, repository.findByCompanyId(50, 1).size());
    }

    @Test
    void shouldAddMarket() {
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
    void shouldDeleteMarket() {
        assertTrue(repository.deleteMarket(1));
    }

    @Test
    void shouldNotDeleteMarket() {
        assertFalse(repository.deleteMarket(5));
    }
}