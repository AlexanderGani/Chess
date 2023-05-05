import java.awt.*;

public class Pawn extends Piece {
    private Image wpawn, bpawn;
    private int x, y;
    private boolean isWhite;
    public Pawn(int x, int y, boolean isWhite) {
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
            if (board[x][y].getPiece() instanceof King) {
                board[x][y].getPiece().setEaten(true);
            }
            board[x][y].removePiece();
        }
        board[row][col].setPiece(this);
    }
    public boolean isValidMove(int row, int col, Game l) {
        //see valid moves - basically only up 1
        if (isWhite) {
            //doesn't work yet but to prevent pawns from eating piece in front of them
            if ((row == x + 1 && col == y && l.getBoard()[y][x + 1].getPiece() == null) ||
                    (row == x + 1 && col == y + 1 && l.getBoard()[y + 1][x + 1].getPiece() != null)
                    || (row == x + 1 && col == y - 1 && l.getBoard()[y - 1][x + 1] != null)) {
                return true;
            }
            return false;
        }
        //for black pieces
        else {
            if ((row == x - 1 && col == y && l.getBoard()[y][x - 1].getPiece() == null) ||
                    (row == x - 1 && col == y + 1 && l.getBoard()[y + 1][x - 1] .getPiece() != null)
                    || (row == x - 1 && col == y - 1 && l.getBoard()[y - 1][x - 1] != null)) {
                return true;
            }
            return false;
        }
    }
    public boolean isWhite() {
        return isWhite;
    }
    public void draw(Graphics g, window w) {
        wpawn = w.getPawn()[0];
        bpawn = w.getPawn()[1];
        if (isWhite) {
            g.drawImage(wpawn, 25 + (90 * y), 50 + (90 * x), 90, 90, w);
        }
        else {
            g.drawImage(bpawn, 25 + (90 * y), 50 + (90 * x), 90, 90, w);
        }
    }

}
