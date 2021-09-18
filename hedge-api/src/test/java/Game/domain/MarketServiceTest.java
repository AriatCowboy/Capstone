package Game.domain;

import Game.data.MarketRepository;
import Game.model.Market;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class MarketServiceTest {

    @MockBean
    MarketRepository repository;

    @Autowired
    MarketService service;

//    @Test
//    void shouldAddMarket() {
//        Market market = new Market(3, 25, 1, 3, 1, 10, false, false);
//
//    }

}