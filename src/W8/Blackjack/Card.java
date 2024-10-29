package W8.Blackjack;

public class Card {
    private String rank;
    private String suit;
    private int value;
    private boolean hidden;

    public Card(String rank, String suit, int value) {
        this.rank = rank;
        this.suit = suit;
        this.value = value;
        this.hidden = false;
    }

    public int getValue() {
        if (hidden) {
            return 0;
        }
        return this.value;
    }

    public String getRank() {
        return this.rank;
    }

    public boolean isHidden() {
        return this.hidden;
    }

    public void hide() {
        this.hidden = true;
    }

    public void reveal() {
        this.hidden = false;
    }

    @Override
    public String toString() {
        // return "Card [rank=" + rank + ", suit=" + suit + ", value=" + value + ",
        // hidden=" + hidden + "]";
        return rank + " of " + suit;
    }

}
