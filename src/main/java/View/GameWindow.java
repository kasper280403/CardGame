package View;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class GameWindow {

    private StackPane root;

    public GameWindow() {
        Stage stage = new Stage();
        stage.setTitle("Card Game");

        root = new StackPane();
        setBackground();

        //HBox cardBox = createCardHBox();
        //ImageView deckImage = createDeckImage();

        //VBox layout = new VBox(10, cardBox, deckImage);
        VBox layout = new VBox(10);
        layout.setStyle("-fx-alignment: center;");

        root.getChildren().add(layout);

        Scene scene = new Scene(root, 800, 600);
        stage.setScene(scene);
        stage.show();
    }

    private void setBackground() {
        Image backgroundImage = new Image("src/main/resources/background.jpg");
        BackgroundImage background = new BackgroundImage(backgroundImage,
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER, new BackgroundSize(100, 100, true, true, true, false));
        root.setBackground(new Background(background));
    }

    private HBox createCardHBox() {
        HBox hbox = new HBox(10);
        for (int i = 1; i <= 5; i++) {
            ImageView card = new ImageView(new Image("file:path/to/card" + i + ".png"));
            card.setFitHeight(120);
            card.setFitWidth(80);
            hbox.getChildren().add(card);
        }
        hbox.setStyle("-fx-alignment: center;");
        return hbox;
    }

    private ImageView createDeckImage() {
        ImageView deckImage = new ImageView(new Image("file:path/to/deck.png"));
        deckImage.setFitHeight(150);
        deckImage.setFitWidth(100);
        return deckImage;
    }
}
