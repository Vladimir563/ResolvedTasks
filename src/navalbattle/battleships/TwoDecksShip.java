package navalbattle.battleships;

import navalbattle.Deck;
import navalbattle.Ship;
import navalbattle.shipsDescriptions;

public class TwoDecksShip extends Ship
{
    public TwoDecksShip(Deck deck1, Deck deck2)
    {
        super(deck1, deck2);
        setDescription(shipsDescriptions.TWODECKSSHIP.getShipNameRUS());
    }
}
