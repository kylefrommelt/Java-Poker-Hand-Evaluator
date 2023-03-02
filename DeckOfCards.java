// DeckOfCards class represents a deck of playing cards.
import java.security.SecureRandom;
import java.util.Arrays;

public class DeckOfCards {
    // random number generator
    private static final SecureRandom randomNumbers = new SecureRandom();
    private static final int NUMBER_OF_CARDS = 52; // constant # of Cards
    private Card[] deck = new Card[NUMBER_OF_CARDS]; // Card references
    private int currentCard = 0; // index of next Card to be dealt (0-51)

    // constructor fills deck of Cards
    public DeckOfCards() {
        String[] faces = {"Ace", "Deuce", "Three", "Four", "Five", "Six",
            "Seven", "Eight", "Nine", "Ten", "Jack", "Queen", "King"};
        String[] suits = {"Hearts", "Diamonds", "Clubs", "Spades"};

        // populate deck with Card objects
        for (int count = 0; count < deck.length; count++) {
            deck[count] = new Card(faces[count % 13], suits[count / 13]);
        }
    }

    public Card[] getDeck() {
        return deck;
    }

    // shuffle deck of cards
    public void shuffle() {
        // next call to method dealCard should start at deck[0] again
        currentCard = 0;

        // for each Card, pick another random Card and swap them
        for (int first = 0; first < deck.length; first++) {
            // select a random number between 0 and 51
            int second = randomNumbers.nextInt(NUMBER_OF_CARDS);

            // swap current Card with randomly selected Card
            Card temp = deck[first];
            deck[first] = deck[second];
            deck[second] = temp;
        }
    }

    // deal one card
    public Card dealCard() {
        // determine whether Cards remain to be dealt
        if (currentCard < deck.length) {
            return deck[currentCard++]; // return current Card in array
        }
        else {
            return null; // return null to indicate that all Cards were dealt
        }
    }

    // deal hand
    public Card[] dealHand() {
        Card[] hand = new Card[5];
        for (int i = 0; i < 5; i++) {
            hand[i] = dealCard();
        }
        return hand;
    }

    // check for pair
    public static boolean hasPair(Card[] hand) {
        for (int i = 0; i < hand.length - 1; i++) {
            for (int j = i + 1; j < hand.length; j++) {
                if (hand[i].getFace().equals(hand[j].getFace())) {
                    return true;
                }
            }
        }
        return false;
    }

    // check for two pairs
    public static boolean hasTwoPairs(Card[] hand) {
        int numPairs = 0;
        for (int i = 0; i < hand.length - 1; i++) {
            for (int j = i + 1; j < hand.length; j++) {
                if (hand[i].getFace().equals(hand[j].getFace())) {
                    numPairs++;
                }
            }
        }
        return numPairs == 2;
    }


    // check for three of a kind
    public static boolean hasThreeOfAKind(Card[] hand) {
        for (int i = 0; i < hand.length; i++) {
            int count = 0;
            for (int j = 0; j < hand.length; j++) {
                if (hand[i].getFace().equals(hand[j].getFace())) {
                    count++;
                }
            }
            if (count == 3) {
                return true;
            }
        }
        return false;
    }
    
    // check for four of a kind
    public boolean hasFourOfAKind(Card[] hand) {
        for (int i = 0; i < hand.length; i++) {
            int count = 0;
            for (int j = 0; j < hand.length; j++) {
                if (hand[i].getFace().equals(hand[j].getFace())) {
                    count++;
                }
            }
            if (count == 4) {
                return true;
            }
        }
        return false;
    }

    // check for flush
    public static boolean hasFlush(Card[] hand) {
        String suit = hand[0].getSuit();
        for (int i = 1; i < hand.length; i++) {
            if (!hand[i].getSuit().equals(suit)) {
                return false;
            }
        }
        return true;
    }

    // check for straight
    public static boolean hasStraight(Card[] hand) {
        Arrays.sort(hand);
        for (int i = 0; i < hand.length - 1; i++) {
            if (hand[i].getCardValue() != hand[i + 1].getCardValue() - 1) {
                return false;
            }
        }
        return true;
    }


    // check for full house
    public static boolean hasFullHouse(Card[] hand) {
        boolean hasThree = false;
        boolean hasPair = false;
    
        // Count the number of cards with the same face value
        int[] faceCount = new int[14];
        for (Card card : hand) {
            faceCount[card.getCardValue()]++;
        }
    
        // Check if there is a three of a kind and a pair
        for (int count : faceCount) {
            if (count == 3) {
                hasThree = true;
            } 
            else if (count == 2) {
                hasPair = true;
            }
        }
        return hasThree && hasPair;
    }


    public static int getRank(Card[] hand) {
        Arrays.sort(hand);
        boolean flush = hasFlush(hand);
        boolean straight = hasStraight(hand);
        boolean threeOfKind = hasThreeOfAKind(hand);
        boolean pair = hasPair(hand);
        boolean twoPair = hasTwoPairs(hand);
        boolean fullHouse = hasFullHouse(hand);
    
        if (flush && straight) {
            // straight flush
            return 9;
        } else if (fullHouse) {
            // full house
            return 8;
        } else if (flush) {
            // flush
            return 7;
        } else if (straight) {
            // straight
            return 6;
        } else if (threeOfKind) {
            // three of a kind
            return 5;
        } else if (twoPair) {
            // two pair
            return 4;
        } else if (pair) {
            // pair
            return 3;
        } else {
            // high card
            return 2;
        }
    }
}