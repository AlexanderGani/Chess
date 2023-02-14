import java.awt.*;

public class Knight extends Piece {
    private Image wknight, bknight;
    private int x, y;
    private boolean isWhite;

    public Knight(int x, int y, boolean isWhite) {
        super(x, y, isWhite);
        this.isWhite = isWhite;
        this.x = x;
        this.y = y;
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
    public boolean isValidMove(int row, int col) {
        //valid moves - L shapes
        if ((row == x + 2 && col == y + 1) || (row == x + 2 && col == y - 1) || (row == x - 2 && col == y + 1)
                || (row == x - 2 && col == y - 1)) {
            return true;
        }
        return false;
    }
    public boolean isWhite() {
        return isWhite;
    }
    public void draw(Graphics g, window w) {
        wknight = w.getKnight()[0];
        bknight = w.getKnight()[1];
        if (isWhite) {
            g.drawImage(wknight, 25 + (100 * y), 50 + (100 * x), 100, 100, w);
        }
        else {
            g.drawImage(bknight, 25  + (100 * y), 50 + (100 * x), 100, 100, w);
        }
    }
}
