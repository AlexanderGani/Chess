import java.awt.*;

public class Cell {
    private int row;
    private int col;
    private String space;

    public Cell(int row, int col) {
        this.row = row;
        this.col = col;
    }
    public boolean isEmpty() {
        return this.space.equals(Chess.BLANK);
    }
    public void draw(Graphics g, window v) {
        int width = 100;
        g.drawRect(100 + (width * col), 100 + (width * row), width, width);
        if (space.equals("X")) {
            g.drawImage(v.getImage(), 100 + (width * col), 100 + (width * row), width, width, v);
        }
        else if(space.equals("O")) {
            g.drawImage(v.getImage(), 100 + (width * col), 100 + (width * row), width, width, v);
        }
    }
}
