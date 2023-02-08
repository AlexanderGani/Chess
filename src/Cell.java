import java.awt.*;

public class Cell {
    private int row;
    private int col;
    private String space;
    private String piece;

    public Cell(int row, int col) {
        this.row = row;
        this.col = col;
        this.piece = "-";
    }
    //public boolean isEmpty() {
        //return this.space.equals(Chess.BLANK);
   // }

    public int getRow() {
        return this.row;
    }

    public int getCol() {
        return this.col;
    }

    public void setCell(String piece) {
        this.piece = piece;
    }
    public void draw(Graphics g) {
        int width = 100;
        g.drawRect(100 + (width * col), 100 + (width * row), width, width);
    }

    public String toString() {
        return this.piece;
    }

}
