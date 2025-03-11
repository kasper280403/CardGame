package Modules;

import java.util.ArrayList;

public class GameLogic {

    DeckOfCards deck;

    public GameLogic() {
        createDeck(1);
    }


    public void createDeck(int deckSize) {
        deck = new DeckOfCards(deckSize);
    }

    public ArrayList<String> deal(int numberOfCards) {
        ArrayList<String> imgURL = new ArrayList<>();
        ArrayList<Card> cards = deck.getCards(numberOfCards);

        for (Card card : cards) {
            imgURL.add(card.getImgURL());
        }

        return imgURL;
    }

}
