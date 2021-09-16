package Game.domain;

import Game.data.GameRepository;
import Game.data.LeaderBoardRepository;
import Game.model.Game;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class GameServiceTest {

    @Autowired
    GameService service;

    @Autowired
    GameRepository gameRepository;

    @Test
    void findGameById() {
       Game game = service.findGameById(1);
        assertEquals(1, game.getUserId());
    }

    @Test
    void shouldNotFindGameById() {
        Game game = service.findGameById(50);
        assertNull(game);
    }


    @Test
    void findGameByUserID() {
        Game game = service.findGameByUserID(1);
        assertEquals(1, game.getUserId());
    }

    @Test
    void shouldNotFindGameByUserID() {
        Game game = service.findGameByUserID(2);
        assertNull(game);
    }

    @Test
    void addGame() {
        Game game = new Game(2, 2, 9);
        Result<Game> gameResult = service.addGame(game);
        assertTrue(gameResult.isSuccess());
    }

    @Test
    void shouldNotAddGame() {
        Game game = new Game(0, 2, 0);
        Result<Game> gameResult = service.addGame(game);
        assertTrue(gameResult.getMessages().size() > 0);

        game = new Game(2, 2, 0);
        gameResult = service.addGame(game);
        assertTrue(gameResult.getMessages().size() > 0);

        game = new Game(2, 0, 3);
        gameResult = service.addGame(game);
        assertTrue(gameResult.getMessages().size() > 0);
    }

    @Test
    void updateGameState() {
        Game game = new Game(1, 1, 9);
        Result<Boolean> gameResult = service.updateGameState(game);
        assertTrue(gameResult.isSuccess());
        game = service.findGameById(1);
        assertEquals(9, game.getLastYear());
    }

    @Test
    void shouldNotUpdateGameState() {
        Game game = new Game(0, 1, 9);
        Result<Boolean> gameResult = service.updateGameState(game);
        assertTrue(gameResult.getMessages().size() > 0);

        game = new Game(1, 0, 9);
        gameResult = service.updateGameState(game);
        assertTrue(gameResult.getMessages().size() > 0);

        game = new Game(1, 1, 0);
        gameResult = service.updateGameState(game);
        assertTrue(gameResult.getMessages().size() > 0);
    }

    @Test
    void deleteGame() {
        boolean result = service.deleteGame(1);
        Game game = service.findGameById(1);
        assertEquals(false, game);
    }
}