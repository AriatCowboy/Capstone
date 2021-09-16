package Game.domain;

import Game.data.MarketTypeRepository;
import Game.model.MarketType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class MarketTypeServiceTest {

    @Autowired
    MarketTypeService service;

    @Autowired
    MarketTypeRepository marketTypeRepository;

    @Test
    void findRoll() {
        Result<MarketType> result = service.findRoll(4, 3);
        assertEquals(ResultType.SUCCESS, result.getType());
        assertEquals(-5, result.getPayload().getBullModify());
        assertEquals(5, result.getPayload().getBearModify());
    }
    @Test
    void shouldNotFindRoll() {
        Result<MarketType> result = service.findRoll(40, 3);
        assertEquals(false, result.isSuccess());

    }
}