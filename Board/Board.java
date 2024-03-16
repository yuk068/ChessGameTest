package hus.TestZone.ChessGameTest.TheGame.Board;

import hus.TestZone.ChessGameTest.TheGame.Gameplay.Piece.Piece;
import hus.TestZone.ChessGameTest.TheGame.Gameplay.Piece.*;

import java.util.HashMap;

public class Board {

    public static final int SIZE = 8;
    public static final int[] NUMBERS_ROWS = {1, 2, 3, 4, 5, 6, 7, 8};
    public static final char[] LETTERS_COLS = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H'};
    public static final HashMap<Integer, String> idToCoordinate = new HashMap<>();
    public static final HashMap<String, Integer> coordinateToId = new HashMap<>();

    private static Square[] squares;

    static {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                int id = i * SIZE + j;
                String coordinate = LETTERS_COLS[j] + Integer.toString(NUMBERS_ROWS[i]);
                idToCoordinate.put(id, coordinate);
                coordinateToId.put(coordinate, id);
            }
        }
        initializeBoard();
    }

    public static void initializeBoard() {
        squares = new Square[SIZE * SIZE];

        // White rooks
        squares[0] = new Square(new Rook(0, false), 0);
        squares[7] = new Square(new Rook(7, false), 7);
        // Black rooks
        squares[56] = new Square(new Rook(56, true), 56);
        squares[63] = new Square(new Rook(63, true), 63);
        // White bishops
        squares[2] = new Square(new Bishop(2, false), 2);
        squares[5] = new Square(new Bishop(5, false), 5);
        // Black bishops
        squares[58] = new Square(new Bishop(58, true), 58);
        squares[61] = new Square(new Bishop(61, true), 61);
        // White knights
        squares[1] = new Square(new Knight(1, false), 1);
        squares[6] = new Square(new Knight(6, false), 6);
        // Black knights
        squares[57] = new Square(new Knight(57, true), 57);
        squares[62] = new Square(new Knight(62, true), 62);
        // White queen
        squares[3] = new Square(new Queen(3, false), 3);
        // Black queen
        squares[59] = new Square(new Queen(59, true), 59);
        // White king
        squares[4] = new Square(new King(4, false), 4);
        // Black king
        squares[60] = new Square(new King(60, true), 60);

        // White pawns
        for (int i = 8; i < 16; i++) {
            squares[i] = new Square(new Pawn(i, false), i);
        }
        // Black pawns
        for (int i = 48; i < 56; i++) {
            squares[i] = new Square(new Pawn(i, true), i);
        }

        for (int i = 0; i < SIZE * SIZE; i++) {
            if (squares[i] == null) {
                squares[i] = new Square(null, i);
            }
        }
    }

    public static Square[] getSquares() {
        return squares;
    }

    public static void updateBoard() {
        System.out.print("   ");
        for (char c : LETTERS_COLS) {
            System.out.print(c + "  ");
        }
        System.out.println();

        for (int i = SIZE - 1; i >= 0; i--) {
            System.out.print(NUMBERS_ROWS[i] + "  ");
            for (int j = 0; j < SIZE; j++) {
                String pieceSymbol = getPieceSymbol(squares[i * SIZE + j]);
                System.out.print(pieceSymbol != null ? pieceSymbol + "  " : "   ");
            }
            System.out.println();
        }
    }

    private static String getPieceSymbol(Square square) {
        if (square == null || square.getPiece() == null) {
            return null;
        }
        Piece piece = square.getPiece();
        if (piece instanceof King) {
            return piece.isBlackWhite() ? "%" : "&";
        } else if (piece instanceof Queen) {
            return piece.isBlackWhite() ? "Q" : "W";
        } else if (piece instanceof Bishop) {
            return piece.isBlackWhite() ? "#" : "$";
        } else if (piece instanceof Rook) {
            return piece.isBlackWhite() ? "[" : "]";
        } else if (piece instanceof Knight) {
            return piece.isBlackWhite() ? "K" : "N";
        } else if (piece instanceof Pawn) {
            return piece.isBlackWhite() ? "@" : "O";
        }
        return null;
    }

}

