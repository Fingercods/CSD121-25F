package lab6.game;

public class TicTacToeGame {

    private final Board board = new Board();

    public TicTacToeGame() {}

    public Board getBoard() {
        return board;
    }

    public PlayerToken playAt(Position pos) {
        var token = board.getNextTurnToken();
        board.placeNextToken(pos);
        return token;
    }
}
