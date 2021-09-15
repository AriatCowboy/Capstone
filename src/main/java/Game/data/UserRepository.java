package Game.data;

import Game.model.User;

public interface UserRepository {
    User FindByUserName (String Username);
    Boolean addUser (String username);
    Boolean deleteUser (int userId);
}
