package hus.TestZone.ChessGameTest.TheGame.UI;

import hus.TestZone.ChessGameTest.TheGame.Board.Board;
import hus.TestZone.ChessGameTest.TheGame.Board.Square;
import hus.TestZone.ChessGameTest.TheGame.Gameplay.Piece.*;
import hus.TestZone.ChessGameTest.TheGame.Utility;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    static final Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        Board.initializeBoard();
        Square[] squares = Board.getSquares();
        Board.updateBoard();
        String input;
        boolean turn = false;
        while (true) {
            if (turn) {
                System.out.println("Black's turn");
            } else {
                System.out.println("White's turn");
            }
            System.out.println("Select a square (\"exit\" to quit): ");
            input = in.nextLine().toUpperCase();
            if (input.equals("EXIT")) {
                break;
            }
            if (turn) {
                System.out.println("Black's turn");
            } else {
                System.out.println("White's turn");
            }
            int idSelect = Utility.coordinateToId(input);
            System.out.println(squares[idSelect]);
            if (squares[idSelect].getPiece() == null) {
                continue;
            }
            if (turn && !squares[idSelect].getPiece().isBlackWhite()) {
                System.out.println("It's Black's turn");
                continue;
            } else if (!turn && squares[idSelect].getPiece().isBlackWhite()){
                System.out.println("It's White's turn");
                continue;
            }
            System.out.println(Arrays.toString(Utility.idsArrayToCoordinatesArray(squares[idSelect].getPiece().getLegalMoves())));
            System.out.println("Enter your move:");
            input = in.nextLine().toUpperCase();
            int idMove = Utility.coordinateToId(input);
            if (!squares[idSelect].getPiece().move(idMove)) {
                Board.updateBoard();
                continue;
            }
            Board.updateBoard();
            turn = !turn;
        }

    }
}
