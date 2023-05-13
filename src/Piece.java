import java.awt.*;

public class Piece {
    //abstract class - never used
    private int x, y;
    private int lX, lY;
    private boolean isWhite;
    private boolean isEaten;

    public Piece(int x, int y, boolean isWhite) {
        this.isWhite = isWhite;
        this.x = x;
        this.y = y;
    }
    public void move(int nX, int nY, Board b) {
    }
    public void undoMove(Board b) {
        Cell[][] board = b.getBoard();
        board[x][y].removePiece();
        x = lX;
        y = lY;
        board[x][y].setPiece(this);
    }
    public void setEaten(boolean s) {
        isEaten = s;
    }
    public boolean isEaten() {
        return false;
    }
    public boolean isWhite() {
        return isWhite;
    }
    public boolean isValidMove(int row, int col, Board b) {
        return true;
    }
    public void promote(Board b, int row, int col) {
    }
    public boolean isFirst() {
        return true;
    }
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isChecked(Game l) {
        return false;
    }
    public void draw(Graphics g, Window w) {
    }
}
