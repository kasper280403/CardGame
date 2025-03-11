public class Card {

    int value;
    String suit;
    String imgURL;

    public Card(int value, String suit, String imgURL) {
        this.value = value;
        this.suit = suit;
        this.imgURL = imgURL;
    }

    public int getValue() {
        return value;
    }

    public String getSuit() {
        return suit;
    }

    public String getImgURL() {
        return imgURL;
    }

    @Override
    public String toString() {
        return String.valueOf(suit) + value;
    }
}
