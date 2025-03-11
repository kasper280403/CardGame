package View;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class GameWindow {

    private StackPane root;

    public GameWindow(Stage stage) {
        stage.setTitle("Card Game");

        root = new StackPane();
        setBackground();

        HBox cardBox = createCardHBox();
        ImageView deckImage = createDeckImage();
        HBox buttonBox = createButtonBox();

        VBox layout = new VBox(10, deckImage, cardBox, buttonBox);
        layout.setStyle("-fx-alignment: center;");
        layout.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());

        root.getChildren().add(layout);

        Scene scene = new Scene(root, 900, 500);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    private HBox createButtonBox() {
        HBox buttonBox = new HBox(20);
        buttonBox.setStyle("-fx-alignment: center;");

        Button handOutButton = new Button("Hand out card");
        handOutButton.setOnAction(e -> handOutCard());

        Button reshuffleButton = new Button("Reshuffle");
        reshuffleButton.setOnAction(e -> reshuffle());

        buttonBox.getChildren().addAll(handOutButton, reshuffleButton);
        return buttonBox;
    }

    private void handOutCard() {
        // Implementeres senere
    }

    private void reshuffle() {
        // Implementeres senere
    }

    private void setBackground() {
        Image backgroundImage = new Image("background.jpg");
        BackgroundImage background = new BackgroundImage(backgroundImage,
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER, new BackgroundSize(100, 100, true, true, false, true));
        root.setBackground(new Background(background));

        root.widthProperty().addListener((obs, oldVal, newVal) -> updateBackground());
        root.heightProperty().addListener((obs, oldVal, newVal) -> updateBackground());
    }

    private void updateBackground() {
        Image backgroundImage = new Image("background.jpg");
        BackgroundImage background = new BackgroundImage(backgroundImage,
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER, new BackgroundSize(100, 100, true, true, false, true));
        root.setBackground(new Background(background));
    }

    private HBox createCardHBox() {
        HBox hbox = new HBox(10);
        for (int i = 1; i <= 5; i++) {
            ImageView card = new ImageView(new Image("cards/clubs_" + i + ".png"));
            card.setFitHeight(150);
            card.setFitWidth(100);
            hbox.getChildren().add(card);
        }
        hbox.setStyle("-fx-alignment: center;");
        return hbox;
    }

    private ImageView createDeckImage() {
        ImageView deckImage = new ImageView(new Image("cards/back_dark.png"));
        deckImage.setFitHeight(150);
        deckImage.setFitWidth(100);
        return deckImage;
    }
}
