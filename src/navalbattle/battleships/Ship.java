package navalbattle.battleships;

import navalbattle.battleships.Deck;

import java.util.Arrays;

public class Ship //суперкласс для всех типов кораблей
{
    private Deck[] decks; //массив палуб для каждого типа корабля свой
    private boolean isHorizontalPos = true; //ориентация корабля на карте

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

    public boolean isHorizontalPos() {
        return isHorizontalPos;
    }

    public void setHorizontalPos(boolean horizontalPos) {
        isHorizontalPos = horizontalPos;
    }

    @Override
    public String toString() {
        return "Ship{" +
                "decks=" + Arrays.toString(decks) +
                ", isHorizontalPos=" + isHorizontalPos +
                '}';
    }
}
