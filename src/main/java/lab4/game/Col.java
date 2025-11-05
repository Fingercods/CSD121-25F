package lab4.game;

/**
 * Represents the column position of a tictactoe board location
 */
public enum Col {
    LEFT, MIDDLE, RIGHT;

    /**
     * @param str A string representation of a column position
     * @return The column position corresponding to the given string representation
     * @throws IllegalArgumentException if the given string is not a valid representation for a column position
     */
    public static Col from(String str) {
        return switch (str.toLowerCase()) {
            case "1", "l" -> LEFT;
            case "2", "m", "c" -> MIDDLE;
            case "3", "r" -> RIGHT;
            default -> throw new IllegalArgumentException("Invalid column: " + str);
        };
    }
}
