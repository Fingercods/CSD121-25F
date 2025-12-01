package lab5.players;

import lab5.game.Board;
import lab5.game.Position;

import java.util.List;

/**
 * Omala (Advanced Player)
 *
 * 1. If Omala can win this turn → play that move.
 * 2. If the opponent can win next turn → block that move.
 * 3. Otherwise, → pick the first empty cell.
 */
public class OmalaPlayer extends Player {

    public OmalaPlayer(String name) {
        super(name);
    }

    @Override
    public Position pickNextMove(Board currentBoard) {

        List<Position> empties = currentBoard.getEmptyCells();
        //Try to win in one move
        for (Position pos : empties) {
            Board copy = new Board(currentBoard);
            copy.placeNextToken(pos);

            if (copy.getStatus() != Board.Status.InProgress) {
                return pos;
            }
        }

        // block opponent winning move
        for (Position pos : empties) {
            Board copy = new Board(currentBoard);
            copy.placeNextToken(pos);   //  opponent move too

            if (copy.getStatus() != Board.Status.InProgress) {
                return pos;             // block this spot
            }
        }


        return empties.get(0);  // take the first empty cell
    }
}
