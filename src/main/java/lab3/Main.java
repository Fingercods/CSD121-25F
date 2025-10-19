package lab3;

import lab3.game.Board;
import lab3.ui.Console;


public class Main {
    public static void main(String[] args) {
        Console ui = new Console();
        Board board = new Board();
        char currentPlayer = 'X';
        boolean running = true;

        System.out.println("🎮 Welcome to TicTacToe!");
        System.out.println("Use 1-3 or letters (t/m/b, l/m/r) for your move.\n");

        while (running) {
            ui.showBoard(board.toString());
            int[] move = ui.promptMove(currentPlayer);

            if (!board.placeMove(move[0], move[1], currentPlayer)) {
                System.out.println("❌ That spot is taken or invalid. Try again.\n");
                continue;
            }

            if (board.hasWon(currentPlayer)) {
                ui.showBoard(board.toString());
                System.out.println("🎉 Player " + currentPlayer + " wins!");
                running = false;
            } else if (board.isFull()) {
                ui.showBoard(board.toString());
                System.out.println("🤝 It's a draw!");
                running = false;
            } else {
                currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
            }
        }
    }
}
