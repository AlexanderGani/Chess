import java.awt.*;

public class King extends Piece {
    private Image wking, bking;
    private int x, y;
    private boolean isWhite;
    private boolean isEaten;
    public King(int x, int y, boolean isWhite) {
        super(x, y, isWhite);
        this.isWhite = isWhite;
        this.x = x;
        this.y = y;
        this.isEaten = false;
    }
    public void move(int row, int col, Game l) {
        //remove piece from original location, update variables, see if eats king to return win, otherwise eat piece,
        // then set location
        Cell[][] board = l.getBoard();
        board[x][y].removePiece();
        x = row;
        y = col;
        if (board[x][y].getPiece() != null) {
            if(board[x][y].getPiece() instanceof King) {
                board[x][y].getPiece().setEaten(true);
            }
            board[x][y].removePiece();
        }
        board[row][col].setPiece(this);
    }
    public void setEaten(boolean s) {
        isEaten = s;
    }
    public boolean isWhite() {
        return isWhite;
    }
    public boolean isValidMove(int row, int col, Game l) {
        //valid moves - 1 in every direction
        if (row == x + 1 || row == x - 1 || col == y + 1 || col == y - 1 || (row == x + 1 && col == y + 1) ||
                (row == x + 1 && col == y - 1) || (row == x - 1 && col == y + 1) || (row == x - 1 && col == y - 1)) {
            return true;
        }
        return false;
    }
    public boolean isEaten() {
        return isEaten;
    }
    public void draw(Graphics g, window w) {
        wking = w.getKing()[0];
        bking = w.getKing()[1];
        if (isWhite) {
            g.drawImage(wking, 25 + (90 * y), 50 + (90 * x), 90, 90, w);
        }
        else {
            g.drawImage(bking, 25 + (90 * y), 50 + (90 * x), 90, 90, w);
        }
    }
}
