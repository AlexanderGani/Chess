import javax.swing.*;
import java.awt.*;

public class window extends JFrame {
    private final int HEIGHT = 800;
    private final int WIDTH = 800;
    private Game C;

    public window(Game C) {
        this.C = C;
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Chess");
        this.setSize(WIDTH, HEIGHT);
        this.setVisible(true);
    }

    public void paint(Graphics g) {
        Font s = new Font("Roman", Font.ROMAN_BASELINE, 20);
        g.setFont(s);
        g.setColor(Color.GREEN);
        g.drawString("0", 150, 50);
        g.drawString("1", 300, 50);
        g.drawString("2", 450, 50);
        g.drawString("0", 50, 150);
        g.drawString("1", 50, 300);
        g.drawString("2", 50, 450);
        g.setColor(Color.BLACK);
        Cell[][] cells = C.getBoard();
        for(int i = 0; i < cells.length; i++) {
            for(int j = 0; j < cells[i].length; j++) {
                cells[i][j].draw(g);
            }
        }
    }
    }