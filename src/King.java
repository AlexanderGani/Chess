import java.awt.*;

public class King extends Piece {
    private Image wking, bking;
    private int x, y;
    private int lX, lY;
    private boolean isWhite;
    private boolean isEaten;
    private boolean canCastle;
    private Piece old;

    public King(int x, int y, boolean isWhite) {
        super(x, y, isWhite);
        this.isWhite = isWhite;
        this.x = x;
        this.y = y;
        this.isEaten = false;
        canCastle = true;
    }
    // Moves the actual piece
    public void move(int row, int col, Board b) {
        //remove piece from original location, update variables, see if eats king to return win, otherwise eat piece,
        // then set location
        Cell[][] board = b.getBoard();
        // Old piece
        old = null;
        // Old location
        lX = x;
        lY = y;
        // Remove piece
        board[x][y].removePiece();
        // Update coords
        x = row;
        y = col;
        if (board[x][y].getPiece() != null) {
            if (board[x][y].getPiece() instanceof King) {
                board[x][y].getPiece().setEaten(true);
            }
            // set Old piece
            old = board[row][col].getPiece();
            board[x][y].removePiece();
        }
        board[row][col].setPiece(this);
    }
    // Undoes a move if it reveals a check or does not block a check
    public void undoMove(Board b) {
        // Get board
        Cell[][] board = b.getBoard();
        // undo the move
        board[x][y].removePiece();
        // restore old coords
        x = lX;
        y = lY;
        if (old != null) {
            board[old.getX()][old.getY()].setPiece(old);
        }
        board[x][y].setPiece(this);
    }
    public void setEaten(boolean s) {
        isEaten = s;
    }

    public boolean isWhite() {
        return isWhite;
    }
    // Finds valid moves for King
    public boolean isValidMove(int row, int col, Board b) {
        // Get board
        Cell[][] board = b.getBoard();
        // Get piece
        if (row > 8 || row < 0){
            return false;
        }
        if (col > 8 || col < 0) {
            return false;
        }
        Piece p = board[row][col].getPiece();
        //valid moves - 1 in every direction, Math.abs to prevent error from negative
        if (Math.abs(row - x) > 1 && Math.abs(col - y) > 1 || Math.abs(col - y) > 1 || Math.abs(row - x) > 1) {
            return false;
        }
        // Prevent the King from eating its own color piece
        if (p != null) {
            if (isWhite && p.isWhite()) {
                return false;
            }
            if (!isWhite && !p.isWhite()) {
                return false;
            }
        }
        // Make sure move is in bounds
        return true;
    }

    public boolean isEaten() {
        return isEaten;
    }

    // Find if the king is checked on the board
    public boolean isChecked(Board b) {
        // Init king pos
        int kingX = -1;
        int kingY = -1;
        // Traverse board
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    //Find White King
                    Piece piece = b.getBoard()[i][j].getPiece();
                    if (piece != null && piece instanceof King && piece.isWhite() == isWhite) {
                        kingX = i;
                        kingY = j;
                        break;
                    }
                }
                // If White king is found break
                if (kingX != -1 && kingY != -1) {
                    break;
                }
            }
            // If King is not found return false
            if (kingX == -1 || kingY == -1) {
                return false;
            }
            // Traverse board to see if piece can move to king, meaning it is in check
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    Piece piece = b.getBoard()[i][j].getPiece();
                    if (piece != null && piece.isWhite() != isWhite && piece.isValidMove(kingX, kingY, b)) {
                        return true;
                    }
                }
            }
            return false;
    }
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    public void draw(Graphics g, Window w) {
        wking = w.getKing()[0];
        bking = w.getKing()[1];
        if (isWhite) {
            g.drawImage(wking,  (90 * y), (90 * x) + 20, 90, 90, w);
        }
        else {
            g.drawImage(bking, (90 * y), (90 * x) + 20, 90, 90, w);
        }
    }
}
