package Modules;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class DeckOfCards {


    ArrayList<String> suits = new ArrayList<>(Arrays.asList("spades", "hearts", "diamonds", "clubs"));
    ArrayList<Card> cards = new ArrayList<>();

    public DeckOfCards(int n) {
        for (int i = 0; i < n; i++) {
            for (int v = 1; v < 14; v++) {
                for (int s = 0; s < 4; s++) {
                    Card card = new Card(v, suits.get(s));
                    cards.add(card);
                }
            }
        }
    }

    public ArrayList<Card> getCards(int n) {
        ArrayList<Card> drawnCards = new ArrayList<>();
        if ( cardsLeftInDeck()< n) {
            return null;
        }

        Random random = new Random();
        for (int i = 0; i < n; i++) {
            int cardInt = random.nextInt(this.cards.size());
            drawnCards.add(cards.get(cardInt));
            cards.remove(cardInt);

        }
        return drawnCards;
    }

    public Integer cardsLeftInDeck() {
        return this.cards.size();
    }



}