package hus.TestZone.ChessGameTest.TheGame.Gameplay.Piece;

import hus.TestZone.ChessGameTest.TheGame.Board.Board;
import hus.TestZone.ChessGameTest.TheGame.Utility;

import java.util.Arrays;

public abstract class Piece {

    private int squareId;
    private boolean blackWhite;

    public Piece(int squareId, boolean blackWhite) {
        this.squareId = squareId;
        this.blackWhite = blackWhite;
    }

    //region Getters and Setters
    public int getSquareId() {
        return squareId;
    }

    public void setSquareId(int squareId) {
        this.squareId = squareId;
    }

    public boolean isBlackWhite() {
        return blackWhite;
    }

    public void setBlackWhite(boolean blackWhite) {
        this.blackWhite = blackWhite;
    }
    //endregion

    public abstract boolean move(int id);

    public abstract int[] getLegalMoves();

    public abstract boolean isPhasing(int id);

    public abstract int[] getSightProtecting();

    public boolean isValidSquare(int row, int col) {
        return row >= 0 && row < Board.SIZE && col >= 0 && col < Board.SIZE;
    }

    public boolean isValidId(int id) {
        return id >= 0 && id <= 63;
    }

    //region Get array of invalid ids for 8 directions
    //I don't remember directions, so I use up down left right
    public int[] checkInvalid(int tolerance, int maxCheck, int checkId) {
        int[] invalid = new int[7];
        int count = 0;
        int index = 0;
        while (isValidId(checkId) && count < maxCheck) {
            if (Board.getSquares()[checkId].isOccupiedByAlly(blackWhite) && Board.getSquares()[checkId].isOccupied()) {
                while (isValidId(checkId) && count < maxCheck) {
                    invalid[index++] = checkId;
                    checkId += tolerance;
                    count++;
                }
                return Arrays.copyOf(invalid, index);
            } else if (!Board.getSquares()[checkId].isOccupiedByAlly(blackWhite) && Board.getSquares()[checkId].isOccupied()) {
                checkId += tolerance;
                while (isValidId(checkId) && count < maxCheck) {
                    invalid[index++] = checkId;
                    checkId += tolerance;
                    count++;
                }
                return Arrays.copyOf(invalid, index);
            }
            checkId += tolerance;
            count++;
        }
        return null;
    }

    public int checkProtecting(int tolerance, int maxCheck, int checkId) {
        int protecting;
        int count = 0;
        while (isValidId(checkId) && count < maxCheck) {
            if (Board.getSquares()[checkId].isOccupiedByAlly(blackWhite) && Board.getSquares()[checkId].isOccupied()) {
                protecting = checkId;
                return protecting;
            }
            checkId += tolerance;
            count++;
        }
        return -1;
    }

    public int[] invalidProtectingUp(boolean option) {
        int tolerance = 8;
        int row = Utility.idToRowCol(squareId, Board.SIZE)[0];
        int maxCheck = 7 - row;
        int checkId = squareId + tolerance;
        if (option) {
            return checkInvalid(tolerance, maxCheck, checkId);
        }
        return new int[]{checkProtecting(tolerance, maxCheck, checkId)};
    }

    public int[] invalidProtectingDown(boolean option) {
        int tolerance = -8;
        int maxCheck = Utility.idToRowCol(squareId, Board.SIZE)[0];
        int checkId = squareId + tolerance;
        if (option) {
            return checkInvalid(tolerance, maxCheck, checkId);
        }
        return new int[]{checkProtecting(tolerance, maxCheck, checkId)};
    }

    public int[] invalidProtectingRight(boolean option) {
        int tolerance = 1;
        int col = Utility.idToRowCol(squareId, Board.SIZE)[1];
        int maxCheck = 7 - col;
        int checkId = squareId + tolerance;
        if (option) {
            return checkInvalid(tolerance, maxCheck, checkId);
        }
        return new int[]{checkProtecting(tolerance, maxCheck, checkId)};
    }

    public int[] invalidProtectingLeft(boolean option) {
        int tolerance = -1;
        int maxCheck = Utility.idToRowCol(squareId, Board.SIZE)[1];
        int checkId = squareId + tolerance;
        if (option) {
            return checkInvalid(tolerance, maxCheck, checkId);
        }
        return new int[]{checkProtecting(tolerance, maxCheck, checkId)};
    }

    public int[] invalidProtectingUpRight(boolean option) {
        int tolerance = 9;
        int row = Utility.idToRowCol(squareId, Board.SIZE)[0];
        int col = Utility.idToRowCol(squareId, Board.SIZE)[1];
        int maxCheck = Math.min(7 - col, 7 - row);
        int checkId = squareId + tolerance;
        if (option) {
            return checkInvalid(tolerance, maxCheck, checkId);
        }
        return new int[]{checkProtecting(tolerance, maxCheck, checkId)};
    }

    public int[] invalidProtectingUpLeft(boolean option) {
        int tolerance = 7;
        int row = Utility.idToRowCol(squareId, Board.SIZE)[0];
        int col = Utility.idToRowCol(squareId, Board.SIZE)[1];
        int maxCheck = Math.min(col, 7 - row);
        int checkId = squareId + tolerance;
        if (option) {
            return checkInvalid(tolerance, maxCheck, checkId);
        }
        return new int[]{checkProtecting(tolerance, maxCheck, checkId)};
    }

    public int[] invalidProtectingDownRight(boolean option) {
        int tolerance = -7;
        int row = Utility.idToRowCol(squareId, Board.SIZE)[0];
        int col = Utility.idToRowCol(squareId, Board.SIZE)[1];
        int maxCheck = Math.min(7 + col, row);
        int checkId = squareId + tolerance;
        if (option) {
            return checkInvalid(tolerance, maxCheck, checkId);
        }
        return new int[]{checkProtecting(tolerance, maxCheck, checkId)};
    }

    public int[] invalidProtectingDownLeft(boolean option) {
        int tolerance = -9;
        int row = Utility.idToRowCol(squareId, Board.SIZE)[0];
        int col = Utility.idToRowCol(squareId, Board.SIZE)[1];
        int maxCheck = Math.min(col, row);
        int checkId = squareId + tolerance;
        if (option) {
            return checkInvalid(tolerance, maxCheck, checkId);
        }
        return new int[]{checkProtecting(tolerance, maxCheck, checkId)};
    }
//endregion

}
