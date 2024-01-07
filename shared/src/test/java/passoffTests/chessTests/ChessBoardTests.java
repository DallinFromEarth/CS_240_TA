package passoffTests.chessTests;

import chess.*;
import org.junit.jupiter.api.*;

import static passoffTests.TestFactory.*;

public class ChessBoardTests {

    @Test
    @DisplayName("Add and Get Piece")
    public void getAddPiece() {
        ChessPosition position = getNewPosition(4, 4);
        ChessPiece piece = getNewPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.BISHOP);

        var board = getNewBoard();
        board.addPiece(position, piece);

        ChessPiece foundPiece = board.getPiece(position);

        Assertions.assertEquals(piece.getPieceType(), foundPiece.getPieceType(),
                "ChessPiece returned by getPiece had the wrong piece type");
        Assertions.assertEquals(piece.getTeamColor(), foundPiece.getTeamColor(),
                "ChessPiece returned by getPiece had the wrong team color");
    }


    @Test
    @DisplayName("Reset Board")
    public void defaultGameBoard() {
        var expectedBoard = loadBoard("""
                |r|n|b|q|k|b|n|r|
                |p|p|p|p|p|p|p|p|
                | | | | | | | | |
                | | | | | | | | |
                | | | | | | | | |
                | | | | | | | | |
                |P|P|P|P|P|P|P|P|
                |R|N|B|Q|K|B|N|R|
                """);

        var actualBoard = getNewBoard();
        actualBoard.resetBoard();

        Assertions.assertEquals(expectedBoard, actualBoard);
    }

}
