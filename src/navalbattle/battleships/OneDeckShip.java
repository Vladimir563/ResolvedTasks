package navalbattle.battleships;

import navalbattle.Deck;
import navalbattle.Ship;
import navalbattle.shipsDescriptions;

public class OneDeckShip extends Ship
{
    public OneDeckShip(Deck deck)
    {
        super(deck);
        setDescription(shipsDescriptions.ONEDECKSHIP.getShipNameRUS());
    }
}
