package hus.TestZone.ChessGameTest.TheGame.Gameplay.Piece;

import hus.TestZone.ChessGameTest.TheGame.Board.Board;
import hus.TestZone.ChessGameTest.TheGame.Board.Square;
import hus.TestZone.ChessGameTest.TheGame.Utility;

import java.util.Arrays;

public class King extends Piece {

    public King(int squareId, boolean blackWhite) {
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

    public int[] getMoves() {
        int[] kingMoves = new int[8];
        int count = 0;

        int currentSquareId = getSquareId();

        int row = currentSquareId / Board.SIZE;
        int col = currentSquareId % Board.SIZE;

        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i == 0 && j == 0) continue;
                int newRow = row + i;
                int newCol = col + j;
                kingMoves[count++] = Utility.rowColToId(newRow, newCol, Board.SIZE);
            }
        }

        return Arrays.copyOf(kingMoves, count);
    }

    public int[] getLegalMoves() {
        int[] kingMoves = getMoves();
        int[] kingLegalMoves = new int[8];
        int count = 0;
        int row;
        int col;
        for (int move : kingMoves) {
            row = Utility.idToRowCol(move, Board.SIZE)[0];
            col = Utility.idToRowCol(move, Board.SIZE)[1];
            if (isValidSquare(row, col) && !isPhasing(move) && !Board.getSquares()[move].isProtected(move, Board.getSquares())
                && !goesIntoCheck(move)) {
                kingLegalMoves[count++] = move;
            }
        }
        return Arrays.copyOf(kingLegalMoves, count);
    }

    @Override
    public boolean isPhasing(int id) {
        int[] invalid = Utility.mergeArrays(invalidProtectingUp(true), invalidProtectingDown(true), invalidProtectingLeft(true), invalidProtectingRight(true),
                invalidProtectingUpRight(true), invalidProtectingUpLeft(true), invalidProtectingDownLeft(true), invalidProtectingDownRight(true));
        return Utility.containsElement(invalid, id);
    }

    @Override
    public int[] getSightProtecting() {
        int[] base = new int[8];
        int count = 0;

        int currentSquareId = getSquareId();

        int row = currentSquareId / Board.SIZE;
        int col = currentSquareId % Board.SIZE;

        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i == 0 && j == 0) continue;
                int newRow = row + i;
                int newCol = col + j;
                if (isValidSquare(newRow, newCol) && !isPhasing(Utility.rowColToId(newRow, newCol, Board.SIZE))) {
                    base[count++] = Utility.rowColToId(newRow, newCol, Board.SIZE);
                }
            }
        }
        int[] protecting = new int[8];
        int index = 0;
        for (int id : base) {
            if (Board.getSquares()[id].isOccupiedByAlly(isBlackWhite())) {
                protecting[index++] = id;
            }
        }
        return Arrays.copyOf(protecting, index);
    }

    public boolean goesIntoCheck(int id) {
        Square[] squares = Board.getSquares();
        for (Square square : squares) {
            if (square.getPiece() == null) continue;
            int[] enemySight = new int[0];
            if (isBlackWhite() != square.getPiece().isBlackWhite()) {
                if (square.getPiece() instanceof King king) {
                    enemySight = king.getMoves();
                } else if (square.getPiece() instanceof Pawn pawn) {
                    enemySight = pawn.getSight();
                } else {
                    enemySight = square.getPiece().getLegalMoves();
                }
            }
            if (Utility.containsElement(enemySight, id)) {
                return true;
            }
        }
        return false;
    }

}