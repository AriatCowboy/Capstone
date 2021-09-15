package Game.data;

import Game.model.User;

public class UserJDBCRepository implements UserRepository{
    public User FindByUserName (String Username){
        return null;
    }
    public Boolean addUser (String username){
        return false;
    }
    public Boolean deleteUser (int userId){
        return false;
    }
}
