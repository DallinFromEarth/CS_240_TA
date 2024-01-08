package chess;

import java.util.Collection;

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
        throw new RuntimeException("Not implemented");
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
}
