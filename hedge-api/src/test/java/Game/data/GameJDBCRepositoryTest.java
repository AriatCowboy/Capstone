package Game.data;

import Game.model.Game;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class GameJDBCRepositoryTest {

    @Autowired
    GameJDBCRepository repository;

    @Autowired
    KnownGoodState knownGoodState;

    @BeforeEach
    void setup() {
        knownGoodState.set();
    }

    @Test
    void shouldFindByUserId() {
        Game game1 = repository.findGameByUserID(1);

        assertNotNull(game1);
        assertEquals(1, game1.getGameId());
        assertEquals(1, game1.getUserId());
    }

    @Test
    void shouldNotFindByUserId() {
        Game game1 = repository.findGameByUserID(5);

        assertNull(game1);
    }

    @Test
    void shouldAdd() {
        Game game = makeGame();
        Game actual = repository.addGame(game);
        assertNotNull(actual);
        assertEquals(3, actual.getGameId());
    }

    @Test
    void shouldUpdate() {
        Game game = makeGame();
        game.setGameId(1);
        game.setUserId(1);
        game.setLastYear(4);
        assertTrue(repository.updateGameState(game));
    }

    @Test
    void shouldNotUpdate() {
        Game game = makeGame();
        game.setGameId(1);
        game.setUserId(5);
        assertFalse(repository.updateGameState(game));
    }

    @Test
    void shouldDelete() {
        assertTrue(repository.deleteGame(2));
    }

    @Test
    void shouldNotDelete() {
        assertFalse(repository.deleteGame(4));
    }

    private Game makeGame() {
        Game game = new Game();
        game.setUserId(3);
        game.setLastYear(1);
        return game;
    }


}