package lab4.game;

import static lab4.game.Board.PlayerToken.O;
import static lab4.game.Board.PlayerToken.X;

/**
 * Represents a TicTacToe game board
 */
public class Board {

    /** Represents each token on the board */
    public enum PlayerToken { X, O }

    /** Represents the high-level status of the game board */
    public enum Status { InProgress, Draw, XWins, OWins }

    /** The current game board state */
    private final PlayerToken[][] board = new PlayerToken[3][3];

    /** Initialize a new, empty TicTacToe board */
    public Board() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = null;
            }
        }
    }

    /**
     * @return The current status of the game board
     */
    public Status getStatus() {
        PlayerToken winner = this.getWinner();
        if (winner == X) return Status.XWins;
        if (winner == O) return Status.OWins;
        return isFull() ? Status.Draw : Status.InProgress;
    }

    /**
     * @param pos A board position
     * @return The row index for the given position
     */
    private int rowIdx(Position pos) {
        return switch (pos.row()) {
            case TOP -> 0;
            case MIDDLE -> 1;
            case BOTTOM -> 2;
        };
    }

    /**
     * @param pos A board position
     * @return The column index for the given position
     */
    private int colIdx(Position pos) {
        return switch (pos.col()) {
            case LEFT -> 0;
            case MIDDLE -> 1;
            case RIGHT -> 2;
        };
    }

    /**
     * @return The PlayerToken for the winner, or null if there is no winner
     */
    private PlayerToken getWinner() {
        // Check rows and columns
        for (int i = 0; i < 3; i++) {
            // Row check
            if (board[i][0] != null && board[i][0] == board[i][1] && board[i][1] == board[i][2]) {
                return board[i][0];
            }
            // Column check
            if (board[0][i] != null && board[0][i] == board[1][i] && board[1][i] == board[2][i]) {
                return board[0][i];
            }
        }

        // ✅ Fixed diagonal checks
        if (board[0][0] != null && board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
            return board[0][0]; // fixed from board[0][2]
        }
        if (board[0][2] != null && board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
            return board[0][2];
        }

        return null;
    }

    /** @return true if the board is full; false otherwise */
    public boolean isFull() {
        for (var row : board) {
            for (var cell : row) {
                if (cell == null) return false;
            }
        }
        return true;
    }

    /**
     * @param pos The board position
     * @return true if already occupied
     */
    public boolean isOccupiedAt(Position pos) {
        return board[rowIdx(pos)][colIdx(pos)] != null;
    }

    /** Places an X token on the game board */
    public void placeX(Position pos) {
        board[rowIdx(pos)][colIdx(pos)] = X;
    }

    /** Places an O token on the game board */
    public void placeO(Position pos) {
        board[rowIdx(pos)][colIdx(pos)] = O;
    }

    /**
     * @return A 3x3 string representation of the game board
     */
    @Override
    public String toString() {
        var boardString = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                boardString.append(board[i][j] == null ? '.' : board[i][j]);
            }
            boardString.append("\n");
        }
        return boardString.toString();
    }
}
