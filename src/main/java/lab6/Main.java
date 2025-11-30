package lab6;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Label;

public class Main extends Application {
    @Override
    public void start(Stage stage)  {
        var label = new Label("helo world");
        var scene = new Scene(label, 400, 300);
        stage.setScene(scene);
        stage.setTitle("my java fx");
        stage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
