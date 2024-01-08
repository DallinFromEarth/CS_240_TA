import chess.*;


public class Main {

    public static void main(String[] args) {
        ChessPiece test = new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.KING);
        System.out.println(test.toString());
        System.out.println(test.toSymbol());
        System.out.println("Test");
        ChessBoard gameBoard = new ChessBoard();
        System.out.println(gameBoard.toString());
        ChessPosition test1 = new ChessPosition(1,'a');
        ChessPosition test2 = new ChessPosition(3,'c');
        ChessPosition test3 = new ChessPosition(8,8);
        gameBoard.addPiece(test1,new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.KNIGHT));
        gameBoard.addPiece(test2,new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.ROOK));
        gameBoard.addPiece(test3,new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.QUEEN));
//        gameBoard.board[0][0] = new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.KNIGHT);
//        gameBoard.board[1][0] = new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.KING);
//        gameBoard.board[0][1] = new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.QUEEN);
        System.out.println(gameBoard.toString());
        gameBoard.getPiece(new ChessPosition(3,'c')).pieceMoves(gameBoard, new ChessPosition(3,'c'));
    }
}
