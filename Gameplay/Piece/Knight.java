package hus.TestZone.ChessGameTest.TheGame.Gameplay.Piece;

import hus.TestZone.ChessGameTest.TheGame.Board.Board;
import hus.TestZone.ChessGameTest.TheGame.Board.Square;
import hus.TestZone.ChessGameTest.TheGame.Utility;

import java.util.Arrays;

public class Knight extends Piece {

    public Knight(int squareId, boolean blackWhite) {
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
        int[] knightMoves = new int[8];
        int count = 0;

        int currentSquareId = getSquareId();

        int row = currentSquareId / Board.SIZE;
        int col = currentSquareId % Board.SIZE;

        int[][] offsets = {{-2, -1}, {-2, 1}, {2, -1}, {2, 1}, {-1, -2}, {-1, 2}, {1, -2}, {1, 2}};

        for (int[] offset : offsets) {
            int newRow = row + offset[0];
            int newCol = col + offset[1];
            if (isValidSquare(newRow, newCol) && !isPhasing(Utility.rowColToId(newRow, newCol, Board.SIZE))) {
                knightMoves[count++] = Utility.rowColToId(newRow, newCol, Board.SIZE);
            }
        }

        return Arrays.copyOf(knightMoves, count);
    }

    @Override
    //Modified for knight's behavior
    public boolean isPhasing(int id) {
        if (Board.getSquares()[id].isOccupied()) {
            return Board.getSquares()[id].isOccupiedByAlly(isBlackWhite());
        }
        return false;
    }

    @Override
    public int[] getSightProtecting() {
        int[] protecting = new int[8];
        int count = 0;

        int currentSquareId = getSquareId();

        int row = currentSquareId / Board.SIZE;
        int col = currentSquareId % Board.SIZE;

        int[][] offsets = {{-2, -1}, {-2, 1}, {2, -1}, {2, 1}, {-1, -2}, {-1, 2}, {1, -2}, {1, 2}};

        for (int[] offset : offsets) {
            int newRow = row + offset[0];
            int newCol = col + offset[1];
            if (isValidSquare(newRow, newCol) && Board.getSquares()[Utility.rowColToId(newRow, newCol, Board.SIZE)].isOccupiedByAlly(isBlackWhite())) {
                protecting[count++] = Utility.rowColToId(newRow, newCol, Board.SIZE);
            }
        }

        return Arrays.copyOf(protecting, count);
    }
}

