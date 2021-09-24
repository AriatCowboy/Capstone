package Game.data;

import Game.model.MarketType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class MarketTypeJDBCRepositoryTest {
    @Autowired
    MarketTypeJDBCRepository repository;

    @Autowired
    KnownGoodState knownGoodState;

    @BeforeEach
    void setup() {
        knownGoodState.set();
    }

    @Test
    void shouldFindOneMarketType(){
        MarketType marketType= repository.findRoll(2, 4);

        assertEquals(2, marketType.getRoll());
        assertEquals(-4, marketType.getBullModify());
        assertEquals(62, marketType.getMarketId());
    }

    @Test
    void shouldFindNoMarketTypesWrongRoll(){
        MarketType marketType= repository.findRoll(21, 4);

        assertNull(marketType);
    }

    @Test
    void shouldFindNoMarketTypesWrongCompany(){
        MarketType marketType= repository.findRoll(11, 50);

        assertNull(marketType);
    }

}