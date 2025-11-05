package lab4.game;

/**
 * Represents the row position of a tictactoe board location
 */
public enum Row {
    TOP, MIDDLE, BOTTOM;

    /**
     * @param str A string representation of a row position
     * @return The row position corresponding to the given string representation
     * @throws IllegalArgumentException if the given string is not a valid representation for a row position
     */
    public static Row from(String str) {
        return switch (str.toLowerCase()) {
            case "1", "t" -> TOP;
            case "2", "m", "c" -> MIDDLE;
            case "3", "b" -> BOTTOM;
            default -> throw new IllegalArgumentException("Invalid row: " + str);
        };
    }
}
