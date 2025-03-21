package View;

import Modules.GameLogic;
import javafx.animation.PauseTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.util.ArrayList;

public class GameWindow {

    private StackPane root;
    GameLogic gameLogic;
    private HBox cardBox;
    private HBox buttonBox;
    private VBox changeDeck;
    public VBox statusBox;

    public GameWindow(Stage stage) {
        stage.setTitle("Card Game");

        gameLogic = new GameLogic();

        root = new StackPane();
        setBackground();

        cardBox = createCardHBox();
        ImageView deckImage = createDeckImage();
        buttonBox = createButtonBox();

        statusBox = statusBox();
        statusBox.setAlignment(Pos.TOP_RIGHT);

        changeDeck = nDecks();
        StackPane.setAlignment(changeDeck, Pos.TOP_LEFT);


        VBox layout = new VBox(10,changeDeck,statusBox, deckImage, cardBox, buttonBox);
        layout.setStyle("-fx-alignment: center;");

        HBox finalLayer = new HBox(changeDeck, layout);

        root.getChildren().add(finalLayer);


        Scene scene = new Scene(root, 900, 500);
        scene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());

        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
        System.out.println(getClass().getResource("/style.css"));
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

        if(!imgURL.isEmpty()){
            updateCardHBox(imgURL);
            ArrayList<Object> obj = gameLogic.handStatus();
            changeStatusBox((Boolean) obj.getFirst(),(String) obj.get(1), (Boolean)obj.get(2), (int) obj.getLast());
        } else{
            outOfCards();
        }

    }

    private void reshuffle() {
        gameLogic.reShuffle();
        enableHandOutButton();
        handOutCard();
    }

    public void disableHandOutButton() {
        for (javafx.scene.Node node : buttonBox.getChildren()) {
            if (node instanceof Button && ((Button) node).getText().equals("Deal")) {
                node.setDisable(true);
            }
        }
    }

    public void enableHandOutButton() {
        for (javafx.scene.Node node : buttonBox.getChildren()) {
            if (node instanceof Button && ((Button) node).getText().equals("Deal")) {
                node.setDisable(false);
            }
        }
    }

    public void outOfCards(){
        disableHandOutButton();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Empty");
        alert.setHeaderText(null);
        alert.setContentText("Not enough cards to deal. You need to reshuffle.");
        alert.showAndWait();
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

    public void updateCardHBox(ArrayList<String> imgURL) {
        cardBox.getChildren().clear();
        disableHandOutButton();


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
            sequence.setOnFinished(event -> enableHandOutButton());
            sequence.play();
        }
    }

    private VBox nDecks(){
        VBox deckMenu = new VBox(10);
        Label label = new Label("Choose deck amount:");
        label.setStyle("-fx-text-fill: #ffcc00; -fx-font-size: 20px;");

        Button oneDeck = new Button("One");
        oneDeck.setOnAction(e -> {
            gameLogic.createDeck(1);
            reshuffle();
        });

        Button twoDecks = new Button("Two");
        twoDecks.setOnAction(e -> {
            gameLogic.createDeck(2);
            reshuffle();
        });

        Button threeDecks = new Button("Three");
        threeDecks.setOnAction(e -> {
            gameLogic.createDeck(3);
            reshuffle();
        });

        Button fourDecks = new Button("Four");
        fourDecks.setOnAction(e -> {
            gameLogic.createDeck(4);
            reshuffle();
        });

        oneDeck.setPrefWidth(100);
        twoDecks.setPrefWidth(100);
        threeDecks.setPrefWidth(100);
        fourDecks.setPrefWidth(100);

        deckMenu.getChildren().addAll(label, oneDeck, twoDecks, threeDecks, fourDecks);

        return deckMenu;
    }

    private ImageView createDeckImage() {
        ImageView deckImage = new ImageView(new Image("cards/back_dark.png"));
        deckImage.setFitHeight(150);
        deckImage.setFitWidth(100);
        return deckImage;
    }

    public VBox statusBox(){
        VBox statusBox = new VBox(10);

        Text flush = new Text("Flush: ");
        Text hearts = new Text("Hearts: ");
        Text spadeDame = new Text("Spade Dame: ");
        Text sum = new Text("Sum: ");

        flush.setStyle("-fx-text-fill: #ffcc00;\n -fx-font-size: 14px;");
        hearts.setStyle("-fx-text-fill: #ffcc00;\n -fx-font-size: 14px;");
        spadeDame.getStyleClass().add("status-text");
        sum.getStyleClass().add("status-text");

        statusBox.getChildren().addAll(flush, hearts, spadeDame, sum);

        return statusBox;
    }

    public void changeStatusBox(Boolean flush, String hearts, Boolean spadeDame, int sum) {

        Text flushT;
        if(flush){
            flushT = new Text("Flush: Yes");
        } else{
            flushT = new Text("Flush: No");
        }
        Text heartsT = new Text("Hearts: "+hearts);

        Text spadeDameT;
        if(spadeDame){
            spadeDameT = new Text("Spade Dame: Yes");
        } else{
            spadeDameT = new Text("Spade Dame: No");
        }

        Text sumT = new Text("Sum: "+sum);

        flushT.getStyleClass().add("status-text");
        heartsT.getStyleClass().add("status-text");
        spadeDameT.getStyleClass().add("status-text");
        sumT.getStyleClass().add("status-text");

        statusBox.getChildren().clear();
        statusBox.getChildren().addAll(flushT, heartsT, spadeDameT, sumT);

    }
}
