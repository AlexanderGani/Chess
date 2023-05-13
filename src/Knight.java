import java.awt.*;
import java.util.ArrayList;

public class Knight extends Piece {
    private Image wknight, bknight;
    private int x, y;
    private int lX, lY;
    private boolean isWhite;
    private Piece old;

    public Knight(int x, int y, boolean isWhite) {
        super(x, y, isWhite);
        this.isWhite = isWhite;
        this.x = x;
        this.y = y;
    }
    public void move(int row, int col, Board b) {
        //remove piece from original location, update variables, see if eats king to return win, otherwise eat piece,
        // then set location
        Cell[][] board = b.getBoard();
        // Find old position
        old = null;
        lX = x;
        lY = y;
        board[x][y].removePiece();
        x = row;
        y = col;
        if (board[x][y].getPiece() != null) {
            if(board[x][y].getPiece() instanceof King) {
                board[x][y].getPiece().setEaten(true);
            }
            old = board[row][col].getPiece();
            board[x][y].removePiece();
        }
        board[row][col].setPiece(this);
    }
    public void undoMove(Board b) {
        Cell[][] board = b.getBoard();
        board[x][y].removePiece();
        x = lX;
        y = lY;
        if(old != null) {
            board[old.getX()][old.getY()].setPiece(old);
        }
        board[x][y].setPiece(this);
    }
    public boolean isValidMove(int row, int col, Board b) {
        // Get board
        Cell[][] board = b.getBoard();
        if (row > 8 || row < 0){
            return false;
        }
        if (col > 8 || col < 0) {
            return false;
        }
        // Get piece
        Piece p = board[row][col].getPiece();
        // Check for L shape move
        if ((Math.abs(row - x) == 2 && Math.abs(col - y) == 1) || (Math.abs(row - x) == 1 && Math.abs(col - y) == 2)) {
            return true;
        }
        // Make sure isn't eating own color pieces
        if (p != null) {
            if (isWhite && p.isWhite()) {
                return false;
            }
            else if (!isWhite && !p.isWhite()) {
                return false;
            }
        }
        return false;
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
        wknight = w.getKnight()[0];
        bknight = w.getKnight()[1];
        if (isWhite) {
            g.drawImage(wknight, (90 * y), (90 * x) + 20, 90, 90, w);
        }
        else {
            g.drawImage(bknight, (90 * y), (90 * x) + 20, 90, 90, w);
        }
    }
}
