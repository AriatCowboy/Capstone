package Game.domain;

import Game.data.LeaderBoardRepository;
import Game.model.LeaderBoard;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class LeaderBoardServiceTest {

    @Autowired
    LeaderBoardService service;

    @Autowired
    LeaderBoardRepository leaderBoardRepository;

    @Test
    void findAll() {
        List<LeaderBoard> leaderboards = service.findAll();
        assertEquals(1, leaderboards.size());
    }

    @Test
    void addHighScore() {
        Result<Boolean> success = service.addHighScore("AriatCowboy", 99999);
        assertTrue(success.isSuccess());
    }

    @Test
    void shouldNotAddHighScore() {
        Result<Boolean> failure = service.addHighScore("", 99999);
        assertFalse(failure.isSuccess());
        failure = service.addHighScore("AriatCowboy", -129873);
        assertFalse(failure.isSuccess());
        failure = service.addHighScore("AriatCowboy", -1);
        assertFalse(failure.isSuccess());
    }
}