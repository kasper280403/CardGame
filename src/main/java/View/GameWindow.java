package View;

import Modules.GameLogic;
import javafx.animation.PauseTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.util.ArrayList;

public class GameWindow {

    private StackPane root;
    GameLogic gameLogic;
    private HBox cardBox;

    public GameWindow(Stage stage) {
        stage.setTitle("Card Game");

        gameLogic = new GameLogic();

        root = new StackPane();
        setBackground();

        cardBox = createCardHBox();
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

        Button handOutButton = new Button("Deal");
        handOutButton.setOnAction(e -> handOutCard());

        Button reshuffleButton = new Button("Reshuffle");
        reshuffleButton.setOnAction(e -> reshuffle());

        buttonBox.getChildren().addAll(handOutButton, reshuffleButton);
        return buttonBox;
    }

    private void handOutCard() {
        ArrayList<String> imgURL = gameLogic.deal(5);
        updateCardHBox(imgURL);

    }

    private void reshuffle() {
        // Implementeres senere
    }

    private void disableHandOutButton() {

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
        cardBox = new HBox(10);
        for (int i = 0; i <= 4; i++) {
            ImageView card = new ImageView(new Image("cards/back_light.png"));
            card.setFitHeight(150);
            card.setFitWidth(100);
            cardBox.getChildren().add(card);
        }
        cardBox.setStyle("-fx-alignment: center;");
        return cardBox;
    }

    private void updateCardHBox(ArrayList<String> imgURL) {
        cardBox.getChildren().clear();

        for (int i = 0; i < imgURL.size(); i++) {
            String url = imgURL.get(i);
            ImageView card = new ImageView(new Image("cards/back_dark.png"));
            card.setFitHeight(150);
            card.setFitWidth(100);
            cardBox.getChildren().add(card);

            TranslateTransition slide = new TranslateTransition(Duration.millis(300), card);
            slide.setFromY(-50);
            slide.setToY(0);

            PauseTransition pause = new PauseTransition(Duration.millis(200));

            ScaleTransition flip = new ScaleTransition(Duration.millis(300), card);
            flip.setFromX(1);
            flip.setToX(0);

            flip.setOnFinished(event -> {
                card.setImage(new Image(url));
                ScaleTransition flipBack = new ScaleTransition(Duration.millis(300), card);
                flipBack.setFromX(0);
                flipBack.setToX(1);
                flipBack.play();
            });

            SequentialTransition sequence = new SequentialTransition(slide, pause, flip);
            sequence.setDelay(Duration.millis(i * 400));
            sequence.play();
        }
    }

    private ImageView createDeckImage() {
        ImageView deckImage = new ImageView(new Image("cards/back_dark.png"));
        deckImage.setFitHeight(150);
        deckImage.setFitWidth(100);
        return deckImage;
    }
}
