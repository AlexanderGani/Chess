import java.util.Scanner;

public class Game {
    private window w;
    private Cell[][] board;

    public Game() {
        //init board cells - empty
        board = new Cell[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                this.board[i][j] = new Cell(i, j);
            }
        }
        //init pieces
        initBoard();
        //draw window
        w = new window(this);
    }

    public void run() {
        Scanner input = new Scanner(System.in);

        System.out.println("Welcome to Chess!");
        System.out.println("Instructions:\n" + "Input the row/col of your piece and then the row/col you want to"
        + " move it to.\n" + "Remember, pawns move 1 tile forwards, knights move in an L shape, and king can move 1 tile"
                + " in every direction");
        //main loop
        while (!this.checkWin()) {
            //all of these while loops are checks to avoid out of bounds exception error
            System.out.println("Enter your piece's row:");
            int row1 = input.nextInt();
            while (row1 > 7) {
                System.out.println("Enter your piece's row:");
                row1 = input.nextInt();
            }
            System.out.println("Enter your piece's col:");
            int col1 = input.nextInt();
            while (col1 > 7) {
                System.out.println("Enter your piece's col:");
                col1 = input.nextInt();
            }
            System.out.println("Enter your Row Pick:");
            int row = input.nextInt();
            while (row > 7) {
                System.out.println("Enter your Row Pick:");
                row = input.nextInt();
            }
            System.out.println("Enter your Col Pick:");
            int col = input.nextInt();
            while (col > 7) {
                System.out.println("Enter your Col Pick:");
                col = input.nextInt();
            }
            //check valid move, then move
            if (board[row1][col1].getPiece() != null && board[row1][col1].getPiece().isValidMove(row, col)) {
                board[row1][col1].getPiece().move(row, col, this);
            } else {
                System.out.println("That space is taken, or you entered an invalid row/col");
            }
            //repaint window to update piece locations
            w.repaint();
        }
        System.out.println(findWinner() + " wins!");
    }

    public void initBoard() {
        //set piece locations
        for (int i = 0; i < 8; i++) {
            board[1][i].setPiece(new Pawn(1, i, true));
        }
        board[0][0].setPiece(new Rook(0, 0, true));
        board[0][7].setPiece(new Rook(0, 7, true));
        board[0][1].setPiece(new Knight(0, 1, true));
        board[0][6].setPiece(new Knight(0, 6, true));
        board[0][2].setPiece(new Bishop(0, 2, true));
        board[0][5].setPiece(new Bishop(0, 5, true));
        King white = new King(0, 3, true);
        board[0][3].setPiece(white);
        board[0][4].setPiece(new Queen(0, 4, true));
        for (int i = 0; i < 8; i++) {
            board[6][i].setPiece(new Pawn(6, i, false));
        }
        board[7][0].setPiece(new Rook(7, 0, false));
        board[7][7].setPiece(new Rook(7, 7, false));
        board[7][1].setPiece(new Knight(7, 1, false));
        board[7][6].setPiece(new Knight(7, 6, false));
        board[7][2].setPiece(new Bishop(7, 2, false));
        board[7][5].setPiece(new Bishop(7, 5, false));
        King black = new King(7, 3, false);
        board[7][3].setPiece(black);
        board[7][4].setPiece(new Queen(7, 4, false));
    }

    public boolean checkWin() {
        //if more than 1 king, return false, else return true
        int count = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j].getPiece() instanceof King) {
                    count += 1;
                }
            }
        }
        if (count == 1) {
            return true;
        }
        return false;
    }

    public String findWinner() {
        //find specific winner (white or black)
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j].getPiece() instanceof King) {
                    if(board[i][j].getPiece().isWhite()) {
                        return "White";
                    }
                    else if(!board[i][j].getPiece().isWhite()) {
                        return "Black";
                    }
                }
            }
        }
        return null;
    }

    public Cell[][] getBoard() {
        return this.board;
    }
    public static void main(String[] args) {
        Game g = new Game();
        g.run();
    }
}
