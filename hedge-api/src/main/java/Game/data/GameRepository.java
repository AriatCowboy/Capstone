package Game.data;

import Game.model.Game;

public interface GameRepository {
    Game findGameById (int gameId);
    Game findGameByUserID (String userId);
    Game addGame (Game game);
    Boolean updateGameState (Game game);
    Boolean deleteGame (int gameId);
}
