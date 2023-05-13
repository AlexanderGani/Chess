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
        //remove piece from original location, update variables, see if eats king to return win, otherwise eat piece,
        // then set location
        Cell[][] board = b.getBoard();
        isFirst = false;
        old = null;
        lX = x;
        lY = y;
        board[x][y].removePiece();
        x = row;
        y = col;
        if (board[x][y].getPiece() != null) {
            if(board[x][y].getPiece() instanceof King) {
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
        if(old != null) {
            board[old.getX()][old.getY()].setPiece(old);
        }
        board[x][y].setPiece(this);
    }
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
        // Check for vertical move
        if (row == x || col == y) {
            // Same thing we did with bishop, but this time we have to increment because vertical/horizontal movement
            int nX = Integer.compare(row, x);
            int nY = Integer.compare(col, y);
            int dX = nX + x;
            int dY = nY + y;
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
    public boolean willBlock(Board board, Piece p, int row, int col) {
        Cell[][] b = board.getBoard();
        //basically we need to get attacking piece x and y and then find if row and col block it
        Piece king;
        if (isWhite) {
            king = board.getWhiteKing();
        }
        else {
            king = board.getBlackKing();
        }
        if (board.getAttackingPieces(king.getX(), king.getY()).size() != 1) {
            return false;
        }
        Piece attacker = board.getAttackingPieces(king.getX(), king.getY()).get(0);
        // if our piece can eat the attacking piece
        if (isValidMove(attacker.getX(), attacker.getY(), board)) {
            return true;
        }
        else if (attacker instanceof Knight) {
            return false;
        }
        else if (attacker instanceof Bishop) {
            for (int i = 1; i < Math.abs(king.getX() - attacker.getX()); i++) {
                // Because compare returns > 0 if row/col is greater than x/y and < 0 if less than, we are able to modify this
                int r = Integer.compare(king.getX(), attacker.getX());
                int c = Integer.compare(king.getY(), attacker.getY());
                if (row == (attacker.getX() + i * r) && col == (attacker.getY() + i * c)) {
                    return true;
                }
            }
        }
        else if (attacker instanceof Rook) {
            int nX = Integer.compare(king.getX(), attacker.getX());
            int nY = Integer.compare(king.getY(), attacker.getY());
            int dX = nX + attacker.getX();
            int dY = nY + attacker.getY();
            while (dX != row || dY != col) {
                if (row == dX && col == dY) {
                    return true;
                }
                dX += nX;
                dY += nY;
            }
        }
        else if (attacker instanceof Queen) {
            if (king.getX() == attacker.getX() || king.getY() == attacker.getY()) {
                int nX = Integer.compare(king.getX(), attacker.getX());
                int nY = Integer.compare(king.getY(), attacker.getY());
                int dX = nX + attacker.getX();
                int dY = nY + attacker.getY();
                while (dX != row || dY != col) {
                    if (row == dX && col == dY) {
                        return true;
                    }
                    dX += nX;
                    dY += nY;
                }
            }
            else {
                for (int i = 1; i < Math.abs(king.getX() - attacker.getX()); i++) {
                    // Because compare returns > 0 if row/col is greater than x/y and < 0 if less than, we are able to modify this
                    int r = Integer.compare(king.getX(), attacker.getX());
                    int c = Integer.compare(king.getY(), attacker.getY());
                    if (row == (attacker.getX() + i * r) && col == (attacker.getY() + i * c)) {
                        return true;
                    }
                }
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
