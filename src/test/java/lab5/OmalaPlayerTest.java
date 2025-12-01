package lab5;

import lab5.game.Board;
import lab5.game.Position;
import lab5.players.OmalaPlayer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class OmalaPlayerTest {

    @Test
    public void testOmalaWinsWhenPossible() {
        // Board:
        // X X .
        // . O .
        // . . .
        Board board = new Board("XX.O.....");

        var omala = new OmalaPlayer("Omala");
        Position pos = omala.pickNextMove(board);

        // Winning move = row 0, col 2
        assertEquals(0, pos.row().ordinal());
        assertEquals(2, pos.col().ordinal());
    }

    @Test
    public void testOmalaBlocksOpponent() {
        // O O .
        // . X .
        // . . .
        Board board = new Board("OO.X.....");

        var omala = new OmalaPlayer("Omala");
        Position pos = omala.pickNextMove(board);

        // Block row 0, col 2
        assertEquals(0, pos.row().ordinal());
        assertEquals(2, pos.col().ordinal());
    }

    @Test
    public void testOmalaFallback() {
        // X O X
        // O X .
        // . . .
        Board board = new Board("XOXOX....");

        var omala = new OmalaPlayer("Omala");
        Position pos = omala.pickNextMove(board);

        // First empty position in "XOXOX...." is index 5 → row 1, col 2
        assertEquals(1, pos.row().ordinal());
        assertEquals(2, pos.col().ordinal());
    }
}
