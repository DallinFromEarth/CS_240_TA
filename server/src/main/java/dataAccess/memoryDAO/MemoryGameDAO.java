package dataAccess.memoryDAO;

import dataAccess.DataAccessException;
import dataAccess.GameDAO;
import model.GameData;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class MemoryGameDAO implements GameDAO {
    /**
     * gameID, game data object
     */
    private static HashMap<Integer, GameData> data;

    private static void setUp() {
        if (data == null) {
            data = new HashMap<Integer, GameData>();
        }
    }
    @Override
    public void addGame(GameData game) throws DataAccessException {
        setUp();
        if (data.containsKey(game.gameID())) {
            throw new DataAccessException("Tried to add game that already exists in the database: " + game.gameName() + " {" + game.gameID() + "}");
        }
        data.put(game.gameID(), game);
    }

    @Override
    public GameData getGame(int gameID) throws DataAccessException {
        setUp();
        return data.get(gameID);
    }

    @Override
    public Collection<GameData> listGames() throws DataAccessException {
        Collection<GameData> gameDataCollection = new ArrayList<GameData>();
        for (Integer key : data.keySet()) {
            gameDataCollection.add(data.get(key));
        }
        return gameDataCollection;
    }

    @Override
    public void updateGame(int gameID, GameData game) throws DataAccessException {
        setUp();
        if (!data.containsKey(gameID)) { throw new DataAccessException("tried to update game that does not exist"); }
        data.put(gameID, game);
    }

    @Override
    public void clear() throws DataAccessException {
        data = new HashMap<Integer, GameData>();
    }
}
