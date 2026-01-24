package lab3.ui;

import java.util.Scanner;


public class Console {
    private final Scanner scanner = new Scanner(System.in);


    public void showBoard(String boardText) {
        System.out.println(boardText);
    }


    public int[] promptMove(char player) {
        while (true) {
            System.out.print("Player " + player + " — enter your move (e.g., 2 3 or m r): ");
            String line = scanner.nextLine().trim().toLowerCase();
            String[] parts = line.split("\\s+");
            if (parts.length != 2) {
                System.out.println("❌ Enter two values separated by a space.");
                continue;
            }

            int row = parseRow(parts[0]);
            int col = parseCol(parts[1]);
            if (row != -1 && col != -1) {
                return new int[]{row, col};
            }
            System.out.println("⚠️ Invalid input. Try again.");
        }
    }


    private int parseRow(String s) {
        return switch (s) {
            case "1", "t" -> 0;
            case "2", "m", "c" -> 1;
            case "3", "b" -> 2;
            default -> -1;
        };
    }

    private int parseCol(String s) {
        return switch (s) {
            case "1", "l" -> 0;
            case "2", "m", "c" -> 1;
            case "3", "r" -> 2;
            default -> -1;
        };
    }
}
