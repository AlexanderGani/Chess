import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Window extends JFrame implements MouseListener, MouseMotionListener {
    private final int HEIGHT = 740;
    private final int WIDTH = 720;
    private Image[] pawn, rook, knight, bishop, queen, king;
    private Piece p;
    private Board b;
    private int ix, iy;
    private int turn;
    private boolean wcheckmate;
    private boolean bcheckmate;

    public Window(Board b) {
        this.b = b;
        // Import resources for white/black pieces
        // Set values for turn and checkmate for white or black
        turn = 0;
        wcheckmate = false;
        bcheckmate = false;
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

    // Gets Pawn Images
    public Image[] getPawn() {
        return pawn;
    }
    // Gets Rook images
    public Image[] getRook() {
        return rook;
    }
    // Gets Knight Images
    public Image[] getKnight() {
        return knight;
    }
    // Gets Bishop Images
    public Image[] getBishop() {
        return bishop;
    }
    // Gets Queen Images
    public Image[] getQueen() {
        return queen;
    }
    // Gets King Images
    public Image[] getKing() {
        return king;
    }

    public void paint(Graphics g) {
        //draw row/col numbers
        // Set font to draw checkmate in
        Font s = new Font("s", Font.ITALIC, 75);
        g.setFont(s);
        Color green = new Color(0, 86, 63);
        Color cream = new Color(249, 229, 188);
        Color red = new Color(250, 0,0 );
        boolean Green = true;
        Cell[][] board = b.getBoard();
        //make checkered board
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                // If king is in check color that square red for white
                if(i == b.getWhiteKing().getX() && j == b.getWhiteKing().getY() && b.getWhiteKing().isChecked(b)) {
                    g.setColor(red);
                }
                // Same thing as above but for black
                else if(i == b.getBlackKing().getX() && j == b.getBlackKing().getY() && b.getBlackKing().isChecked(b)) {
                    g.setColor(red);
                }
                else if(Green) {
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
        g.setColor(red);
        // If checkmated draw this string
        if (wcheckmate) {
            g.drawString("Checkmate, White", 0, 350);
            b.reset();
        }
        else if(bcheckmate) {
            g.drawString("Checkmate, Black", 0, 350);
            b.reset();
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        // Finds row and col on board by div by cell width
        ix = (e.getX()) / 90;
        iy = (e.getY() - 20) / 90;

        // Select Piece
        Piece piece = b.getBoard()[iy][ix].getPiece();

        // If not blank square
        if (piece != null) {
            p = piece;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // Can't move a blank square
        if (p == null) {
            return;
        }
        // Find col and row again
        int col = (e.getX()) / 90;
        int row = (e.getY() - 20) / 90;
        // For white turn
        if (turn % 2 == 0) {
            // Promotion for pawn
            if (p instanceof Pawn) {
                if (row == 7 && p.isWhite()) {
                    p.promote(b, row, col);
                }
            }
            // If Checked, see if a piece can block the check, if it can't, undo the move
            if (b.getWhiteKing().isChecked(b)) {

                if (p.isValidMove(row, col, b)) {
                    p.move(row, col, b);
                    if (b.getWhiteKing().isChecked(b)) {
                        p.undoMove(b);
                    }
                    else {
                        turn++;
                    }
                }
            }
            // If it's a regular move also makes sure pinned pieces can't move
            else if (p.isValidMove(row, col, b) && p.isWhite()) {
                p.move(row, col, b);
                if(b.getWhiteKing().isChecked(b)) {
                    p.undoMove(b);
                }
                else {
                    turn++;
                }
            }
        }
        // Same thing as above, but for Black pieces
        else {
            if (p instanceof Pawn) {
                if (row == 0 && !p.isWhite()) {
                    p.promote(b, row, col);
                }
            }
            if (b.getBlackKing().isChecked(b)) {
                if(p.isValidMove(row, col, b)) {
                    p.move(row, col, b);
                    if (b.getBlackKing().isChecked(b)) {
                        p.undoMove(b);
                    }
                    else {
                        turn++;
                    }
                }
            }
            else if (p.isValidMove(row, col, b) && !p.isWhite()) {
                p.move(row, col, b);
                if(b.getBlackKing().isChecked(b)) {
                    p.undoMove(b);
                }
                else {
                    turn++;
                }
            }
        }
        // Refresh window
        repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    }