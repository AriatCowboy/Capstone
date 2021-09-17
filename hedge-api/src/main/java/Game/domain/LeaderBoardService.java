package Game.domain;

import Game.data.LeaderBoardRepository;
import Game.model.LeaderBoard;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeaderBoardService {
    private final LeaderBoardRepository repository;
    public LeaderBoardService(LeaderBoardRepository repository) {
        this.repository = repository;
    }
    public List<LeaderBoard> findAll(){
        return repository.findAll();
    }
    public Result<Boolean> addHighScore(String username, int score){
        Result<Boolean> result = new Result<>();
        if (!username.isEmpty()){
            if (score > -1){
                Boolean leaderBoard = repository.addHighScore(username, score);
                result.setPayload(leaderBoard);
            } else {
                result.addMessage("score must be a number greater than -1.", ResultType.INVALID);
            }
        } else {
            result.addMessage("username cannot be empty.", ResultType.INVALID);
        }
        return result;
    }
}
