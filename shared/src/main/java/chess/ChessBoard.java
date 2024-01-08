package chess;

import java.lang.reflect.Array;

/**
 * A chessboard that can hold and rearrange chess pieces.
 * <p>
 * Note: You can add to this class, but you may not alter
 * signature of the existing methods.
 */
public class ChessBoard {

    /**
     * this is defined so that 0,0 is a1. 1,0 is a2. 0,1 is b1.
     * the first coordinate corresponds to the row number (minus 1)
     * the second coordinate corresponds to the col letter (a=0)
     */
    private ChessPiece[][] board;
    private final int BOARD_SIZE = 8;


    public ChessBoard() {
        board = new ChessPiece[BOARD_SIZE][BOARD_SIZE];
    }

    public int getBoardSize() { return BOARD_SIZE; }

    /**
     * Adds a chess piece to the chessboard
     * SIGNATURE DEFINED BY COURSE!
     *
     * @param position where to add the piece to
     * @param piece    the piece to add
     */
    public void addPiece(ChessPosition position, ChessPiece piece) {
        int row = position.getRow() - 1;
        int col = position.getColumn() - 1;
        board[row][col] = piece;
    }

    /**
     * Gets a chess piece on the chessboard
     * SIGNATURE DEFINED BY COURSE!
     *
     * @param position The position to get the piece from
     * @return Either the piece at the position, or null if no piece is at that
     * position
     */
    public ChessPiece getPiece(ChessPosition position) {
        int row = position.getRow() - 1;
        int col = position.getColumn() - 1;
        return board[row][col];
    }

    /**
     * Sets the board to the default starting board
     * (How the game of chess normally starts)
     * SIGNATURE DEFINED BY COURSE!
     */
    public void resetBoard() {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public String toString() {
       StringBuilder builder = new StringBuilder();
       char colChar = 'A';
       int rowNum = BOARD_SIZE;

       for (int j = BOARD_SIZE - 1; j >= 0; j--) {
           //get this row
           ChessPiece[] row = board[j];

           //print the row number
           builder.append("\n");
           builder.append(rowNum);
           builder.append(" ");

           //print the pieces in the row
           for (ChessPiece piece : row) {
               if (piece == null) {
                   builder.append("-");
               } else {
                   builder.append(piece.toSymbol());
               }
               builder.append(" ");
           }

           //advance to the next row
           rowNum--;
       }

       //print the column letters
        builder.append("\n  ");
        for (int i = 0; i < BOARD_SIZE; i++) {
            builder.append(colChar);
            colChar++;
            builder.append(" ");
        }
       return builder.toString();
    }
}
