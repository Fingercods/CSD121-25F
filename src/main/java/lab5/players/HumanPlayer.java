package lab5.players;

import lab5.game.Board;
import lab5.game.Position;
import lab5.ui.Console;

/**
 * A player that is controlled by a human using the console.
 * The human is asked to choose a position on their turn.
 */
public class HumanPlayer extends Player {

    /**
     * Create a new HumanPlayer with the given name.
     *
     * @param name the name of the player
     */
    public HumanPlayer(String name) {
        super(name);
    }

    /**
     * Ask the human user to pick their next move on the current board.
     *
     * @param currentBoard the current state of the game board
     * @return the position chosen by the human player
     */
    @Override
    public Position pickNextMove(Board currentBoard) {
        while (true) {
            var move = Console.promptForPosition(getName() + " pick your next move: ");
            if (currentBoard.isEmptyAt(move)) {
                return move;
            } else {
                Console.printAlert("That position is already taken.");
            }
        }
    }
}
