package lab3.game;

/**
 * Represents the TicTacToe board.
 * Stores and updates the 3x3 grid.
 */
public class Board {
    private final char[][] grid;

    /** Creates an empty 3x3 board */
    public Board() {
        grid = new char[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                grid[i][j] = ' ';
            }
        }
    }

    /** Tries to place a move for the player. Returns true if successful. */
    public boolean placeMove(int row, int col, char player) {
        if (row < 0 || row > 2 || col < 0 || col > 2) return false;
        if (grid[row][col] != ' ') return false;
        grid[row][col] = player;
        return true;
    }

    /** Checks if a player has won */
    public boolean hasWon(char player) {
        for (int i = 0; i < 3; i++) {
            if (grid[i][0] == player && grid[i][1] == player && grid[i][2] == player) return true;
            if (grid[0][i] == player && grid[1][i] == player && grid[2][i] == player) return true;
        }
        if (grid[0][0] == player && grid[1][1] == player && grid[2][2] == player) return true;
        if (grid[0][2] == player && grid[1][1] == player && grid[2][0] == player) return true;
        return false;
    }

    /** Checks if the board is full (draw) */
    public boolean isFull() {
        for (char[] row : grid) {
            for (char cell : row) {
                if (cell == ' ') return false;
            }
        }
        return true;
    }

    /** Returns a printable string of the board */
    @Override
    public String toString() {
        return String.format("""
             %c | %c | %c
            ---+---+---
             %c | %c | %c
            ---+---+---
             %c | %c | %c
            """,
                grid[0][0], grid[0][1], grid[0][2],
                grid[1][0], grid[1][1], grid[1][2],
                grid[2][0], grid[2][1], grid[2][2]);
    }
}
