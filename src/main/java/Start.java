import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


public class Start extends Application {
    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Kortspill");
        primaryStage.setScene(new Scene(new StackPane(), 400, 300));
        primaryStage.show();
    }



    public static void main(String[] args) {
        launch(args);
    }
}
