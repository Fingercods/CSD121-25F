package lab5.players;

import lab5.game.Board;
import lab5.game.Position;

/**
 * A simple computer-controlled player that always selects
 * the first available empty position on the board.
 * <p>
 * This is the simplest possible strategy:
 * <ul>
 *     <li>Retrieve the list of empty cells from the board</li>
 *     <li>Return the first one in the list</li>
 * </ul>
 * Because this strategy requires no logic or lookahead,
 * it serves as an easy example of a basic computer player.
 */
public class FirstAvailablePlayer extends Player {

    /**
     * Construct a new FirstAvailablePlayer with the given name.
     *
     * @param name the name of the computer player
     */
    public FirstAvailablePlayer(String name) {
        super(name);
    }

    /**
     * Select the next move by returning the first empty cell
     * from the list provided by the board.
     *
     * @param currentBoard the current game board
     * @return the first available empty position
     */
    @Override
    public Position pickNextMove(Board currentBoard) {
        return currentBoard.getEmptyCells().get(0);
    }
}
