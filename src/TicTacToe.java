
import java.util.Scanner;

public class TicTacToe {

    private static final int ROWS = 3;
    private static final int COLS = 3;

    private static String[][] board = new String[ROWS][COLS];

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        boolean playAgain = true;

        // Keep playing while the players want another game
        while (playAgain) {

            // Clear the board and set X as the first player
            clearBoard();
            String player = "X";
            int moveCount = 0;
            boolean gameOver = false;

            // Continue until someone wins or the game is tied
            while (!gameOver) {

                display();

                System.out.println("Player " + player + "'s turn.");

                // Get the player's row and column
                int rowMove = SafeInput.getRangedInt(in, "Enter row", 1, ROWS);
                int colMove = SafeInput.getRangedInt(in, "Enter column", 1, COLS);

                // Convert the coordinates to array indexes
                int row = rowMove - 1;
                int col = colMove - 1;

                // Check if the move is valid
                while (!isValidMove(row, col)) {
                    System.out.println("That space is already taken. Try again.");

                    rowMove = SafeInput.getRangedInt(in, "Enter row", 1, ROWS);
                    colMove = SafeInput.getRangedInt(in, "Enter column", 1, COLS);

                    row = rowMove - 1;
                    col = colMove - 1;
                }

                // Record the player's move
                board[row][col] = player;
                moveCount++;

                // Check for a win
                if (moveCount >= 5 && isWin(player)) {
                    display();
                    System.out.println("Player " + player + " wins!");
                    gameOver = true;
                }

                // Check for a tie
                else if (isTie()) {
                    display();
                    System.out.println("The game is a tie!");
                    gameOver = true;
                }

                // Switch players
                else {
                    if (player.equals("X")) {
                        player = "O";
                    } else {
                        player = "X";
                    }
                }
            }

            // Ask players if they want to play again
            playAgain = SafeInput.getYNConfirm(in, "Would you like to play again?");
        }

        System.out.println("Thanks for playing!");
        in.close();
    }

    // Helper methods go below this line

    private static void clearBoard() {
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                board[row][col] = " ";
            }
        }
    }

    private static void display() {
        System.out.println();

        for (int row = 0; row < ROWS; row++) {
            System.out.println(" " + board[row][0] + " | " +
                    board[row][1] + " | " + board[row][2]);

            if (row < ROWS - 1) {
                System.out.println("---+---+---");
            }
        }

        System.out.println();
    }

    private static boolean isValidMove(int row, int col) {
        return board[row][col].equals(" ");
    }

    private static boolean isWin(String player) {
        return isColWin(player) ||
                isRowWin(player) ||
                isDiagnalWin(player);
    }

    private static boolean isColWin(String player) {
        for (int col = 0; col < COLS; col++) {
            if (board[0][col].equals(player) &&
                    board[1][col].equals(player) &&
                    board[2][col].equals(player)) {
                return true;
            }
        }

        return false;
    }

    private static boolean isRowWin(String player) {
        for (int row = 0; row < ROWS; row++) {
            if (board[row][0].equals(player) &&
                    board[row][1].equals(player) &&
                    board[row][2].equals(player)) {
                return true;
            }
        }

        return false;
    }

    private static boolean isDiagnalWin(String player) {
        if (board[0][0].equals(player) &&
                board[1][1].equals(player) &&
                board[2][2].equals(player)) {
            return true;
        }

        if (board[0][2].equals(player) &&
                board[1][1].equals(player) &&
                board[2][0].equals(player)) {
            return true;
        }

        return false;
    }

    private static boolean isTie() {

        // Check rows
        for (int row = 0; row < ROWS; row++) {
            boolean hasX = false;
            boolean hasO = false;

            for (int col = 0; col < COLS; col++) {
                if (board[row][col].equals("X")) hasX = true;
                if (board[row][col].equals("O")) hasO = true;
            }

            if (!hasX || !hasO) return false;
        }

        // Check columns
        for (int col = 0; col < COLS; col++) {
            boolean hasX = false;
            boolean hasO = false;

            for (int row = 0; row < ROWS; row++) {
                if (board[row][col].equals("X")) hasX = true;
                if (board[row][col].equals("O")) hasO = true;
            }

            if (!hasX || !hasO) return false;
        }

        // Check both diagonals
        boolean mainX = false, mainO = false;
        boolean otherX = false, otherO = false;

        for (int i = 0; i < ROWS; i++) {
            if (board[i][i].equals("X")) mainX = true;
            if (board[i][i].equals("O")) mainO = true;

            if (board[i][COLS - 1 - i].equals("X")) otherX = true;
            if (board[i][COLS - 1 - i].equals("O")) otherO = true;
        }

        return mainX && mainO && otherX && otherO;
    }
}

    
