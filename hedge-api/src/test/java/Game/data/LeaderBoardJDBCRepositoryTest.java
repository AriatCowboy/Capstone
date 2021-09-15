package Game.data;

import Game.model.Game;
import Game.model.LeaderBoard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class LeaderBoardJDBCRepositoryTest {

    @Autowired
    LeaderBoardJDBCRepository repository;

    @Autowired
    KnownGoodState knownGoodState;

    @BeforeEach
    void setup() {
        knownGoodState.set();
    }

    @Test
    void shouldAdd() {
        assertTrue(repository.addHighScore("blah", 200));
        assertTrue(repository.addHighScore("blah", 200));
    }

    @Test
    void shouldFindAll() {
        List<LeaderBoard> leaderBoardList = repository.findAll();

        assertEquals(1, leaderBoardList.size());
    }



}