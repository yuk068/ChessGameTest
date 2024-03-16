package hus.TestZone.ChessGameTest.TheGame.UI;

import hus.TestZone.ChessGameTest.TheGame.Board.Board;
import hus.TestZone.ChessGameTest.TheGame.Gameplay.Piece.Bishop;
import hus.TestZone.ChessGameTest.TheGame.Gameplay.Piece.King;
import hus.TestZone.ChessGameTest.TheGame.Gameplay.Piece.Queen;
import hus.TestZone.ChessGameTest.TheGame.Board.Square;
import hus.TestZone.ChessGameTest.TheGame.Gameplay.Piece.Rook;
import hus.TestZone.ChessGameTest.TheGame.Utility;

import java.util.Arrays;

public class Test {
    public static void main(String[] args) {
        Board.initializeBoard();
        Board.updateBoard();
        Square[] squares = Board.getSquares();
        int id = 37;
        squares[11].getPiece().move(27);
        squares[3].getPiece().move(19);
        squares[19].getPiece().move(18);
        squares[15].getPiece().move(31);
        squares[14].getPiece().move(30);
        squares[7].setPiece(null);
        squares[5].setPiece(null);
        squares[18].getPiece().move(23);
        squares[6].getPiece().move(21);
        squares[37].setPiece(new Rook(37, true));
        squares[54].getPiece().move(46);
        squares[28].setPiece(new King(28, false));
        Board.updateBoard();
        System.out.println(squares[id].isProtected(id, squares));
        System.out.println(Arrays.toString(Utility.idsArrayToCoordinatesArray(squares[28].getPiece().getLegalMoves())));

    }
}
