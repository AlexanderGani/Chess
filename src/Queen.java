import java.awt.*;

public class Queen extends Piece {
    private Image wqueen, bqueen;
    private int x, y;
    private int lX, lY;
    private boolean isWhite;
    private Piece old;
    public Queen( int x, int y, boolean isWhite) {
        super(x, y, isWhite);
        this.isWhite = isWhite;
        this.x = x;
        this.y = y;
    }
    // Moves piece
    public void move(int row, int col, Board b) {
        //remove piece from original location, update variables, see if eats king to return win, otherwise eat piece,
        // then set location
        Cell[][] board = b.getBoard();
        old = null;
        // Set old coords
        lX = x;
        lY = y;
        // remove piece from old
        board[x][y].removePiece();
        // Update coords
        x = row;
        y = col;
        if (board[x][y].getPiece() != null) {
            if(board[x][y].getPiece() instanceof King) {
                board[x][y].getPiece().setEaten(true);
            }
            // Set old (eaten) piece
            old = board[row][col].getPiece();
            board[x][y].removePiece();
        }
        // Update on front + back end
        board[row][col].setPiece(this);
    }
    // Undoes move
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
    // Check if legal move
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
        // Check vertical/horizontal move
        if (row == x || col == y) {
            // Same thing as rook
            int nX = Integer.compare(row, x);
            int nY = Integer.compare(col, y);
            int dX = nX + x;
            int dY = nY + y;
            while (dX != row || dY != col) {
                // Make sure can't move over pieces
                if (board[dX][dY].getPiece() != null) {
                    return false;
                }
                dX += nX;
                dY += nY;
            }
            // Can't eat piece of same color
            if (p == null || isWhite != p.isWhite()) {
                return true;
            }
            return false;
        }
        // if it isn't moving diagonally return false;
        else if(Math.abs(row - x) != Math.abs(col - y)) {
            return false;
        }
        else {
            // Same method as bishop
            for (int i = 1; i < Math.abs(row - x); i++) {
                // Because compare returns > 0 if row/col is greater than x/y and < 0 if less than, we are able to modify this
                int r = Integer.compare(row, x);
                int c = Integer.compare(col, y);
                if (board[x + i * r][y + i * c].getPiece() != null) {
                    return false;
                }
            }
            // Prevent the Bishop from eating its own color piece
            if (board[row][col].getPiece() != null) {
                if (isWhite && p.isWhite()) {
                    return false;
                }
                if (!isWhite && !p.isWhite()) {
                    return false;
                }
            }
        }
        return true;
    }
    public boolean isWhite() {
        return isWhite;
    }

    public void draw(Graphics g, Window w) {
        wqueen = w.getQueen()[0];
        bqueen = w.getQueen()[1];
        //draw white or black
        if (isWhite) {
            g.drawImage(wqueen, (90 * y),  (90 * x) + 20, 90, 90, w);
        }
        else {
            g.drawImage(bqueen,(90 * y), (90 * x) + 20, 90, 90, w);
        }
    }
}
