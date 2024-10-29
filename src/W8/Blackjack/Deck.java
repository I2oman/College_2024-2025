package W8.Blackjack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Deck {
    private List<Card> cardList; // Use a List to store multiple cards

    public Deck() {
        cardList = new ArrayList<>();
        String[] ranks = { "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace" };
        String[] suits = { "Hearts", "Diamonds", "Clubs", "Spades" };

        // Create a standard deck of 52 cards
        for (String suit : suits) {
            for (int i = 0; i < ranks.length; i++) {
                int value;
                if (ranks[i].equals("Jack") || ranks[i].equals("Queen") || ranks[i].equals("King")) {
                    value = 10; // Face cards are worth 10
                } else if (ranks[i].equals("Ace")) {
                    value = 11; // Aces are initially valued at 11 (can be adjusted later)
                } else {
                    value = Integer.parseInt(ranks[i]); // Number cards have their face value
                }
                cardList.add(new Card(ranks[i], suit, value));
            }
        }

        shuffle(); // Shuffle the deck after creating it
    }

    // Shuffle the deck using Collections.shuffle
    private void shuffle() {
        Collections.shuffle(cardList);
    }

    public Card drawCard() {
        if (!cardList.isEmpty()) {
            return cardList.remove(cardList.size() - 1); // Remove and return the top card
        }
        return null; // Return null if the deck is empty
    }

    public int getSize() {
        return cardList.size();
    }

    @Override
    public String toString() {
        // return "Deck [cardList=" + cardList + "]";
        return "Deck:\n" + cardList.stream()
                .map(card -> "\t" + card.toString())
                .collect(Collectors.joining("\n"));
    }

}
