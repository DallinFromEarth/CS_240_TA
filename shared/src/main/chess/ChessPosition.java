package chess;

/**
 * Represents a single square position on a chess board
 * <p>
 * Note: You can add to this class, but you may not alter
 * signature of the existing methods.
 */
public class ChessPosition {

    private int row;
    private int col;

    public ChessPosition(int row, int col) {
        this.row = row;
        this.col = col;
    }

    /**
     * SIGNATURE DEFINED BY COURSE!
     * @return which row this position is in
     * 1 codes for the bottom row
     */
    public int getRow() {
        return row + 1;
    }

    /**
     * SIGNATURE DEFINED BY COURSE!
     * @return which column this position is in
     * 1 codes for the left row
     */
    public int getColumn() {
        return col + 1;
    }
}
