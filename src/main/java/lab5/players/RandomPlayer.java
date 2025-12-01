package lab5.players;

import lab5.game.Board;
import lab5.game.Position;

import java.util.List;
import java.util.Random;

/**
 * A computer-controlled player that chooses a random empty cell.
 *
 * This strategy is unpredictable because it selects one of the available
 * positions completely at random.
 */
public class RandomPlayer extends Player {

    private final Random rng = new Random();

    public RandomPlayer(String name) {
        super(name);
    }

    @Override
    public Position pickNextMove(Board currentBoard) {
        List<Position> empties = currentBoard.getEmptyCells();

        // Choose a random empty position
        int index = rng.nextInt(empties.size());
        return empties.get(index);
    }
}
