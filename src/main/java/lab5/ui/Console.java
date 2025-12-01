package lab5.ui;

import com.diogonunes.jcolor.AnsiFormat;
import lab5.game.*;
import lab5.players.*;
import lab5.players.FirstAvailablePlayer;

import java.util.Scanner;

import static com.diogonunes.jcolor.Ansi.colorize;
import static com.diogonunes.jcolor.Attribute.*;
/**
 * The Console class handles all user interaction for the Tic Tac Toe game.
 * <p>
 * This class is responsible for all input/output operations between the
 * program and the player. It provides methods for:
 * <ul>
 *     <li>Asking players for their names or selecting computer players</li>
 *     <li>Prompting players for their next move (board position)</li>
 *     <li>Displaying the current game board with colored symbols</li>
 *     <li>Showing normal messages and alert/error messages</li>
 *     <li>Formatting text using colors and styles (via JColor)</li>
 *     <li>Constructing and returning Player and Position objects based on user input</li>
 * </ul>
 * <p>
 * All communication between the player and the game runs through this class,
 * making it the UI (User Interface) layer of the application.
 */
public class Console {

    // Define some colors and text styles for use in the console
    private static final AnsiFormat fPrompt = new AnsiFormat(GREEN_TEXT(), BOLD());
    private static final AnsiFormat fAlert = new AnsiFormat(YELLOW_TEXT());

    public static void println(String message) {
        System.out.println(message);
    }

    /**
     * Prompt the user for input using the given promptMessage
     * @param promptMessage The message to prompt the user with
     * @return The user's response
     */
    public static String prompt(String promptMessage) {//promptMessage:question you want to ask the user
        System.out.print(fPrompt.format(promptMessage));
        var scanner = new Scanner(System.in);
        return scanner.nextLine(); // next line : wait for user to press enter key and return as a string
    }

    /**
     * Display an alert message to the user
     * @param message The message to display
     */
    public static void printAlert(String message) {
        System.out.println(fAlert.format(message));
    }

    /**
     * Repeatedly prompt the user to select a player for the given token
     * until the select a valid one of a set of valid players
     * @param whichPlayer The player for which to prompt
     * @return A player object representing the user's chosen player
     */
    public static Player promptForPlayer(PlayerToken whichPlayer) {//ask the user which player will play X or O

        while ( true ) {// if player token is working
            var input = prompt(fPrompt.format("Who will play " + whichPlayer + "? ")); // which X or O

            if (input.startsWith("@")) {
                input = input.substring(1).toLowerCase();

                switch (input) {

                    case "first" -> {
                        return new FirstAvailablePlayer("Computer-FirstAvailable");
                    }

                    case "random" -> {
                        return new RandomPlayer("Computer-Random");
                    }
                    case "omala" -> { return new OmalaPlayer("Computer-Omala"); }


                    default -> {
                        printAlert("Unknown computer player. Try '@first' '@random' or '@omala'");
                    }

                }

            }
            else {
                return new HumanPlayer(input);
            }
        }
    }

    /**
     * Display the given game board
     * @param board A tictactoe game board
     */
    public static void showBoard(Board board) {//Showboard from main
        var sb = new StringBuilder();// stringbuilder stores the colored board text
        for (var c : board.toString().toCharArray()) { // converts board into a string separated by single characters
            if ( c == 'X' ) {
                sb.append(colorize("X", BRIGHT_CYAN_TEXT()));
            } else if ( c == 'O' ) {
                sb.append(colorize("O", BRIGHT_MAGENTA_TEXT()));
            } else {
                sb.append(c); //add the character as it is
            }
        }
        System.out.println(sb);
    }

    /**
     * Repeatedly prompt the user for a position on which to place their next token.
     * If they enter an invalid response they are re-prompted.
     * @param prompt The prompt to display to the user
     * @return The position selected by the user
     */
    public static Position promptForPosition(String prompt) {// from player.java

        final String helpMessage = "Input must be in the format 'row column', e.g., '1 2' or 't m' for the top middle cell.";

        while ( true ) {
            var input = prompt(fPrompt.format(prompt));

            if ( input.length() != 3 ) {
                printAlert(helpMessage);
                continue;
            }

            var parts = input.split(" ");

            if ( parts.length != 2 ) { // if not 2 chars
                printAlert(helpMessage);
                continue;
            }

            try {//convert input in valid board position
                return new Position(Row.from(parts[0]), Col.from(parts[1]));
            } catch ( IllegalArgumentException e ) { // if something goes wrong inside the try block
                printAlert(helpMessage);
            }
        }
    }
}
