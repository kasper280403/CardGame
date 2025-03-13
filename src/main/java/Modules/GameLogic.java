package Modules;

import View.GameWindow;
import javafx.stage.Stage;

import java.util.ArrayList;

public class GameLogic {

    DeckOfCards deck;
    int currentAmountOfDecks = 0;;

    public GameLogic() {
        createDeck(1);
    }


    public void createDeck(int deckSize) {
        deck = new DeckOfCards(deckSize);
        currentAmountOfDecks = deckSize;
    }

    public ArrayList<String> deal(int numberOfCards) {
        ArrayList<String> imgURL = new ArrayList<>();
        ArrayList<Card> cards = deck.getCards(numberOfCards);
        if (cards == null || cards.size() == 0) {
            return imgURL;
        }
        for (Card card : cards) {
            imgURL.add(card.getImgURL());
        }
        return imgURL;
    }

    public Boolean toFewCards(int numberOfCards) {
        return numberOfCards <= deck.cardsLeftInDeck();
    }

    public void reShuffle(){
        createDeck(currentAmountOfDecks);
    }


}
