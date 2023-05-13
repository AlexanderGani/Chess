import java.awt.*;

public class Pawn extends Piece {
    private Image wpawn, bpawn;
    private int x, y;
    private int lX, lY;
    private boolean isWhite;
    private boolean isFirst;
    private Piece old;
    public Pawn(int x, int y, boolean isWhite) {
        super(x, y, isWhite);
        this.isWhite = isWhite;
        this.x = x;
        this.y = y;
        isFirst = true;
    }
    public void move(int row, int col, Board b) {
        //remove piece from original location, update variables, see if eats king to return win, otherwise eat piece,
        // then set location
        Cell[][] board = b.getBoard();
        old = null;
        lX = x;
        lY = y;
        board[x][y].removePiece();
        x = row;
        y = col;
        if (board[x][y].getPiece() != null) {
            if (board[x][y].getPiece() instanceof King) {
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
        if (old != null) {
            board[old.getX()][old.getY()].setPiece(old);
        }
        board[x][y].setPiece(this);
    }
    public boolean isValidMove(int row, int col, Board b) {
        // See valid moves - basically only up 1
        // Get board
        Cell[][] board = b.getBoard();
        // Make sure move is in bounds
        if (row > 8 || row < 0) {
            return false;
        }
        if (col > 8 || col < 0) {
            return false;
        }
        // Make sure can't move backwards
        if (isWhite) {
            if (row - x < 0) {
                return false;
            }
        }
        else if(row - x > 0) {
            return false;
        }
        // Make sure that pawn can't eat piece in front of it, also only mvoes once
        if (Math.abs(row - x) == 1 && col == y && board[row][col].getPiece() == null) {
            return true;
        }
        // Can eat diagonal if is opposite color
        if (Math.abs(col - y) == 1 && Math.abs(row - x) == 1 && board[row][col].getPiece() != null &&
                board[row][col].getPiece().isWhite() != isWhite) {
            return true;
        }
        // Can move two squares first move
        if (isFirst) {
            if (isWhite) {
                if(row - x == 2 && col == y && board[row][col].getPiece() == null) {
                    isFirst = false;
                    return true;
                }
            }
            else {
                if (row - x == -2 && col == y && board[row][col].getPiece() == null) {
                    isFirst = false;
                    return true;
                }
            }
        }
        return false;
    }
    public boolean isWhite() {
        return isWhite;
    }
    public void promote(Board b, int row, int col) {
        Cell[][] board = b.getBoard();
        board[x][y].removePiece();
        board[row][col].setPiece(new Queen(row, col, isWhite));
    }
    public void draw(Graphics g, Window w) {
        wpawn = w.getPawn()[0];
        bpawn = w.getPawn()[1];
        if (isWhite) {
            g.drawImage(wpawn, (90 * y), (90 * x) + 20, 90, 90, w);
        }
        else {
            g.drawImage(bpawn, (90 * y), (90 * x) + 20, 90, 90, w);
        }
    }

}
