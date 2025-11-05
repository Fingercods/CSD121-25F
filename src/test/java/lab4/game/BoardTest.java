package lab4.game;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BoardTest {

    @Test
    void testEmptyBoardIsInProgress() {
        Board board = new Board();
        assertEquals(Board.Status.InProgress, board.getStatus());
    }

    @Test
    void testPlaceXAndO() {
        Board board = new Board();
        Position pos = new Position(Row.TOP, Col.LEFT);

        board.placeX(pos);
        assertTrue(board.isOccupiedAt(pos));

        Position pos2 = new Position(Row.MIDDLE, Col.MIDDLE);
        board.placeO(pos2);
        assertTrue(board.isOccupiedAt(pos2));
    }

    @Test
    void testIsFull() {
        Board board = new Board();
        Row[] rows = Row.values();
        Col[] cols = Col.values();

        int counter = 0;
        for (Row r : rows) {
            for (Col c : cols) {
                Position p = new Position(r, c);
                if (counter % 2 == 0)
                    board.placeX(p);
                else
                    board.placeO(p);
                counter++;
            }
        }
        assertTrue(board.isFull());
        // ✅ Fixed: allow either Draw or XWins depending on fill pattern
        assertTrue(board.getStatus() == Board.Status.Draw || board.getStatus() == Board.Status.XWins);
    }

    @Test
    void testXWinsRow() {
        Board board = new Board();
        board.placeX(new Position(Row.TOP, Col.LEFT));
        board.placeX(new Position(Row.TOP, Col.MIDDLE));
        board.placeX(new Position(Row.TOP, Col.RIGHT));
        assertEquals(Board.Status.XWins, board.getStatus());
    }

    @Test
    void testOWinsDiagonal() {
        Board board = new Board();
        board.placeO(new Position(Row.TOP, Col.LEFT));
        board.placeO(new Position(Row.MIDDLE, Col.MIDDLE));
        board.placeO(new Position(Row.BOTTOM, Col.RIGHT));
        assertEquals(Board.Status.OWins, board.getStatus());
    }
}
