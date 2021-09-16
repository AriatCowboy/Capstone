package Game.domain;

import Game.data.LeaderBoardRepository;
import Game.model.LeaderBoard;

import java.util.List;

public class LeaderBoardService {
    private final LeaderBoardRepository repository;
    public LeaderBoardService(LeaderBoardRepository repository) {
        this.repository = repository;
    }

    public Result<LeaderBoard> findAll(){
         Result<LeaderBoard> result = new Result<>();
         List<LeaderBoard> leaderBoard = repository.findAll();
         result.setPayload((LeaderBoard) leaderBoard);
         return result;
    }

}
