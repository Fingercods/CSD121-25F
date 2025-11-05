package lab4.game;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ColTest {

    @Test
    void testValidColumns() {
        assertEquals(Col.LEFT, Col.from("l"));
        assertEquals(Col.MIDDLE, Col.from("m"));
        assertEquals(Col.RIGHT, Col.from("r"));
    }

    @Test
    void testCaseInsensitive() {
        assertEquals(Col.LEFT, Col.from("L"));
        assertEquals(Col.MIDDLE, Col.from("M"));
        assertEquals(Col.RIGHT, Col.from("R"));
    }

    @Test
    void testInvalidColumnThrows() {
        assertThrows(IllegalArgumentException.class, () -> Col.from("x"));
    }
}
