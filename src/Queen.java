import java.awt.*;

public class Queen extends Piece {
    private Image wqueen, bqueen;
    private int x, y;
    private boolean isWhite;
    public Queen( int x, int y, boolean isWhite) {
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
    public boolean isWhite() {
        return isWhite;
    }

    public void draw(Graphics g, window w) {
        wqueen = w.getQueen()[0];
        bqueen = w.getQueen()[1];
        //draw white or black
        if (isWhite) {
            g.drawImage(wqueen, 25 + (90 * y), 50 + (90 * x), 90, 90, w);
        }
        else {
            g.drawImage(bqueen, 25 + (90 * y), 50 + (90 * x), 90, 90, w);
        }
    }
}
