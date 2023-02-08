import java.util.Scanner;

public class Game {
    private window view;
    private Cell[][] board;
    private boolean isGameOver;
    public Game() {
        this.board = new Cell[8][8];
        for (int i = 0; i < this.board.length; i++) {
            for (int j = 0; j < this.board[i].length; j++) {
                this.board[i][j] = new Cell(i, j);
            }
        }
        this.isGameOver = false;
        this.view = new window(this);
    }

    public void run() {
        Scanner input = new Scanner(System.in);

        System.out.println("Welcome to Tic Tac Toe!");

        // Loop until there is a winner or no more turns
        while(!this.checkWin()) {
            this.printBoard();
            System.out.println("Enter piece name:");
            String name = input.nextLine();
            System.out.println("Enter your piece's Row:" );
            int row = input.nextInt();
            System.out.println("Enter your piece's Col:" );
            int col = input.nextInt();
            System.out.println("Enter your row pick:");
            int row1 = input.nextInt();
            System.out.println("Enter your col pick:");
            int col1 = input.nextInt();
            if(this.pickLocation(row, col)) {
                this.move(row, col, row1, col1, name);
            } else {
                System.out.println("That space is taken, or you entered an invalid row/col");
            }
            view.repaint();
        }

        this.printBoard();
        this.isGameOver = true;

        // Determine if there was a winner
        if(!this.checkWin()) {
            System.out.println("Game ends in a tie!");
        }
    }

    private boolean checkWin() {
        return false;
    }

    private boolean pickLocation(int row, int col) {
        if(row > 8 || col > 8) {
            return false;
        }
        return true;
    }
    private void move(int row1, int col2, int row, int col, String piece) {
        this.board[row][col].setCell(piece);
        this.board[row1][col2].setCell("-");
    }

    public void printBoard() {
        System.out.println("  a b c d e f g h");
        int row = 0;
        for (Cell[] array : this.board) {
            System.out.print(row + " ");
            for (Cell item : array) {
                System.out.print(item + " ");
            }
            row++;
            System.out.println();
        }
    }

    public Cell[][] getBoard() {
        return this.board;
    }
    public static void main(String[] args) {
        Game g = new Game();
        g.run();
    }
}
