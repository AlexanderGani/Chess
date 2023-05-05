import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

public class window extends JFrame implements MouseListener, MouseMotionListener {
    private final int HEIGHT = 850;
    private final int WIDTH = 825;
    private Image[] pawn, rook, knight, bishop, queen, king;
    private Game l;
    private Piece p;
    private int ix, iy;

    public window(Game l) {
        this.l = l;
        //import resources for white/black pieces
        addMouseListener(this);
        rook = new Image[2];
        rook[0] = new ImageIcon("Resources/wrook.png").getImage();
        rook[1] = new ImageIcon("Resources/brook.png").getImage();
        pawn = new Image[2];
        pawn[0] = new ImageIcon("Resources/wpawn.png").getImage();
        pawn[1] = new ImageIcon("Resources/bpawn.png").getImage();
        knight = new Image[2];
        knight[0] = new ImageIcon("Resources/wknight.png").getImage();
        knight[1] = new ImageIcon("Resources/bknight.png").getImage();
        bishop = new Image[2];
        bishop[0] = new ImageIcon("Resources/wbishop.png").getImage();
        bishop[1] = new ImageIcon("Resources/bbishop.png").getImage();
        queen = new Image[2];
        queen[0] = new ImageIcon("Resources/wqueen.png").getImage();
        queen[1] = new ImageIcon("Resources/bqueen.png").getImage();
        king = new Image[2];
        king[0] = new ImageIcon("Resources/wking.png").getImage();
        king[1] = new ImageIcon("Resources/bking.png").getImage();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Chess");
        this.setSize(WIDTH, HEIGHT);
        this.setVisible(true);
    }

    public Image[] getPawn() {
        return pawn;
    }
    public Image[] getRook() {
        return rook;
    }
    public Image[] getKnight() {
        return knight;
    }
    public Image[] getBishop() {
        return bishop;
    }
    public Image[] getQueen() {
        return queen;
    }
    public Image[] getKing() {
        return king;
    }

    public void paint(Graphics g) {
        //draw row/col numbers
        Font s = new Font("s", Font.ITALIC, 12);
        g.setFont(s);
        g.setColor(Color.BLACK);
        g.drawString("0", 10, 100);
        g.drawString("1", 10, 200);
        g.drawString("2", 10, 300);
        g.drawString("3", 10, 400);
        g.drawString("4", 10, 500);
        g.drawString("5", 10, 600);
        g.drawString("6", 10, 700);
        g.drawString("7", 10, 800);
        g.drawString("0", 75, 40);
        g.drawString("1", 175, 40);
        g.drawString("2", 275, 40);
        g.drawString("3", 375, 40);
        g.drawString("4", 475, 40);
        g.drawString("5", 575, 40);
        g.drawString("6", 675, 40);
        g.drawString("7", 775, 40);
        //create colors for board
        Color green = new Color(0, 86, 63);
        Color cream = new Color(249, 229, 188);
        boolean Green = true;
        Cell[][] board = l.getBoard();
        //make checkered board
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if(Green) {
                    g.setColor(green);
                }
                else {
                    g.setColor(cream);
                }
                board[i][j].draw(g, this);
                //compressed if/else loop, if !Green, Green = true else false
                Green =! Green;
            }
            Green =! Green;
        }
        //draw win on screen
     /*   if(l.checkWin()) {
            Font m = new Font("m", Font.BOLD, 50);
            g.setFont(m);
            g.setColor(Color.BLACK);
            g.drawString(l.findWinner() + " wins!", 412, 425);
        } */

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        ix = (e.getX() - 25) / 90;
        iy = (e.getY() - 50) / 90;

        Piece piece = l.getBoard()[iy][ix].getPiece();

        if (piece != null) {
            p = piece;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

        if (p == null) {
            return;
        }
        int col = (e.getX() - 25) / 90;
        int row = (e.getY() - 50) / 90;

        if (p.isValidMove(row, col, l)) {
            p.move(row, col, l);
        }
        repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        int deltaX = e.getX() - ix;
        int deltaY = e.getY() - iy;
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    }