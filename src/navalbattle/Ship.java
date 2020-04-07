package navalbattle;

import java.util.Arrays;

public class Ship
{
    private Deck [] decks;
    private String description;

    public Ship(Deck ... deck)
    {
        decks = deck;
    }

    public Deck[] getDecks() {
        return decks;
    }

    public void setDecks(Deck[] decks) {
        this.decks = decks;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Ship{" +
                "decks=" + Arrays.toString(decks) +
                ", description='" + description + '\'' +
                '}';
    }
}
