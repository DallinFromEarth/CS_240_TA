package dataAccess.memoryDAO;

import dataAccess.AuthDAO;
import dataAccess.DataAccessException;
import model.AuthData;

import java.util.HashMap;

public class MemoryAuthDAO implements AuthDAO {

    /**
     * auth token, auth data object
     */
    private static HashMap<String, AuthData> data;

    private static void setUp() {
        if (data == null) {
            data = new HashMap<>();
        }
    }

    @Override
    public void addAuth(AuthData auth) throws DataAccessException {
        setUp();
        if (data.containsKey(auth.authToken())) {
            throw new DataAccessException("Tried to add auth that already exists in the database: " + auth.authToken());
        }
        data.put(auth.authToken(), auth);
    }

    @Override
    public AuthData getAuth(String authToken) throws DataAccessException {
        setUp();
        return data.get(authToken);
    }

    @Override
    public void deleteAuth(String authToken) throws DataAccessException {
        setUp();
        if (!data.containsKey(authToken)) {
            throw new DataAccessException("Tried to delete auth that doesn't exist: " + authToken);
        }
        data.remove(authToken);
    }

    @Override
    public void clear() throws DataAccessException {
        data = new HashMap<>();
    }
}
