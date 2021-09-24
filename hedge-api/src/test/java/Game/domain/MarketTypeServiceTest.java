package Game.domain;

import Game.data.MarketTypeRepository;
import Game.model.MarketType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class MarketTypeServiceTest {

    @Autowired
    MarketTypeService service;

    @MockBean
    MarketTypeRepository repository;

    @Test
    void shouldFindRoll() {
        when(repository.findRoll(4, 3)).thenReturn(createMarketType());
        Result<MarketType> result = service.findRoll(4, 3);
        assertEquals(ResultType.SUCCESS, result.getType());
        assertEquals(-5, result.getPayload().getBullModify());
        assertEquals(5, result.getPayload().getBearModify());
    }

    @Test
    void shouldNotFindRollTooHigh() {
        Result<MarketType> result = service.findRoll(22, 3);
        assertEquals(ResultType.INVALID, result.getType());
        assertEquals("Roll must be a number 1 through 20.", result.getMessages().get(0));
    }

    @Test
    void shouldNotFindRollWrongCompany() {
        Result<MarketType> result = service.findRoll(10, 30);
        assertEquals(ResultType.INVALID, result.getType());
        assertEquals("companyId must be a number 1 through 26.", result.getMessages().get(0));
    }

    MarketType createMarketType() {
        MarketType marketType = new MarketType();
        marketType.setBearModify(5);
        marketType.setBullModify(-5);
        marketType.setCompanyId(3);
        return marketType;
    }

}