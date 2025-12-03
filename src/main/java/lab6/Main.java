package lab6;

import javafx.animation.Interpolator;
import javafx.animation.ScaleTransition;
import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import lab6.game.*;


public class Main extends Application {

    private TicTacToeGame game;
    private Button[][] boardButtons = new Button[3][3];
    private Label status = new Label();

    @Override
    public void start(Stage stage) {

        // new game created
        game = new TicTacToeGame();

        status.setText("New game – X starts");


        BorderPane root = new BorderPane();//layout of root


        BorderPane.setAlignment(status, Pos.CENTER);//align
        status.getStyleClass().add("status-label");
        root.setTop(status);


        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(0);
        grid.setVgap(0);
        root.setCenter(grid);


        for (int r = 0; r < 3; r++) { // create btn
            for (int c = 0; c < 3; c++) {

                Button btn = new Button();
                btn.getStyleClass().add("cell");

                final int rr = r;
                final int cc = c;

                btn.setOnMouseReleased(e -> animateHoverOut(btn));

                btn.setOnAction(e -> {
                    animateClick(btn);
                    handleMove(rr, cc); // created below
                });

                boardButtons[r][c] = btn;
                grid.add(btn, c, r);// place button inside grid on screen
            }
        }

        Button reset = new Button("Reset Game");
        reset.getStyleClass().add("reset-btn");
        BorderPane.setAlignment(reset, Pos.CENTER);
        root.setBottom(reset);

        reset.setOnAction(e -> {// on click
            animateClick(reset);
            resetGame(); //created below at end of main.java
        });

        Scene scene = new Scene(root, 400, 500);

        scene.getStylesheets().add(
                getClass().getResource("/css/style.css").toExternalForm()
        );

        stage.setScene(scene);
        stage.setTitle("Lab 6 Tic Tac Toe");
        stage.show();
    }



    private void animateHoverIn(Button btn) {//reset btn
        ScaleTransition st = new ScaleTransition(Duration.millis(150), btn);
        st.setToX(1.07);
        st.setToY(1.07);
        st.setInterpolator(Interpolator.EASE_OUT);
        st.play();
    }

    private void animateHoverOut(Button btn) {
        ScaleTransition st = new ScaleTransition(Duration.millis(150), btn);
        st.setToX(1.00);
        st.setToY(1.00);
        st.setInterpolator(Interpolator.EASE_IN);
        st.play();
    }

    private void animateClick(Button btn) {
        FadeTransition ft = new FadeTransition(Duration.millis(200), btn);
        ft.setFromValue(0.3);
        ft.setToValue(1.0);
        ft.setInterpolator(Interpolator.EASE_BOTH);
        ft.play();
    }


    private void handleMove(int r, int c) {

        Position pos = new Position(Row.values()[r], Col.values()[c]);

        if (!game.getBoard().isEmptyAt(pos))
            return;

        PlayerToken token = game.playAt(pos);// place the token at pos

        boardButtons[r][c].setText(token.toString());//converts the token into text which is displayed

        Board.Status statusNow = game.getBoard().getStatus();//checks the current game state

        switch (statusNow) {
            case XWins -> {
                status.setText("X wins!");
                lockBoard();
            }
            case OWins -> {
                status.setText("O wins!");
                lockBoard();
            }
            case Draw -> {
                status.setText("Draw!");
                lockBoard();
            }
            default -> {
                status.setText("Next turn: " + game.getBoard().getNextTurnToken());
            }
        }
    }

    private void lockBoard() {
        for (Button[] row : boardButtons)
            for (Button b : row) {
                b.setDisable(true);
                b.setOpacity(0.35);  // fade all buttons
                b.setScaleX(1);      // make sure no cell stays shrunk
                b.setScaleY(1);
            }
    }


    public void resetGame() {

        game = new TicTacToeGame();


        for (Button[] row : boardButtons)
            for (Button b : row) {
                b.setText("");
                b.setDisable(false);   // enable all btns
                b.setOpacity(1.0);
                animateHoverOut(b);
            }

        status.setText("New game – X starts");
    }


    public static void main(String[] args) {
        launch(args);
    }
}
