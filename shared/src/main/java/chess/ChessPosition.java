package chess;

import java.util.Objects;

/**
 * Represents a single square position on a chess board
 * <p>
 * Note: You can add to this class, but you may not alter
 * signature of the existing methods.
 */
public class ChessPosition {

    private int row;
    private int col;

    /**
     *
     * @param row 1 codes for bottom row
     * @param col 1 codes for left column
     */
    public ChessPosition(int row, int col) {
        this.row = row;
        this.col = col;
    }

    /**
     *
     * @param row 1 codes for bottom row
     * @param col 'a' codes for left column
     */
    public ChessPosition(int row, char col) {
        this.row = row;
        // this converts the character into the relative column number where the left column is 1
        // since 'a' = 97
        this.col = (int) col - 96;
    }

    /**
     * SIGNATURE DEFINED BY COURSE!
     * @return which row this position is in
     * 1 codes for the bottom row
     */
    public int getRow() {
        return row;
    }

    /**
     * SIGNATURE DEFINED BY COURSE!
     * @return which column this position is in
     * 1 codes for the left row
     */
    public int getColumn() {
        return col;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChessPosition that = (ChessPosition) o;
        return row == that.row && col == that.col;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, col);
    }

    @Override
    public String toString() {
        return "" + row + (char) (col +96);
    }
}
