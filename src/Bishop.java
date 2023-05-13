import java.awt.*;

public class Bishop extends Piece {
    private Image wbishop, bbishop;
    private int x, y;
    private int lX, lY;
    private boolean isWhite;
    private Piece old;

    public Bishop(int x, int y, boolean isWhite) {
        super(x, y, isWhite);
        this.isWhite = isWhite;
        this.x = x;
        this.y = y;
    }
    // Move the actual piece
    public void move(int row, int col, Board b) {
        // Get board
        Cell[][] board = b.getBoard();
        old = null;
        // Remove piece from current pos
        lX = x;
        lY = y;
        board[x][y].removePiece();
        // New pos
        x = row;
        y = col;
        // If piece is in cell, eat it
        if (board[x][y].getPiece() != null) {
            if(board[x][y].getPiece() instanceof King) {
                board[x][y].getPiece().setEaten(true);
            }
            old = board[row][col].getPiece();
            board[x][y].removePiece();
        }
        // Move piece
        board[row][col].setPiece(this);
    }

    public void undoMove(Board b) {
        // Get board
        Cell[][] board = b.getBoard();
        // Remove piece from the current move
        board[x][y].removePiece();
        // undo the move
        x = lX;
        y = lY;
        // if this piece ate another one, restore the other piece
        if(old != null) {
            board[old.getX()][old.getY()].setPiece(old);
        }
        board[x][y].setPiece(this);
    }

    // Make sure the move of the bishop is legal
    public boolean isValidMove(int row, int col, Board b) {
        // Get board
        Cell[][] board = b.getBoard();
        // get Piece at pos or null
        Piece p = board[row][col].getPiece();
        // Make sure that the bishop is moving diagonally, absolute value if one results in a - and the other +
        if (Math.abs(row - x) != Math.abs(col - y)) {
            return false;
        }
        // Check to make sure the bishop is not moving over pieces - Math.abs because negative val results in error
        for (int i = 1; i < Math.abs(row - x); i++) {
            // Because compare returns > 0 if row/col is greater than x/y and < 0 if less than, we are able to modify this
            int r = Integer.compare(row, x);
            int c = Integer.compare(col, y);
            if (board[x + i * r][y + i * c].getPiece() != null) {
                return false;
            }
        }
        // Prevent the Bishop from eating its own color piece
        if (p != null) {
            if (isWhite && p.isWhite()) {
                return false;
            }
            if (!isWhite && !p.isWhite()) {
                return false;
            }
        }
        return Math.abs(row - x) <= 7 && Math.abs(col - y) <= 7;
    }
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isWhite() {
        return isWhite;
    }
    public void draw(Graphics g, Window w) {
        wbishop = w.getBishop()[0];
        bbishop = w.getBishop()[1];
        if(isWhite) {
            g.drawImage(wbishop, (90 * y), (90 * x) + 20, 90, 90, w);
        }
        else {
            g.drawImage(bbishop, (90 * y), (90 * x) + 20, 90, 90, w);
        }
    }

}
