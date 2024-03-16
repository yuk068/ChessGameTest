package hus.TestZone.ChessGameTest.TheGame.Gameplay.Piece;

import hus.TestZone.ChessGameTest.TheGame.Board.Board;
import hus.TestZone.ChessGameTest.TheGame.Board.Square;
import hus.TestZone.ChessGameTest.TheGame.Utility;

import java.util.Arrays;

public class Bishop extends Piece {

    public Bishop(int squareId, boolean blackWhite) {
        super(squareId, blackWhite);
    }

    @Override
    public boolean move(int id) {
        int[] legalMoves = getLegalMoves();

        boolean isValidMove = false;
        for (int legalMove : legalMoves) {
            if (legalMove == id) {
                isValidMove = true;
                break;
            }
        }
        if (isValidMove) {
            Square currentSquare = Board.getSquares()[getSquareId()];
            currentSquare.setPiece(null);
            setSquareId(id);
            Square newSquare = Board.getSquares()[id];
            newSquare.setPiece(this);
            return true;
        } else {
            System.out.println("Invalid move. Please choose a valid square.");
            return false;
        }
    }

    @Override
    public int[] getLegalMoves() {
        int[] bishopMoves = new int[13];
        int count = 0;

        int currentSquareId = getSquareId();

        int row = currentSquareId / Board.SIZE;
        int col = currentSquareId % Board.SIZE;

        for (int i = -Board.SIZE; i <= Board.SIZE; i++) {
            for (int j = -Board.SIZE; j <= Board.SIZE; j++) {
                if (i == 0 && j == 0) continue;
                if (Math.abs(i) == Math.abs(j)) {
                    int newRow = row + i;
                    int newCol = col + j;
                    if (isValidSquare(newRow, newCol) && !isPhasing(Utility.rowColToId(newRow, newCol, Board.SIZE))) {
                        bishopMoves[count++] = Utility.rowColToId(newRow, newCol, Board.SIZE);
                    }
                }
            }
        }

        return Arrays.copyOf(bishopMoves, count);
    }

    @Override
    public boolean isPhasing(int id) {
        int[] invalid = Utility.mergeArrays(invalidProtectingUpRight(true), invalidProtectingUpLeft(true), invalidProtectingDownLeft(true), invalidProtectingDownRight(true));
        return Utility.containsElement(invalid, id);
    }

    @Override
    public int[] getSightProtecting() {
        return Utility.mergeArrays(invalidProtectingUpRight(false), invalidProtectingUpLeft(false), invalidProtectingDownLeft(false), invalidProtectingDownRight(false));
    }
}
