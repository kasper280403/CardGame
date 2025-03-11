import View.GameWindow;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;


public class Start extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Card Game");
        Button button = new Button("Play");
        button.setOnAction(e -> {
            new GameWindow();
        });
        Scene scene = new Scene(button, 300, 200);;
        primaryStage.setScene(scene);
        primaryStage.show();


    }


    public static void main(String[] args) {
        launch(args);
    }
}
