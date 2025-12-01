package lab5.game;

import lab5.players.Player;

/**
 * Handles all game logic for a Tic Tac Toe match.
 * <p>
 * This class is responsible for managing the full lifecycle of a game,
 * including:
 * <ul>
 *     <li>Tracking which player's turn it is</li>
 *     <li>Maintaining the current state of the game board</li>
 *     <li>Asking the appropriate player to select their next move</li>
 *     <li>Validating and placing that move on the board</li>
 *     <li>Returning a {@code TurnRecord} describing what happened each turn</li>
 * </ul>
 * <p>
 * The {@code TicTacToeGame} class acts as the main controller for game flow.
 * It does not handle input/output directly; instead, it communicates with
 * {@code Player} objects, which themselves determine how moves are chosen
 * (e.g., human input or computer strategy).
 */

public class TicTacToeGame {

    /**
     * The players in the game
     */

    private final Player playerX;
    private final Player playerO;

    /**
     * The current state of the game board
     */
    private final Board board = new Board();

    /**
     * Initialize a new TicTacToe game with the given players
     * @param playerX The X player
     * @param playerO The O player
     */
    public TicTacToeGame(Player playerX, Player playerO) {
        this.playerX = playerX;
        this.playerO = playerO;
    }

    /**
     * Request where the next player wants to place a token on the board,
     * and then place that token, and update the game state accordingly.
     * @return A record of the turn that was just played
     * @throws IllegalArgumentException if the given position is already taken on the game board
     */
    public TurnRecord doNextTurn() throws IllegalArgumentException {
        var turnToken = board.getNextTurnToken();//Get whose turn it is
        var whoseTurn = switch(turnToken) {//Pick the correct player object
            case X -> playerX;
            case O -> playerO;
        };
        var pos = whoseTurn.pickNextMove(board);//Ask that player for their move

        // If the player objects are implemented correctly, this should never happen
        if ( ! board.isEmptyAt(pos) ) {//Validate the move
            throw new IllegalArgumentException("Position %s is already taken".formatted(pos));
        }

        board.placeNextToken(pos);//Place the token on the board

        return new TurnRecord(whoseTurn, turnToken, pos, board);//return whose did what move and new board from main
    }
    /**
     * Represents what happened on one turn that was just played
     * @param whoseTurn The player who just played
     * @param positionPlayed The position on the board where the token was placed
     * @param newBoardState The new state of the game board after the turn was played
     */
    public record TurnRecord(Player whoseTurn, PlayerToken token, Position positionPlayed, Board newBoardState) {}
//data container
}
