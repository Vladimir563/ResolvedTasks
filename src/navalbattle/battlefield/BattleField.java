package navalbattle.battlefield;

import navalbattle.Deck;
import navalbattle.Ship;
import navalbattle.battleships.FourDecksShip;
import navalbattle.battleships.OneDeckShip;
import navalbattle.battleships.ThreeDecksShip;
import navalbattle.battleships.TwoDecksShip;
import navalbattle.shipsDescriptions;

import javax.swing.*;
import java.util.Arrays;
import java.util.Date;
import java.util.Random;

public class BattleField
{
    private OneDeckShip [] oneDeckShips = new OneDeckShip[4];
    private TwoDecksShip[] twoDeckShips = new TwoDecksShip[3];
    private ThreeDecksShip[] threeDeckShips = new ThreeDecksShip[2];
    private FourDecksShip[] fourDeckShips = new FourDecksShip[1];

    private int shipsCounter = 0;

    public boolean isDeckInCoord = false;

    public int getShipsCounter() {
        return shipsCounter;
    }

    public void setShipsCounter(int shipsCounter) {
        this.shipsCounter = shipsCounter;
    }

    public OneDeckShip[] getOneDeckShips() {
        return oneDeckShips;
    }

    public void setOneDeckShips(OneDeckShip[] oneDeckShips) {
        this.oneDeckShips = oneDeckShips;
    }

    public TwoDecksShip[] getTwoDeckShips() {
        return twoDeckShips;
    }

    public void setTwoDeckShips(TwoDecksShip[] twoDeckShips) {
        this.twoDeckShips = twoDeckShips;
    }

    public ThreeDecksShip[] getThreeDeckShips() {
        return threeDeckShips;
    }

    public void setThreeDeckShips(ThreeDecksShip[] threeDeckShips) {
        this.threeDeckShips = threeDeckShips;
    }

    public FourDecksShip[] getFourDeckShips() {
        return fourDeckShips;
    }

    public void setFourDeckShips(FourDecksShip[] fourDeckShips) {
        this.fourDeckShips = fourDeckShips;
    }

    public void fillShipArray(Ship [] ships) //заполняет массивы кораблями
    {

        for (int i = 0; i < ships.length; i++)
        {
            Random rand = new Random(new Date().getTime());
//todo: устанавливаем рандомно координаты первой палубы каждого корабля
            if(shipsDescriptions.ONEDECKSHIP.getShipNameENG().equals(ships.getClass().getSimpleName().substring(0,ships.getClass().getSimpleName().length()-2)))
            {
                ships[i] = new OneDeckShip(new Deck((2 + (int) (Math.random() * 9)), (2 + (int) (Math.random() * 9))));
            }

            if(shipsDescriptions.TWODECKSSHIP.getShipNameENG().equals(ships.getClass().getSimpleName().substring(0,ships.getClass().getSimpleName().length()-2)))
            {
                ships[i] = new TwoDecksShip(new Deck((2 + (int) (Math.random() * 9)), (2 + (int) (Math.random() * 9))), null);
            }

            if(shipsDescriptions.THREEDECKSSHIP.getShipNameENG().equals(ships.getClass().getSimpleName().substring(0,ships.getClass().getSimpleName().length()-2)))
            {
                ships[i] = new ThreeDecksShip(new Deck((2 + (int) (Math.random() * 9)), (2 + (int) (Math.random() * 9))), null, null);
            }

            if(shipsDescriptions.FOURDECKSSHIP.getShipNameENG().equals(ships.getClass().getSimpleName().substring(0,ships.getClass().getSimpleName().length()-2)))
            {
                ships[i] = new FourDecksShip(new Deck((2 + (int) (Math.random() * 9)), (2 + (int) (Math.random() * 9))), null, null,null);
            }

        }

    }

    public void fieldGenerate(int fieldWidth) //отрисовывает и создает поле битвы
    {
        fillShipArray(oneDeckShips);
        fillShipArray(twoDeckShips);
        fillShipArray(threeDeckShips);
        fillShipArray(fourDeckShips);

        Ship [] allShipsArray = new Ship[10];
        System.arraycopy(getOneDeckShips(),0,allShipsArray,0,getOneDeckShips().length);
        System.arraycopy(getTwoDeckShips(),0,allShipsArray,getOneDeckShips().length,getTwoDeckShips().length);
        System.arraycopy(getThreeDeckShips(),0,allShipsArray,getOneDeckShips().length+getTwoDeckShips().length,getThreeDeckShips().length);
        System.arraycopy(getFourDeckShips(),0,allShipsArray,getOneDeckShips().length + getTwoDeckShips().length + getThreeDeckShips().length,getFourDeckShips().length);

        for (int i = 1; i < 2; i++)
        {
            for (int j = 0; j < fieldWidth + 1; j++)
            {
                if(j == fieldWidth)
                {
                    System.out.print(" " + j);
                    continue;
                }
                System.out.printf("%2d ", j);
            }
            System.out.println();
        }

        for (int i = 0; i < fieldWidth + 1; i++)
        {
            if(i == 0) continue;

            for (int j = 1; j < fieldWidth + 2; j++)
            {
                isDeckInCoord = false;
                if(j <= 1)
                {
                    System.out.printf("%2d  ", i);
                }
                else
                {
                    for (int k = 0; k < allShipsArray.length; k++)
                    {
                        if(allShipsArray[k].getDecks()[0].getX()+1 == j && allShipsArray[k].getDecks()[0].getY() == i)
                        {
                            if(shipsDescriptions.ONEDECKSHIP.getShipNameENG().equals(allShipsArray[k].getClass().getSimpleName()))
                            {
                                System.out.print("1  ");
                            }

                            if(shipsDescriptions.TWODECKSSHIP.getShipNameENG().equals(allShipsArray[k].getClass().getSimpleName()))
                            {
                                System.out.print("2  ");
                            }

                            if(shipsDescriptions.THREEDECKSSHIP.getShipNameENG().equals(allShipsArray[k].getClass().getSimpleName()))
                            {
                                System.out.print("3  ");
                            }

                            if(shipsDescriptions.FOURDECKSSHIP.getShipNameENG().equals(allShipsArray[k].getClass().getSimpleName()))
                            {
                                System.out.print("4  ");
                            }

                            isDeckInCoord = true;
                            break;
                        }
                    }

                    if(!isDeckInCoord)
                    {
                        System.out.print("*  ");
                    }
                }
            }
            System.out.println();
        }

        System.out.println(Arrays.toString(allShipsArray));
    }
}
