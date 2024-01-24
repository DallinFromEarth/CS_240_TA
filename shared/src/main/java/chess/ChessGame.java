package chess;

import java.util.Collection;
import java.util.HashSet;

/**
 * For a class that can manage a chess game, making moves on a board
 * <p>
 * Note: You can add to this class, but you may not alter
 * signature of the existing methods.
 */
public class ChessGame {

    private TeamColor teamTurn;
    private ChessBoard board;

    public ChessGame() {
        teamTurn = TeamColor.WHITE;
        board = new ChessBoard();
        //FIXME: reset the board
        //board.resetBoard();
    }

    /**
     * SIGNATURE DEFINED BY COURSE!
     * @return Which team's turn it is
     */
    public TeamColor getTeamTurn() {
        return teamTurn;
    }

    /**
     * Set's which teams turn it is
     * SIGNATURE DEFINED BY COURSE!
     *
     * @param team the team whose turn it is
     */
    public void setTeamTurn(TeamColor team) {
        teamTurn = team;
    }

    /**
     * Enum identifying the 2 possible teams in a chess game
     * SIGNATURE DEFINED BY COURSE!
     */
    public enum TeamColor {
        WHITE,
        BLACK
    }

    /**
     * Gets a valid moves for a piece at the given location
     * SIGNATURE DEFINED BY COURSE!
     *
     * @param startPosition the piece to get valid moves for
     * @return Set of valid moves for requested piece, or null if no piece at
     * startPosition
     */
    public Collection<ChessMove> validMoves(ChessPosition startPosition) {
        ChessPiece piece = board.getPiece(startPosition);
        if (piece == null) { return null; }

        HashSet<ChessMove> moves = (HashSet<ChessMove>) piece.pieceMoves(board, startPosition);

        return removeIllegalMoves(moves);
    }

    private HashSet<ChessMove> removeIllegalMoves(HashSet<ChessMove> moves) {
        HashSet<ChessMove> badMoves = new HashSet<ChessMove>();
        for (ChessMove move : moves) {
            ChessPiece pieceGettingCaptured = board.getPiece(move.endPosition);
            //test out this move
            board.addPiece(move.endPosition, board.getPiece(move.startPosition)); //move piece to new spot
            board.addPiece(move.startPosition, null); //remove piece from old spot

            if (isInCheck(board.getPiece(move.endPosition).getTeamColor())) { // if the team that just moved is in check now:
                badMoves.add(move); //add to the list of bad moves
            }

            //put the board back how it was
            board.addPiece(move.startPosition, board.getPiece(move.endPosition));
            board.addPiece(move.endPosition, pieceGettingCaptured);
        }

        for (ChessMove badMove : badMoves) {
            moves.remove(badMove);
        }

        return moves;
    }

    /**
     * Makes a move in a chess game
     * SIGNATURE DEFINED BY COURSE!
     *
     * @param move chess move to preform
     * @throws InvalidMoveException if move is invalid
     */
    public void makeMove(ChessMove move) throws InvalidMoveException {
        if (board.getPiece(move.startPosition).getTeamColor() != teamTurn) { // if its not this team's turn
            throw new InvalidMoveException("Not " + teamTurn + "'s turn!");
        }

        System.out.println(board);
        Collection<ChessMove> validMoves = validMoves(move.startPosition);
        if (validMoves.contains(move)) {
            if ( move.promotionPiece == null ) { //not promoting
                board.addPiece(move.endPosition, board.getPiece(move.startPosition)); //move piece to new spot
            } else { // you are promoting a pawn
                board.addPiece(move.endPosition, new ChessPiece(teamTurn, move.promotionPiece));
            }
            board.addPiece(move.startPosition, null); //remove piece from old spot

            nextTurn();
            System.out.println("\n\nMade move: " + move);
        } else {
            System.out.println(("\n\n!Failed move: " + move));
            throw new InvalidMoveException();
        }
    }

    /**
     * switches the team from black to white, or white to black
     */
    private void nextTurn() {
        switch (teamTurn) {
            case WHITE -> teamTurn = TeamColor.BLACK;
            case BLACK -> teamTurn = TeamColor.WHITE;
        }
    }

    /**
     * Determines if the given team is in check
     * SIGNATURE DEFINED BY COURSE!
     *
     * @param teamColor which team to check for check
     * @return True if the specified team is in check
     */
    public boolean isInCheck(TeamColor teamColor) {
        ChessPosition kingPosition = board.findKing(teamColor);

        if (kingPosition == null) { return false; } // if there isn't a king on the board, they can't be in check

        board.getPiece(kingPosition).pieceMoves(board, kingPosition);

        return board.canBeCaptured(kingPosition);
    }

    /**
     * Determines if the given team is in checkmate
     * SIGNATURE DEFINED BY COURSE!
     *
     * @param teamColor which team to check for checkmate
     * @return True if the specified team is in checkmate
     */
    public boolean isInCheckmate(TeamColor teamColor) {
        HashSet<ChessMove> allTheMoves = new HashSet<ChessMove>();

        // Get every possible move that we could make
        for (int row = 1; row <= board.getBoardSize(); row++) {
            for (int col = 1; col <= board.getBoardSize(); col++) {
                ChessPosition curPosition = new ChessPosition(row,col);
                ChessPiece curPiece = board.getPiece(curPosition);

                if (curPiece.getTeamColor() == teamColor) { // we only care about moves we can make
                    allTheMoves.addAll( curPiece.pieceMoves(board,curPosition) );
                }
            }
        }



        throw new RuntimeException("NOT IMPLEMENTED");
    }

    /**
     * Determines if the given team is in stalemate, which here is defined as having
     * no valid moves
     * SIGNATURE DEFINED BY COURSE!
     *
     * @param teamColor which team to check for stalemate
     * @return True if the specified team is in stalemate, otherwise false
     */
    public boolean isInStalemate(TeamColor teamColor) {
        throw new RuntimeException("Not implemented");
    }

    /**
     * Sets this game's chessboard with a given board
     * SIGNATURE DEFINED BY COURSE!
     *
     * @param board the new board to use
     */
    public void setBoard(ChessBoard board) {
        this.board = board;
    }

    /**
     * Gets the current chessboard
     * SIGNATURE DEFINED BY COURSE!
     *
     * @return the chessboard
     */
    public ChessBoard getBoard() {
        return board;
    }
}
