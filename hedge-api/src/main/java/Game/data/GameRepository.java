package Game.data;

import Game.model.Game;

public interface GameRepository {
    Game findGameByUserID (int userId);
    Game addGame (Game game);
    Boolean updateGameState (Game game);
    Boolean deleteGame (int gameId);
}
