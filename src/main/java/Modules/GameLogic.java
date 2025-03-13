package Modules;

import View.GameWindow;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class GameLogic {

    DeckOfCards deck;
    int currentAmountOfDecks = 0;;
    ArrayList<Card> currentHand;

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
        currentHand = cards;
        return imgURL;
    }

    public Boolean toFewCards(int numberOfCards) {
        return numberOfCards <= deck.cardsLeftInDeck();
    }

    public void reShuffle(){
        createDeck(currentAmountOfDecks);
    }


    public ArrayList<Object> handStatus() {

        ArrayList<Object> handStatus = new ArrayList<>();


        boolean flush = currentHand.stream()
                .map(card -> card.suit)
                .distinct()
                .count() == 1;

        List<String> hearts = currentHand.stream()
                 .filter(card -> "hearts".equals(card.suit))
                 .map(Card::toString)
                 .toList();

        String heartsString = String.join(", ", hearts);


        boolean spadeDame = false;
        int sum = 0;
        for (Card card : currentHand) {
            if (card.toString().equals("spades12")) {
                spadeDame = true;
            }
            sum += card.getValue();
        }


        handStatus.add(flush);
        handStatus.add(heartsString);
        handStatus.add(spadeDame);
        handStatus.add(sum);

        return handStatus;

    }


}
