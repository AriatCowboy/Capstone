package Game.domain;

import Game.data.GameRepository;
import Game.data.LeaderBoardRepository;
import Game.model.Company;
import Game.model.Game;
import Game.model.Market;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class GameServiceTest {

    @Autowired
    GameService service;

    @MockBean
    GameRepository repository;

    @MockBean
    MarketService marketService;

    @Test
    void findGameById() {
        when(repository.findGameById(1)).thenReturn(createGame());
        when(marketService.findByGameId(1)).thenReturn(createMarkets());

        Game game = service.findGameById(1);
        assertEquals("1", game.getUserId());
    }

    @Test
    void shouldNotFindGameById() {
        Game game = service.findGameById(50);
        assertNull(game);
    }

    @Test
    void findGameByUserId() {
        when(repository.findGameByUserID("1")).thenReturn(createGame());
        when(marketService.findByGameId(1)).thenReturn(createMarkets());

        Game game = service.findGameByUserID("1");
        assertEquals("1", game.getUserId());
    }

    @Test
    void shouldStartGame() {
        when(repository.findGameByUserID("1")).thenReturn(createGame());
        when(marketService.findByGameId(1)).thenReturn(createMarkets());
        when(repository.updateGameState(any())).thenReturn(true);
        Result<Market> result = new Result<>();
        result.setPayload(createMarket());
        when(marketService.addMarket(any())).thenReturn(result);
        when(marketService.generateThisYearMarket(anyInt(), anyInt())).thenReturn(createMarkets());

        Game game = service.startGame("1");
        assertNotNull(game);
        assertEquals(1, game.getGameId());
        assertEquals("1", game.getUserId());
        assertEquals(10, game.getMarkets().size());
        assertEquals(2, game.getYear());
        assertEquals(0, game.getMessages().size());
    }

    @Test
    void shouldStartNewGame() {
        when(repository.findGameByUserID("1")).thenReturn(null);
        when(repository.addGame(any())).thenReturn(createGame());
        when(marketService.startNewGame(any())).thenReturn(createMarkets());
        Game game = service.startGame("1");
        assertNotNull(game);
        assertEquals(1, game.getGameId());
        assertEquals("1", game.getUserId());
        assertEquals(10, game.getMarkets().size());
        assertEquals(1, game.getYear());
        assertEquals(0, game.getMessages().size());
    }

    @Test
    void shouldFinish() {
        Result<Market> result = new Result<>();
        result.setPayload(createMarket());
        when(marketService.addMarket(any())).thenReturn(result);
        when(repository.updateGameState(any())).thenReturn(true);
        when(marketService.findByGameId(anyInt())).thenReturn(createMarkets());

        Game game = createGame();
        game.setMarkets(createMarkets());
        game = service.finalRound(game);

        assertNotNull(game);
        assertEquals(1, game.getGameId());
        assertEquals("1", game.getUserId());
        assertEquals(10, game.getMarkets().size());
        assertEquals(2, game.getYear());
        assertEquals(0, game.getMessages().size());
    }

    @Test
    void shouldDeleteGame() {
        when(marketService.deleteMarket(1)).thenReturn(true);
        when(repository.deleteGame(1)).thenReturn(true);
        when(repository.findGameByUserID("1")).thenReturn(createGame());
        assertTrue(service.deleteGame("1"));
    }

    Game createGame() {
        List<Market> marketList = new ArrayList<>();
        marketList.add(createMarket());
        Game game = new Game(1, "1", 1, 1, marketList);
        return game;
    }

    List<Market> createMarkets() {
        List<Market> marketList = new ArrayList<>();
        marketList.add(createMarket());
        marketList.add(createMarket());
        marketList.add(createMarket());
        marketList.add(createMarket());
        marketList.add(createMarket());
        marketList.add(createMarket());
        marketList.add(createMarket());
        marketList.add(createMarket());
        marketList.add(createMarket());
        marketList.add(createMarket());
        return marketList;
    }

    Market createMarket() {
        Market market = new Market();
        market.setMarketId(1);
        market.setYearNumber(1);
        market.setCompany(createCompany());
        return market;
    }

    Company createCompany() {
        Company company = new Company();
        company.setCompanyId(1);
        company.setName("blah");
        return company;
    }
}

