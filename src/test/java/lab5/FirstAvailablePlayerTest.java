package lab5;

import lab5.game.Board;
import lab5.game.Position;
import lab5.players.FirstAvailablePlayer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FirstAvailablePlayerTest {

    @Test
    public void testChoosesFirstEmptyCell() {
        Board board = new Board("XO.XO.X..");
        var player = new FirstAvailablePlayer("Computer");

        Position pos = player.pickNextMove(board);

        assertEquals(0, pos.row().ordinal());
        assertEquals(2, pos.col().ordinal());
    }

    @Test
    public void testChoosesFirstEmptyCellOnAnotherBoard() {
        Board board = new Board("XOXOX....");
        var player = new FirstAvailablePlayer("Computer");

        Position pos = player.pickNextMove(board);

        assertEquals(1, pos.row().ordinal());
        assertEquals(2, pos.col().ordinal());
    }
}
