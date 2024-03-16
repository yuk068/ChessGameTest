package hus.TestZone.ChessGameTest.TheGame.Board;

import hus.TestZone.ChessGameTest.TheGame.Gameplay.Piece.Piece;
import hus.TestZone.ChessGameTest.TheGame.Utility;

public class Square {

    private Piece piece;
    private boolean isOccupied;
    private int id;
    private boolean darkLight;

    public Square(Piece piece, int id) {
        this.piece = piece;
        this.id = id;
        this.darkLight = calculateDarkLight(id);
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public boolean isDarkLight() {
        return darkLight;
    }

    public boolean isOccupied() {
        return piece != null;
    }

    public void setDarkLight(boolean darkLight) {
        this.darkLight = darkLight;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isOccupiedByAlly(boolean blackWhite) {
        return blackWhite ? piece != null && piece.isBlackWhite() : piece != null && !piece.isBlackWhite();
    }

    public boolean isProtected(int id, Square[] squares) {
        for (Square square : squares) {
            if (square.getPiece() == null) continue;
            int[] protectingSight = square.getPiece().getSightProtecting();
            if (Utility.containsElement(protectingSight, id)){
                return true;
            }
        }
        return false;
    }

    private boolean calculateDarkLight(int id) {
        int row = id / Board.SIZE;
        int col = id % Board.SIZE;
        return (row + col) % 2 == 0;
    }

    @Override
    public String toString() {
        if (piece != null) {
            return (darkLight ? "Dark " : "Light ") + "square: [" + Utility.idToCoordinate(id) + "] "
                    + (piece.isBlackWhite() ? "Black " : "White ")
                    + piece.getClass().getSimpleName().toLowerCase();
        } else {
            return (darkLight ? "Dark " : "Light ") + "square: [" + Utility.idToCoordinate(id) + "]";
        }
    }

}
