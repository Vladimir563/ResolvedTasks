package navalbattle.battleships;

import navalbattle.Deck;
import navalbattle.Ship;
import navalbattle.shipsDescriptions;

public class ThreeDecksShip extends Ship
{
    public ThreeDecksShip(Deck deck1, Deck deck2, Deck deck3)
    {
        super(deck1, deck2, deck3);
        setDescription(shipsDescriptions.THREEDECKSSHIP.getShipNameRUS());
    }
}
