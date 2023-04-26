import java.awt.*;

public class Rook extends Piece {
    private Image wrook, brook;
    private int x, y;
    private boolean isWhite;
    public Rook(int x, int y, boolean isWhite) {
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
        wrook = w.getRook()[0];
        brook = w.getRook()[1];
        if (isWhite) {
            g.drawImage(wrook, 25 + (90 * y), 50 + (90 * x), 90, 90, w);
        }
        else {
            g.drawImage(brook, 25 + (90 * y), 50 + (90 * x), 90, 90, w);
        }
    }
}
