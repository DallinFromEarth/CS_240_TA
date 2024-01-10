package chess;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Objects;

import static chess.ChessGame.TeamColor.BLACK;
import static chess.ChessGame.TeamColor.WHITE;

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
        board = new ChessPiece[BOARD_SIZE][BOARD_SIZE];

        loadPawnsToBoard(WHITE, 2);
        loadRooksToBoard(WHITE, 1);
        loadKnightsToBoard(WHITE,1);
        loadBishopsToBoard(WHITE, 1);

        loadPawnsToBoard(BLACK,7);
        loadRooksToBoard(BLACK,8);
        loadKnightsToBoard(BLACK,8);
        loadBishopsToBoard(BLACK, 8);

        loadRoyalty();
    }

    /**
     * ONLY CALL FROM RESETBOARD()
     * fills the pawns in their starting rows on the board
     * @param color
     * @param row
     */
    private void loadPawnsToBoard(ChessGame.TeamColor color, int row) {
        for (int col = 1; col <= BOARD_SIZE; col++) {
            addPiece(new ChessPosition(row, col), new ChessPiece(color, ChessPiece.PieceType.PAWN));
        }
    }

    private void loadKnightsToBoard(ChessGame.TeamColor color, int row) {
        addPiece(new ChessPosition(row, 2), new ChessPiece(color, ChessPiece.PieceType.KNIGHT));
        addPiece(new ChessPosition(row, 7), new ChessPiece(color, ChessPiece.PieceType.KNIGHT));
    }
    private void loadRooksToBoard(ChessGame.TeamColor color, int row) {
        addPiece(new ChessPosition(row, 1), new ChessPiece(color, ChessPiece.PieceType.ROOK));
        addPiece(new ChessPosition(row, 8), new ChessPiece(color, ChessPiece.PieceType.ROOK));
    }
    private void loadBishopsToBoard(ChessGame.TeamColor color, int row) {
        addPiece(new ChessPosition(row, 3), new ChessPiece(color, ChessPiece.PieceType.BISHOP));
        addPiece(new ChessPosition(row, 6), new ChessPiece(color, ChessPiece.PieceType.BISHOP));
    }
    private void loadRoyalty() {
        addPiece(new ChessPosition(1, 'd'), new ChessPiece(WHITE, ChessPiece.PieceType.QUEEN));
        addPiece(new ChessPosition(1, 'e'), new ChessPiece(WHITE, ChessPiece.PieceType.KING));
        addPiece(new ChessPosition(8, 'd'), new ChessPiece(BLACK, ChessPiece.PieceType.QUEEN));
        addPiece(new ChessPosition(8, 'e'), new ChessPiece(BLACK, ChessPiece.PieceType.KING));
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChessBoard that = (ChessBoard) o;

        for (int i = 0; i < getBoardSize(); i++) {
            for (int j =0; j < getBoardSize(); j++) {
                ChessPosition current = new ChessPosition(i+1, j+1);
                ChessPiece thisPiece = getPiece(current);
                ChessPiece thatPiece = that.getPiece(current);
                if (thisPiece == null && thatPiece == null) { continue; }
                if ( !thisPiece.equals(thatPiece) ) {
                    return false;
                }
            }
        }
        return BOARD_SIZE == that.BOARD_SIZE && Arrays.deepEquals(board, that.board);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(BOARD_SIZE);
        result = 31 * result + Arrays.hashCode(board);
        return result;
    }
}
