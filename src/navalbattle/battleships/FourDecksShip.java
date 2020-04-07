package navalbattle.battleships;

import navalbattle.Deck;
import navalbattle.Ship;
import navalbattle.shipsDescriptions;

public class FourDecksShip extends Ship
{
    public FourDecksShip(Deck deck1, Deck deck2, Deck deck3, Deck deck4)
    {
        super(deck1,deck2,deck3,deck4);
        setDescription(shipsDescriptions.FOURDECKSSHIP.getShipNameRUS());
    }
}
