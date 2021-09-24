package Game.domain;

import Game.data.LeaderBoardRepository;
import Game.model.LeaderBoard;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class LeaderBoardServiceTest {

    @Autowired
    LeaderBoardService service;

    @MockBean
    LeaderBoardRepository repository;

    @Test
    void findAll() {
        when(repository.findAll()).thenReturn(createLeaderboardList());
        Result<List<LeaderBoard>> leaderboards = service.findAll();
        assertEquals(1, leaderboards.getPayload().size());
    }

    @Test
    void addHighScore() {
        when(repository.addHighScore(anyString(), anyInt())).thenReturn(true);
        Result<Boolean> result = service.addHighScore("AriatCowboy", 99999);
        assertTrue(result.isSuccess());
    }

    @Test
    void shouldNotAddHighScore() {
        Result<Boolean> result = service.addHighScore("", 99999);
        assertFalse(result.isSuccess());
        result = service.addHighScore("AriatCowboy", -129873);
        assertFalse(result.isSuccess());
        result = service.addHighScore("AriatCowboy", -1);
        assertFalse(result.isSuccess());
    }

    List<LeaderBoard> createLeaderboardList() {
        List<LeaderBoard> leaderBoardList = new ArrayList<>();
        leaderBoardList.add(createLeaderBoard());
        return leaderBoardList;
    }

    LeaderBoard createLeaderBoard() {
        LeaderBoard leaderBoard = new LeaderBoard(1, "hey", 1);
        return leaderBoard;
    }
}