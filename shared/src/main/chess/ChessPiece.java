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
        throw new RuntimeException("Not implemented");
    }

    /**
     * SIGNATURE DEFINED BY COURSE!
     * @return which type of chess piece this piece is
     */
    public PieceType getPieceType() {
        throw new RuntimeException("Not implemented");
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
     * @return the piece type as a string
     */
    @Override
    public String toString() {
        return type.toString();
    }

    /**
     *
     * @return a string of one character to indicate the piece type
     */
    public String toSymbol() {
        switch (type) {
            case KING -> { return "K"; }
            case QUEEN -> { return "q"; }
            case KNIGHT -> { return "h"; }
            case BISHOP -> { return "b"; }
            case ROOK -> { return "r"; }
            case PAWN -> { return "p"; }
            default -> { return "?"; }
        }
    }
}
