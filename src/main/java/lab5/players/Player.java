package lab5.players;

import lab5.game.Board;
import lab5.game.Position;

/**
 * Represents a player in the Tic Tac Toe game.
 * <p>
 * This class stores the player's name and defines the common interface
 * that all player types must follow. It is an abstract superclass that
 * is extended by concrete player types such as {@code HumanPlayer} and
 * various computer-controlled players.
 * <p>
 * Responsibilities of this class include:
 * <ul>
 *     <li>Storing and returning the player's name</li>
 *     <li>Providing a common type that can be used by the game engine
 *         regardless of the specific player implementation</li>
 *     <li>Declaring the {@code pickNextMove} method that all subclasses
 *         must implement in order to decide their next move</li>
 * </ul>
 * The actual behaviour for choosing a move is implemented in subclasses.
 * For example, {@code HumanPlayer} will prompt the user through the console,
 * while computer players will use different strategies to select their moves.
 */
public abstract class Player {

    private String name;

    /**
     * Construct a new player with the given name.
     *
     * @param name the name of the player
     */
    public Player(String name) {
        this.name = name;
    }

    /**
     * Get the name of this player.
     *
     * @return the player's name
     */
    public String getName() {
        return name;
    }

    /**
     * Decide and return the next move this player wants to make
     * on the current board.
     * <p>
     * Each concrete subclass (for example, {@code HumanPlayer} or a
     * computer-controlled player) must provide its own implementation
     * of this method.
     *
     * @param currentBoard the current state of the game board
     * @return the position where this player wants to place their token
     */
    public abstract Position pickNextMove(Board currentBoard);
}
