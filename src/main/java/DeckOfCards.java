import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class DeckOfCards {


    ArrayList<String> suits = new ArrayList<>(Arrays.asList("spades", "hearts", "diamonds", "clubs"));
    ArrayList<Card> cards = new ArrayList<>();

    public DeckOfCards(int n) {
        for (int i = 0; i < n; i++) {
            for (int v = 1; v < 14; v++) {
                for (int s = 1; s < 4; s++) {
                    String imgURL = suits.get(s) + "_" + v + ".png";
                    Card card = new Card(v, suits.get(s), imgURL);
                    cards.add(card);
                }
            }
        }
    }

    public ArrayList<Card> getCards(int n) {
        ArrayList<Card> drawnCards = new ArrayList<>();
        if (this.cards.isEmpty()) {
            throw new IllegalArgumentException("Deck is empty");
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