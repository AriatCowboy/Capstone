package Game.data;

import Game.model.Game;

public interface GameRepository {
    Game findGameByUserID (int userId);
    Boolean addGameState (Game game);
    Boolean updateGameState (Game game);
    Boolean deleteGameState (int gameId);
}
