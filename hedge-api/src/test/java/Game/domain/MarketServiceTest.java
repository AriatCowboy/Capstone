package Game.domain;

import Game.data.MarketRepository;
import Game.model.Company;
import Game.model.Game;
import Game.model.Market;
import Game.model.MarketType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.yaml.snakeyaml.error.Mark;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class MarketServiceTest {

    @MockBean
    MarketRepository repository;

    @MockBean
    CompanyService companyService;

    @MockBean
    MarketTypeService marketTypeService;

    @Autowired
    MarketService service;

    @Test
    void shouldFindByGameId() {
        when(repository.findByGameId(1)).thenReturn(createMarkets());
        List<Market> markets = service.findByGameId(1);
        assertEquals(1, markets.size());
    }

    @Test
    void shouldNotFindByGameId() {
        List<Market> markets = service.findByGameId(-1);
        assertEquals(0, markets.size());
    }

    @Test
    void shouldFindPortfolio() {
        when(repository.findPortfolio(1, 1)).thenReturn(createMarkets());
        List<Market> markets = service.findPortfolio(1, 1);
        assertEquals(1, markets.size());
    }

    @Test
    void shouldNotFindPortfolioGameIdWrong() {
        List<Market> markets = service.findPortfolio(-1, 1);
        assertEquals(0, markets.size());
    }

    @Test
    void shouldNotFindPortfolioYearWrong() {
        List<Market> markets = service.findPortfolio(1, -1);
        assertEquals(0, markets.size());
    }

    @Test
    void shouldFindByCompanyId() {
        when(repository.findByCompanyId(1, 1)).thenReturn(createMarkets());
        List<Market> markets = service.findByCompanyId(1, 1);
        assertEquals(1, markets.size());
    }

    @Test
    void shouldNotFindByCompanyIdGameIdWrong() {
        List<Market> markets = service.findByCompanyId(1, -1);
        assertEquals(0, markets.size());
    }

    @Test
    void shouldNotFindByCompanyIdCompanyIdWrong() {
        List<Market> markets = service.findByCompanyId(27, 1);
        assertEquals(0, markets.size());
    }

    @Test
    void shouldStartNewGame() {
        Result<Company> result = new Result<>();
        result.setPayload(createCompany());

        when(companyService.findById(anyInt())).thenReturn(result);
        Game game = new Game();
        game.setYear(0);
        game.setGameId(1);

        List<Market> markets = service.startNewGame(game);
        assertEquals(10, markets.size());
        assertEquals(1, markets.get(0).getYearNumber());
    }

    @Test
    void shouldNotGenerateThisYearMarketWrongInput() {
        List<Market> marketList = new ArrayList<>();
        marketList = service.generateThisYearMarket(-1, 1);
        assertEquals(0, marketList.size());
        marketList = service.generateThisYearMarket(1, -1);
        assertEquals(0, marketList.size());
    }

    @Test
    void shouldGenerateThisYearMarket() {
        when(repository.findPortfolio(1, 1)).thenReturn(createMarkets());
        Result<MarketType> marketTypeResult = new Result<>();
        MarketType marketType = new MarketType(1,1,1,1,1);
        marketTypeResult.setPayload(marketType);
        when(marketTypeService.findRoll(anyInt(), anyInt())).thenReturn(marketTypeResult);
        List<Market> marketList = service.generateThisYearMarket(2, 1);
        assertEquals(1, marketList.size());
    }

    @Test
    void shouldAddMarket() {
        when(repository.addMarket(any())).thenReturn(true);
        Market market = new Market();
        market.setCompany(createCompany());
        market.setStockPurchasedYear(1);
        market.setStockPurchasedTotal(0);
        market.setStockPurchasedYear(0);

        Result<Market> marketResult = service.addMarket(market);
        assertTrue(marketResult.isSuccess());
    }

    @Test
    void shouldSetBankrupt() {
        when(repository.setBankrupt(any())).thenReturn(true);
        Market market = new Market();
        market.setCompany(createCompany());
        market.setStockPurchasedYear(1);
        market.setStockPurchasedTotal(0);
        market.setStockPurchasedYear(0);

        assertTrue(service.setBankrupt(market));
    }

    @Test
    void shouldDeleteMarket() {
        when(repository.deleteMarket(1)).thenReturn(true);
        assertTrue(service.deleteMarket(1));
    }

    List<Market> createMarkets() {
        List<Market> marketList = new ArrayList<>();
        Market market = new Market();
        market.setMarketId(1);
        market.setYearNumber(1);
        market.setCompany(createCompany());
        marketList.add(market);
        return marketList;
    }

    Company createCompany() {
        Company company = new Company();
        company.setCompanyId(1);
        company.setName("blah");
        return company;
    }

}