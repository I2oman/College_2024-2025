package W8.Blackjack;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Hand {
    private List<Card> cards; // List to store the cards in the hand

    public Hand() {
        cards = new ArrayList<>(); // Initialize the card list
    }

    // Method to add a card to the hand
    public void addCard(Card card) {
        cards.add(card);
    }

    // Method to calculate the total value of the hand
    public int calculateValue() {
        int totalValue = 0;
        int acesCount = 0;

        for (Card card : cards) {
            totalValue += card.getValue();
            if (card.getRank().equals("Ace")) {
                acesCount++; // Count the number of Aces
            }
        }

        // Adjust for Aces: if total value exceeds 21 and there are Aces, reduce value
        // by 10 for each Ace
        while (totalValue > 21 && acesCount > 0) {
            totalValue -= 10;
            acesCount--;
        }

        return totalValue;
    }

    // Method to get the cards in the hand (for display purposes)
    public List<Card> getCards() {
        return cards;
    }

    public void cleanHand() {
        cards.clear();
    }

    public int amountOfCards() {
        return cards.size();
    }

    @Override
    public String toString() {
        // return "Hand [cards=" + cards + ", totalValue=" + calculateValue() + "]";
        return "Hand:\n" +
                cards.stream()
                        .map(card -> card.isHidden() ? "\t***Hidden***"
                                : "\t" + card
                                        .toString())
                        .collect(Collectors.joining("\n"))
                + "\nTotal value: " + calculateValue();
    }
}
