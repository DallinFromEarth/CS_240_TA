package chess;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;

/**
 * Represents a single chess piece
 * <p>
 * Note: You can add to this class, but you may not alter
 * signature of the existing methods.
 */
public class ChessPiece {

    private PieceType type;
    private ChessGame.TeamColor pieceColor;

    public ChessPiece(ChessGame.TeamColor pieceColor, PieceType type) {
        this.type = type;
        this.pieceColor = pieceColor;
    }

    /**
     * The various different chess piece options
     * SIGNATURE DEFINED BY COURSE!
     */
    public enum PieceType {
        KING,
        QUEEN,
        BISHOP,
        KNIGHT,
        ROOK,
        PAWN
    }

    /**
     * SIGNATURE DEFINED BY COURSE!
     * @return Which team this chess piece belongs to
     */
    public ChessGame.TeamColor getTeamColor() {
        return pieceColor;
    }

    /**
     * SIGNATURE DEFINED BY COURSE!
     * @return which type of chess piece this piece is
     */
    public PieceType getPieceType() {
        return type;
    }

    /**
     * Calculates all the positions a chess piece can move to
     * Does not take into account moves that are illegal due to leaving the king in
     * danger
     * SIGNATURE DEFINED BY COURSE!
     *
     * @return Collection of valid moves
     */
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition) {
        //just double check that the piece at "myPosition" is at least the same type as this piece
        if (board.getPiece(myPosition).type != type) {
            throw new RuntimeException("chess piece at the position passed in does not match the type of this piece");
        }

        //set up a collection to add to
        //using hashmap to avoid duplicate moves
        HashSet<ChessMove> possibleMoves = new HashSet<ChessMove>();

        if (type == PieceType.KNIGHT) {
            addKnightMoves(board, myPosition, possibleMoves);
        } else if (type == PieceType.KING) {
            addKingMoves(board, myPosition, possibleMoves);
        } else if (type == PieceType.PAWN) {
            addPawnMoves(board, myPosition, possibleMoves);
        } else {
            if (type == PieceType.QUEEN | type == PieceType.BISHOP) {
                addDiagonalMoves(board, myPosition, possibleMoves);
            } if (type == PieceType.QUEEN | type == PieceType.ROOK) {
                addInLineMoves(board, myPosition, possibleMoves);
            }
        }

        return possibleMoves;
    }

    /**
     *
     * @return the piece type and color as a string
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(pieceColor.toString());
        builder.append(" ");
        builder.append(type.toString());
        return builder.toString();
    }

    /**
     *
     * @return a string of one character to indicate the piece type
     */
    public String toSymbol() {
        String symbol;
        switch (type) {
            case KING -> { symbol =  "K"; }
            case QUEEN -> { symbol = "Q"; }
            case KNIGHT -> { symbol = "H"; }
            case BISHOP -> { symbol = "B"; }
            case ROOK -> { symbol = "R"; }
            case PAWN -> { symbol = "P"; }
            default -> { symbol = "?"; }
        }

        //we need some way to distinguish black and white in the toString board method
        //so white pieces will be uppercase, black pieces lowercase
        if (pieceColor == ChessGame.TeamColor.BLACK) {
            symbol = symbol.toLowerCase();
        }
        return symbol;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChessPiece that = (ChessPiece) o;
        return type == that.type && pieceColor == that.pieceColor;
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, pieceColor);
    }





    /*************************************
     * Move Calculation Helper Functions *
     *************************************/

    /**
     * load the possible moves for a piece if they can move diagonally. For example, this is used for the Queen and Bishop
     * @param board
     * @param myPosition
     * @param possibleMoves
     */
    private void addDiagonalMoves(ChessBoard board, ChessPosition myPosition, HashSet<ChessMove> possibleMoves) {
        //step up to the right
        moveHelper(board, myPosition, possibleMoves, 1,1);
        //step up to the left
        moveHelper(board, myPosition, possibleMoves, 1,-1);
        //step down to the right
        moveHelper(board, myPosition, possibleMoves, -1,1);
        //step down to the left
        moveHelper(board, myPosition, possibleMoves, -1,-1);
    }

    private void addInLineMoves(ChessBoard board, ChessPosition myPosition, HashSet<ChessMove> possibleMoves) {
        //step straight up
        moveHelper(board, myPosition, possibleMoves, 1,0);
        //step straight down
        moveHelper(board, myPosition, possibleMoves, -1,0);
        //step straight right
        moveHelper(board, myPosition, possibleMoves, 0,1);
        //step straight left
        moveHelper(board, myPosition, possibleMoves, 0,-1);
    }

    private void addKnightMoves(ChessBoard board, ChessPosition myPosition, HashSet<ChessMove> possibleMoves) {
        final int boardBoundary = board.getBoardSize();
        final int row = myPosition.getRow();
        final int col = myPosition.getColumn();

        //I stole this idea from ChatGPT and it seems to work. (I stole the r/c loops -2:2 and saying its a move if they add to 3. The rest is mine)
        for (int r = -2; r < 3; r++) {
            for (int c = -2; c < 3; c++) {
                if (Math.abs(r) + Math.abs(c) == 3) { //if that move is in the shape of a knight move
                    ChessPosition current = new ChessPosition( (r + row), (c + col));
                    if ( isOnBoard(board, current) ) { //if that move stays on the board
                        if ( (board.getPiece(current) == null) || (board.getPiece(current).getTeamColor() != pieceColor) ) { //if the piece at position "current" is empty OR is on the other team
                            possibleMoves.add(new ChessMove(myPosition, current, null));
                        }
                    }
                }
            }
        }
    }

    private void addKingMoves(ChessBoard board, ChessPosition myPosition, HashSet<ChessMove> possibleMoves) {
        final int row = myPosition.getRow();
        final int col = myPosition.getColumn();

        for (int r = -1; r < 2; r++) {
            for (int c = -1; c < 2; c++) {
                ChessPosition current = new ChessPosition( (r + row), (c + col));
                if ( isOnBoard(board, current) ) { //if that move stays on the board
                    if ( (board.getPiece(current) == null) || (board.getPiece(current).getTeamColor() != pieceColor) ) { //if the piece at position "current" is empty OR is on the other team
                        possibleMoves.add(new ChessMove(myPosition, current, null));
                    }
                }
            }
        }
    }

    private void addPawnMoves(ChessBoard board, ChessPosition myPosition, HashSet<ChessMove> possibleMoves) {
        final int boardBoundary = board.getBoardSize();
        final int row = myPosition.getRow();
        final int col = myPosition.getColumn();

        //set to 1 for white, to move up. Set to -1 for black, to move down. (moving rows)
        int moveDirection;
        if (pieceColor == ChessGame.TeamColor.WHITE) { moveDirection = 1; }
        else if (pieceColor == ChessGame.TeamColor.BLACK) { moveDirection = -1; }
        else { throw new RuntimeException("invalid TeamColor"); }

        for (int c = -1; c < 2; c++) { //only iterate through the columns: one to the left, the current, then one to the right
            ChessPosition current = new ChessPosition( (row + moveDirection), (col + c) );

            if (isOnBoard(board, current)) {
                boolean promotePawn = ( current.getRow() == whatIsLastRow(pieceColor) );

                switch (c) {
                    case -1, 1 -> { //this checks the spaces to the sides
                        if (board.getPiece(current) != null) { //You can only move sideways if theres a piece there
                            if (board.getPiece(current).getTeamColor() != pieceColor) { //if the piece is on the other team
                                if (promotePawn) {
                                    addPawnPromoteMoves(myPosition, current, possibleMoves);
                                } else {
                                    possibleMoves.add(new ChessMove(myPosition, current, null));
                                }
                            }
                        }
                    }
                    case 0 -> { //this checks the space right ahead
                        if (board.getPiece(current) == null) { //if the space is empty or the piece there is on the other team
                            if (promotePawn) {
                                addPawnPromoteMoves(myPosition, current, possibleMoves);
                            }else {
                                possibleMoves.add(new ChessMove(myPosition, current, null));
                            }
                        }
                    }
                    default -> throw new RuntimeException("Something unexpected");
                }
            }

            //give the pawn starting double move powers
            if (row == whatIsStartingPawnRow(pieceColor)) {
                if ( board.getPiece(new ChessPosition(row + moveDirection, col)) == null ) {// if the space in front of you is empty
                    ChessPosition doubleStep = new ChessPosition( ( row + (moveDirection * 2) ), col );
                    if ( board.getPiece(doubleStep) == null) { //if the space two spaces in front of you is empty
                        possibleMoves.add(new ChessMove(myPosition, doubleStep, null));
                    }
                }
            }
        }
    }

    /**
     * loads the passed in HashSet with ChessMove objects with the passed in ChessPositions with promotion to all possible types
     * @param startPosition
     * @param endPosition
     * @param possibleMoves
     */
    private void addPawnPromoteMoves(ChessPosition startPosition, ChessPosition endPosition, HashSet<ChessMove> possibleMoves) {
        PieceType typeList[] = new PieceType[] { PieceType.QUEEN, PieceType.ROOK, PieceType.BISHOP, PieceType.KNIGHT };
        for (PieceType type : typeList) {
            possibleMoves.add(new ChessMove(startPosition, endPosition, type));
        }
    }

    /**
     * returns the last row for a pawn
     * @param color
     * @return 8 for white, 1 for black
     */
    private int whatIsLastRow(ChessGame.TeamColor color) {
        switch (color) {
            case BLACK -> { return 1; }
            case WHITE -> { return 8; }
            default -> throw new RuntimeException("Unknown color");
        }
    }

    /**
     * returns the row that the pawn starts on for the specified color
     * @param color
     * @return 2 for white, 7 for black
     */
    private int whatIsStartingPawnRow(ChessGame.TeamColor color) {
        switch (color) {
            case BLACK -> { return 7; }
            case WHITE -> { return 2; }
            default -> throw new RuntimeException("Unknown color");
        }
    }

    /**
     * Checks if the specified position is on the board
     * @param board
     * @param position
     * @return
     */
    private boolean isOnBoard(ChessBoard board, ChessPosition position) {
        return !( (position.getRow() < 1) | (position.getRow() > board.getBoardSize()) | (position.getColumn() < 1) | (position.getColumn() > board.getBoardSize()) );
    }

    /**
     * Adds ChessMove objects to possibleMoves that the piece can move to, continuously moving in the direction indicated by the rowIncrement and colIncrement
     * @param board
     * @param myPosition
     * @param possibleMoves
     * @param rowIncrement size/direction to step along the rows for each move
     * @param colIncrement size/direction to step along the columns for each move
     */
    private void moveHelper(ChessBoard board, ChessPosition myPosition, HashSet<ChessMove> possibleMoves, int rowIncrement, int colIncrement) {
        //this number is the last pos
        final int boardBoundary = board.getBoardSize();

        int row = myPosition.getRow();
        int col = myPosition.getColumn();

        while(true) {
            //step once in the direction
            row += rowIncrement;
            col += colIncrement;
            ChessPosition current = new ChessPosition(row, col);

            //if we have stepped off the board, return
            if ( !isOnBoard(board, current) ) { return; }

                //if the position on the board at position "current" is empty (i.e. null) then for this function it is a valid move
            if (board.getPiece(current) == null) {
                possibleMoves.add(new ChessMove(myPosition, current,null));
            } else { //what to do once you run into another piece
                if (board.getPiece(current).pieceColor != pieceColor) { //if that piece is the other team
                    possibleMoves.add(new ChessMove(myPosition, current, null)); //this gives you the option to capture
                }
                return; //exit loop once you run into or capture a piece
            }
        }
    }
}
