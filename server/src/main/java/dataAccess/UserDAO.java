package dataAccess;

import model.UserData;

public interface UserDAO {

    void clear() throws DataAccessException;

    void addUser(UserData user) throws DataAccessException;

    UserData getUser(String username) throws DataAccessException;
}
