import java.util.Scanner;

public class Chess {
        private window view;
        public static final String X_MARKER = "X";
        public static final String O_MARKER = "O";
        public static final String BLANK = "-";

        public static final int ROW_WIN = 1;
        public static final int COL_WIN = 2;
        public static final int DIAGONAL_RIGHT_WIN = 3;
        public static final int DIAGONAL_LEFT_WIN = 4;
        private String winner;      // Provides the marker of the winner
        private int winDirection;   // Provides the direction of the win
        // following the win direction final variables above
        private int winIndex;       // Provides the index of the row/col of the win
        private int turn;

        private Cell[][] board;
        private boolean isGameOver;

        /**
         * Constructor which initialized the board with BLANKs.
         * The winner is also initialized to BLANK.
         *
         * The view is initialized with this TicTacToe object
         */
        public Chess() {
            // Initialize Squares in the board
            this.board = new Cell[8][8];
            for(int row = 0; row < this.board.length; row++) {
                for(int col = 0; col< this.board[row].length; col++) {
                    this.board[row][col] = new Cell(row, col);
                }
            }

            // Initialize winning stats variables
            this.isGameOver = false;
            this.turn = 0;
            this.winner = BLANK;
            this.winIndex = -1;
            this.winDirection = -1;
            view = new window(this);
        }

        /******************** Methods You May Find Helpful ********************/
        public Cell[][] getBoard() {
            return this.board;
        }

        public String getWinner() {
            return this.winner;
        }

        public boolean getGameOver() {
            return this.isGameOver;
        }

        public boolean checkTie() {
            return this.isGameOver && this.winner.equals(BLANK);
        }

        public void run() {
            Scanner input = new Scanner(System.in);

            System.out.println("Welcome to Chess!");

            // Loop until there is a winner or no more turns
            while(!this.checkWin() && this.checkTurn()) {
                this.printBoard();
                System.out.println("Enter your Row Pick:" );
                int row = input.nextInt();
                System.out.println("Enter your Col Pick:" );
                int col = input.nextInt();
                if(this.pickLocation(row, col)) {
                    this.takeTurn(row, col);
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
            } else {
                this.markWinningSquares();
                if (this.turn%2 == 0) {
                    this.winner = O_MARKER;
                    System.out.println("O Wins!");
                } else {
                    this.winner = X_MARKER;
                    System.out.println("X Wins!");
                }
            }
        }


        /******************** Do NOT Modify Code Below this Line ********************/
        /**
         * Places an X or an O in the provided row and col
         * @param row row to place character in indexed at 0
         * @param col column to place character in indexed at 0
         */
        private void takeTurn(int row, int col) {
            if(this.turn % 2 == 0) {
                this.board[row][col].setMarker(X_MARKER);
            }
            else {
                this.board[row][col].setMarker(O_MARKER);
            }
            this.turn++;
        }

        /**
         * Checks if the selected location is valid.
         * A location is valid if it is empty "-"
         * @param row selected row number indexed at 0
         * @param col selected column number indexed at 0
         * @return True if the location is available, False otherwise
         */
        private boolean pickLocation(int row, int col) {
            if(row < 3 && col < 3) {
                return this.board[row][col].isEmpty();
            }
            return false;
        }

        /**
         * Check if there's a win on the board
         * @return True if there's a win and False otherwise
         */
        private boolean checkWin() {
        }

        private boolean checkTurn() {
            return this.turn < 9;
        }

        private void printBoard() {
            System.out.println("  0 1 2");
            int row = 0;
            for(Cell[] array : this.board) {
                System.out.print(row + " ");
                for(Cell item : array) {
                    System.out.print(item + " ");

                }
                row++;
                System.out.println();
            }
        }

        public static void main(String[] args) {
            Chess game = new Chess();
            game.run();
        }
}
