import java.awt.*;
import java.util.ArrayList;

public class Cell {
    private int WIDTH = 100;
    private int x, y;
    private Piece p;
    private Board b;

    public Cell(int x, int y, Board b) {
        //x and y are mixed up for all pieces and cells - too difficult to reset them
        this.x = x;
        this.y = y;
        this.b = b;
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

    // Getter methods
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    public void draw(Graphics g, Window w) {
        //draws checkers and piece in cell
        g.fillRect((y * 90) ,(x * 90) + 20, 90, 90);
        if (p != null) {
            p.draw(g, w);
        }
    }
}
