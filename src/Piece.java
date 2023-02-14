import java.awt.*;

public class Piece {
    //abstract class - never used
    private int x, y;
    private boolean isWhite;
    private boolean isEaten;

    public Piece(int x, int y, boolean isWhite) {
        this.isWhite = isWhite;
        this.x = x;
        this.y = y;
    }
    public void move(int row, int col, Game l) {
        Cell[][] board = l.getBoard();
        board[row][col].setPiece(this);
        board[x][y].removePiece();
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
    public boolean isValidMove(int row, int col) {
        return true;
    }
    public void draw(Graphics g, window w) {
    }
}
