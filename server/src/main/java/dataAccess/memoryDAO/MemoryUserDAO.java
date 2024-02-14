package dataAccess.memoryDAO;

import dataAccess.DataAccessException;
import dataAccess.UserDAO;
import model.UserData;

import java.util.HashMap;

public class MemoryUserDAO implements UserDAO {
    /**
     * username, user data object
     */
    private static HashMap<String, UserData> data;

    private static void setUp() {
        if (data == null) {
            data = new HashMap<String, UserData>();
        }
    }
    @Override
    public void clear() throws DataAccessException {
        data = new HashMap<String, UserData>();
    }

    @Override
    public void addUser(UserData user) throws DataAccessException {
        setUp();
        if (data.containsKey(user.username())) {
            throw new DataAccessException("Tried to add user that already exists in the database: " + user.username());
        }
        data.put(user.username(), user);
    }

    @Override
    public UserData getUser(String username) throws DataAccessException {
        setUp();
        return data.get(username);
    }
}
