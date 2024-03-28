package hus.TestZone.ChessGameTest.TheGame.Gameplay.Piece;

import hus.TestZone.ChessGameTest.TheGame.Board.Board;
import hus.TestZone.ChessGameTest.TheGame.Board.Square;

import java.util.Arrays;

public class Pawn extends Piece {


    public Pawn(int squareId, boolean blackWhite) {
        super(squareId, blackWhite);
    }

    public boolean isFirstMove() {
        if (isBlackWhite() && getSquareId() >= 48 && getSquareId() <= 55) {
            return true;
        } else return getSquareId() >= 8 && getSquareId() <= 15;
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
        int[] pawnMoves = new int[4];
        int count = 0;

        int currentSquareId = getSquareId();

        int tolerance = isBlackWhite() ? -8 : 8;
        int[] diagonal = isBlackWhite() ? new int[]{-9, -7} : new int[]{7, 9};

        if (isValidId(currentSquareId + tolerance) && !Board.getSquares()[currentSquareId + tolerance].isOccupied()) {
            pawnMoves[count++] = currentSquareId + tolerance;
            if (isFirstMove() && isValidId(currentSquareId + 2 * tolerance) && !Board.getSquares()[currentSquareId + 2 * tolerance].isOccupied()) {
                pawnMoves[count++] = currentSquareId + 2 * tolerance;
            }
        }
        for (int i = 0; i < 2; i++) {
            if (isValidId(currentSquareId + diagonal[i]) && Board.getSquares()[currentSquareId + diagonal[i]].isOccupied()) {
                if (!Board.getSquares()[currentSquareId + diagonal[i]].isOccupiedByAlly(isBlackWhite())) {
                    pawnMoves[count++] = currentSquareId + diagonal[i];
                }
            }
        }

        return Arrays.copyOf(pawnMoves, count);
    }

    @Override
    public boolean isPhasing(int id) {
        return false;
    }

    @Override
    public int[] getSightProtecting() {
        int[] protecting = new int[4];
        int count = 0;

        int currentSquareId = getSquareId();

        int[] diagonal = isBlackWhite() ? new int[]{-9, -7} : new int[]{7, 9};
        for (int i = 0; i < 2; i++) {
            if (isValidId(currentSquareId + diagonal[i]) && Board.getSquares()[currentSquareId + diagonal[i]].isOccupied()) {
                if (Board.getSquares()[currentSquareId + diagonal[i]].isOccupiedByAlly(isBlackWhite())) {
                    protecting[count++] = currentSquareId + diagonal[i];
                }
            }
        }

        return Arrays.copyOf(protecting, count);
    }

    public int[] getSight() {
        int[] sight = new int[4];
        int count = 0;

        int currentSquareId = getSquareId();

        int[] diagonal = isBlackWhite() ? new int[]{-9, -7} : new int[]{7, 9};
        for (int i = 0; i < 2; i++) {
            if (isValidId(currentSquareId + diagonal[i])) {
                sight[count++] = currentSquareId + diagonal[i];
            }
        }

        return Arrays.copyOf(sight, count);
    }

}
