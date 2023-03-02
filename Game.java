public class Game {

    public static void main(String[] args) {
        DeckOfCards deck = new DeckOfCards();
        deck.shuffle();
    
        Card[] hand1 = deck.dealHand();
        Card[] hand2 = deck.dealHand();
    
        System.out.println("Hand 1:");
        printHand(hand1);
    
        System.out.println("Hand 2:");
        printHand(hand2);
    
        String result = compareHands(hand1, hand2);
        System.out.println(result);
    }
    
    
    private static void printHand(Card[] hand) {
        for (Card card : hand) {
            System.out.println(card);
        }
        System.out.println();
    }

    public static String compareHands(Card[] hand1, Card[] hand2) {

        // check rankings for both hands
        int rank1 = DeckOfCards.getRank(hand1);
        int rank2 = DeckOfCards.getRank(hand2);
    
        // if both hands have the same ranking, compare by highest card
        if (rank1 == rank2) {
            Card highCard1 = getHighCard(hand1);
            Card highCard2 = getHighCard(hand2);
            if (highCard1.getRank() > highCard2.getRank()) {
                return "Hand 1 wins with " + getHandRankingName(rank1) + " and high card " + highCard1;
            } else if (highCard2.getRank() > highCard1.getRank()) {
                return "Hand 2 wins with " + getHandRankingName(rank2) + " and high card " + highCard2;
            } else {
                return "It's a tie with " + getHandRankingName(rank1) + " and high card " + highCard1;
            }
        }
    
        // compare rankings
        if (rank1 > rank2) {
            return "Hand 1 wins with " + getHandRankingName(rank1);
        } else {
            return "Hand 2 wins with " + getHandRankingName(rank2);
        }
    }
    
        
    private static String getHandRankingName(int rank) {
        switch (rank) {
            case 1:
                return "high card";
            case 2:
                return "pair";
            case 3:
                return "two pair";
            case 4:
                return "three of a kind";
            case 5:
                return "straight";
            case 6:
                return "flush";
            case 7:
                return "full house";
            case 8:
                return "four of a kind";
            default:
                return "";
        }
    }
    
    private static Card getHighCard(Card[] hand) {
        if (hand.length == 0) {
            return null;
        }
        Card highCard = hand[0];
        for (int i = 1; i < hand.length; i++) {
            if (hand[i].getRank() > highCard.getRank()) {
                highCard = hand[i];
            }
        }
        return highCard;
    }
}