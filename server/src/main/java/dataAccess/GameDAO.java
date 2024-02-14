package dataAccess;

import model.GameData;

import java.util.Collection;

public interface GameDAO {

    void addGame(GameData game) throws DataAccessException;

    GameData getGame(int gameID) throws DataAccessException;

    Collection<GameData> listGames() throws DataAccessException;

    void updateGame(int gameID, GameData game) throws DataAccessException;

    void clear() throws DataAccessException;
}
