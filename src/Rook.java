import java.awt.*;

public class Rook extends Piece {
    private Image wrook, brook;
    private int x, y;
    private int lX, lY;
    private boolean isWhite;
    private boolean isFirst;
    private Piece old;
    public Rook(int x, int y, boolean isWhite) {
        super(x, y, isWhite);
        this.isWhite = isWhite;
        this.x = x;
        this.y = y;
        isFirst = true;
    }
    public void move(int row, int col, Board b) {
        // Remove piece from original location, update variables, see if eats king to return win, otherwise eat piece,
        // Then set location
        Cell[][] board = b.getBoard();
        isFirst = false;
        old = null;
        // Old coords
        lX = x;
        lY = y;
        // Remove piece from old pos
        board[x][y].removePiece();
        x = row;
        y = col;
        if (board[x][y].getPiece() != null) {
            if(board[x][y].getPiece() instanceof King) {
                board[x][y].getPiece().setEaten(true);
            }
            // set old piece
            old = board[row][col].getPiece();
            board[x][y].removePiece();
        }
        // update on front end + back end
        board[row][col].setPiece(this);
    }
    // Undoes moves
    public void undoMove(Board b) {
        // Get board
        Cell[][] board = b.getBoard();
        // undoes piece pos
        board[x][y].removePiece();
        // resets coords
        x = lX;
        y = lY;
        // resets eaten piece
        if(old != null) {
            board[old.getX()][old.getY()].setPiece(old);
        }
        board[x][y].setPiece(this);
    }
    // Checks if move is legal
    public boolean isValidMove(int row, int col, Board b) {
        // Get board
        Cell[][] board = b.getBoard();
        // Make sure move isn't out of bounds
        if (row > 8 || row < 0){
            return false;
        }
        if (col > 8 || col < 0) {
            return false;
        }
        // Get piece
        Piece p = board[row][col].getPiece();
        // Check for vertical move
        if (row == x || col == y) {
            // Same thing we did with bishop, but this time we have to increment because vertical/horizontal movement
            int nX = Integer.compare(row, x);
            int nY = Integer.compare(col, y);
            int dX = nX + x;
            int dY = nY + y;
            // While loop to iterate over so it can't jump over pieces
            while (dX != row || dY != col) {
                if (board[dX][dY].getPiece() != null) {
                    return false;
                }
                dX += nX;
                dY += nY;
            }
            // Can't eat piece of same color
            if (p == null || p.isWhite() != isWhite) {
                return true;
            }
        }
        return false;
    }
    public boolean isWhite() {
        return isWhite;
    }
    public boolean isFirst() {
        return isFirst;
    }

    public void draw(Graphics g, Window w) {
        wrook = w.getRook()[0];
        brook = w.getRook()[1];
        if (isWhite) {
            g.drawImage(wrook, (90 * y), (90 * x) + 20, 90, 90, w);
        }
        else {
            g.drawImage(brook, (90 * y), (90 * x) + 20, 90, 90, w);
        }
    }
}
