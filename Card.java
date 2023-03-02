// Card class represents a playing card.
public class Card implements Comparable<Card> {
    private final String face; // face of card ("Ace", "Deuce", …)
    private final String suit; // suit of card ("Hearts", "Diamonds", …)
    private final int rank;

    // two-argument constructor initializes card's face and suit
    public Card(String cardFace, String cardSuit) {
        this.face = cardFace; // initialize face of card
        this.suit = cardSuit; // initialize suit of card
        this.rank = getCardValue(); // initialize rank of card
    }

    // return String representation of Card    
    public String toString() {
        return face + " of " + suit;    
    }

    // return face of the card
    public String getFace() {
        return face;
    }

    // return suit of the card
    public String getSuit() {
        return suit;
    }

    public int getRank() {
        return rank;
    }

    public int getCardValue() {
        int value = 0;
    
        switch (face) {
            case "Ace":
                value = 1;
                break;
            case "Two":
                value = 2;
                break;
            case "Three":
                value = 3;
                break;
            case "Four":
                value = 4;
                break;
            case "Five":
                value = 5;
                break;
            case "Six":
                value = 6;
                break;
            case "Seven":
                value = 7;
                break;
            case "Eight":
                value = 8;
                break;
            case "Nine":
                value = 9;
                break;
            case "Ten":
            case "Jack":
            case "Queen":
            case "King":
                value = 10;
                break;
            default:
                break;
        }
    
        return value;
    }

    @Override
    public int compareTo(Card otherCard) {
        if (this.rank < otherCard.rank) {
            return -1;
        } else if (this.rank > otherCard.rank) {
            return 1;
        } else {
            return 0;
        }
    }
}
