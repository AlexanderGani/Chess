import java.awt.*;

public class Bishop extends Piece {
        private Image wbishop, bbishop;
        private int x, y;
        private boolean isWhite;

        public Bishop(int x, int y, boolean isWhite) {
            super(x, y, isWhite);
            this.isWhite = isWhite;
            this.x = x;
            this.y = y;
        }
    public void move(int row, int col, Game l) {
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
            wbishop = w.getBishop()[0];
            bbishop = w.getBishop()[1];
            if(isWhite) {
                g.drawImage(wbishop, 25 + (90 * y), 50 + (90 * x), 90, 90, w);
            }
            else {
                g.drawImage(bbishop, 25 + (90 * y), 50 + (90 * x), 90, 90, w);
            }
        }

}
