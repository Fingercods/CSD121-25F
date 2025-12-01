package lab5;

import lab5.game.Board;
import lab5.game.Position;
import lab5.players.RandomPlayer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RandomPlayerTest {

    @Test
    public void testRandomPlayerChoosesEmptyCell() {
        Board board = new Board("XO.XO.X..");
        var player = new RandomPlayer("Computer-Random");

        Position pos = player.pickNextMove(board);

        // Random player must choose an empty position
        assertTrue(board.isEmptyAt(pos));
    }
}
