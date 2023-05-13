import java.util.ArrayList;

public class Board {
    private int ROWS = 8;
    private int COLS = 8;
    private Cell[][] b;
    private Window w;
    public Board() {
        // Creates board in back end
        b = new Cell[ROWS][COLS];
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                b[i][j] = new Cell(i, j, this);
            }
        }
        initBoard();
        w = new Window(this);
    }

    public Cell getCell(int row, int col) {
        return b[row][col];
    }

    // Initialized board with pieces
    public void initBoard() {
        //set piece locations
        for (int i = 0; i < 8; i++) {
            b[1][i].setPiece(new Pawn(1, i, true));
        }
        b[0][0].setPiece(new Rook(0, 0, true));
        b[0][7].setPiece(new Rook(0, 7, true));
        b[0][1].setPiece(new Knight(0, 1, true));
        b[0][6].setPiece(new Knight(0, 6, true));
        b[0][2].setPiece(new Bishop(0, 2, true));
        b[0][5].setPiece(new Bishop(0, 5, true));
        King white = new King(0, 3, true);
        b[0][3].setPiece(white);
        b[0][4].setPiece(new Queen(0, 4, true));
        for (int i = 0; i < 8; i++) {
            b[6][i].setPiece(new Pawn(6, i, false));
        }
        b[7][0].setPiece(new Rook(7, 0, false));
        b[7][7].setPiece(new Rook(7, 7, false));
        b[7][1].setPiece(new Knight(7, 1, false));
        b[7][6].setPiece(new Knight(7, 6, false));
        b[7][2].setPiece(new Bishop(7, 2, false));
        b[7][5].setPiece(new Bishop(7, 5, false));
        King black = new King(7, 3, false);
        b[7][3].setPiece(black);
        b[7][4].setPiece(new Queen(7, 4, false));
    }

    // Get white king
    public King getWhiteKing() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Piece p = b[i][j].getPiece();
                if (p instanceof King && p.isWhite()) {
                    return (King) p;
                }
            }
        }
        return null;
    }

    // Get black king
    public King getBlackKing() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Piece p = b[i][j].getPiece();
                if (p instanceof King && !p.isWhite()) {
                    return (King) p;
                }
            }
        }
        return null;
    }

    // Array list of attacking pieces for check
    public ArrayList<Piece> getAttackingPieces(int row, int col) {
        ArrayList<Piece> attackingPieces = new ArrayList<Piece>();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Piece p = b[i][j].getPiece();
                if (p != null && p.isValidMove(row, col, this)) {
                    attackingPieces.add(p);
                }
            }
        }
        return attackingPieces;
    }

    // Also couldn't implement
    public void reset() {
        Board d = new Board();
    }

    public Cell[][] getBoard() {
        return this.b;
    }


}
