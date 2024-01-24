import chess.*;


public class Main {

    public static void main(String[] args) {
        ChessGame game = new ChessGame();
        ChessBoard gameBoard = game.getBoard();

        System.out.println(gameBoard.toString());
        ChessPiece test = new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.KING);
        ChessPosition test1 = new ChessPosition(6,'f');
        ChessPosition test2 = new ChessPosition(3,'c');
        ChessPosition test3 = new ChessPosition(6,8);
        ChessPosition test4 = new ChessPosition(5,'d');
        ChessPosition test5 = new ChessPosition(8,'h');
        gameBoard.addPiece(test1,new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.KNIGHT));
        gameBoard.addPiece(test2,new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.ROOK));
        gameBoard.addPiece(test3,new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.QUEEN));
        gameBoard.addPiece(test4,new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.KNIGHT));
        gameBoard.addPiece(test5,new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.KING));
        System.out.println(gameBoard.toString());
        System.out.println("SNAKES");
        System.out.println(gameBoard.findKing(ChessGame.TeamColor.WHITE));
        gameBoard.getPiece(test4).pieceMoves(gameBoard, test4);

        System.out.println(game.isInCheck(ChessGame.TeamColor.BLACK));
    }
}
