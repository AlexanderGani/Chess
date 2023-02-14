import java.awt.*;

public class Cell {
    private int WIDTH = 100;
    private int x, y;
    private Piece p;

    public Cell(int x, int y) {
        //x and y are mixed up for all pieces and cells - too difficult to reset them
        this.x = x;
        this.y = y;
    }
    //find piece in cell, if any
    public Piece getPiece() {
        return p;
    }

    //set piece, for when moving pieces
    public void setPiece(Piece p) {
        this.p = p;
    }

    //remove piece for when eating pieces or moving pieces
    public void removePiece() {
        p = null;
    }
    public void draw(Graphics g, window w) {
        //draws checkers and piece in cell
        g.fillRect(25 + (y * 100), 50 + x * 100, 100, 100);
        if (p != null) {
            p.draw(g, w);
        }
    }
}
