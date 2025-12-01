package lab5;

import lab5.game.TicTacToeGame;
import lab5.ui.Console;

import static lab5.game.PlayerToken.O;
import static lab5.game.PlayerToken.X;
/**
 * The entry point and overall controller ("director") of the Tic Tac Toe game.
 * <p>
 * This class does not contain the game logic itself. Instead, it coordinates the
 * flow of the program:
 * <ul>
 *     <li>Displays the welcome message and usage instructions</li>
 *     <li>Prompts the user to choose the two players (human or computer)</li>
 *     <li>Creates a new {@link lab5.game.TicTacToeGame} instance</li>
 *     <li>Runs the main game loop, advancing the game one turn at a time</li>
 *     <li>Displays the results of each turn (player, token, and board state)</li>
 *     <li>Checks for win or draw conditions and ends the program when the game is complete</li>
 * </ul>
 * All game rules and mechanics are handled by the {@link lab5.game.Board} and
 * {@link lab5.game.TicTacToeGame} classes. This class simply directs the high-level flow.
 */

public class Main {

    public static void main(String[] args) {

        Console.println("""
    Welcome to Tic Tac Toe!
    Players can be human or computer. When prompted for player names use one of the following:
    - To play as a human, just enter a name
    - To have the player played by the computer enter @ followed by one of the following names:
      - @first   → simple computer: first available move
      - @random  → simple computer: random move
      - @omala   → Omala (advanced one-move-ahead)
                
""");


        var player1 = Console.promptForPlayer(X);
        var player2 = Console.promptForPlayer(O);
        var game = new TicTacToeGame(player1, player2); // create the instance of game

        while (true) { // main loop and logic of game

            // Advance the game based on the player's selected move, and get the results
            var turnRecord = game.doNextTurn();

            // Display the results of the turn
            Console.println("%s plays %s at %s %s".formatted(turnRecord.whoseTurn().getName(), turnRecord.token(), turnRecord.positionPlayed().row(), turnRecord.positionPlayed().col())); //fetched from tictactoegame.java
            var newBoard = turnRecord.newBoardState();
            Console.showBoard(newBoard);

            // Decide what to do next based on the current status of the game
            switch ( newBoard.getStatus() ) { //checks(if its draw or victory on each turn) get status from board.java
                case Draw -> { // this means switch'->'
                    Console.println("It's a draw!");
                    System.exit(0); // 0 = the program ended normally(stop the program)
                }
                case XWins, OWins -> {
                    Console.println("%s wins!".formatted(turnRecord.whoseTurn().getName())); // check turn from tictaktoegame.java
                    System.exit(0);
                }
            }

        }
    }
}
