package navalbattle.battlefield;

import navalbattle.*;
import navalbattle.battleships.FourDecksShip;
import navalbattle.battleships.OneDeckShip;
import navalbattle.battleships.ThreeDecksShip;
import navalbattle.battleships.TwoDecksShip;

import java.util.Date;
import java.util.Random;

public class BattleField
{
    private OneDeckShip [] oneDeckShips = new OneDeckShip[4];
    private TwoDecksShip[] twoDeckShips = new TwoDecksShip[3];
    private ThreeDecksShip[] threeDeckShips = new ThreeDecksShip[2];
    private FourDecksShip[] fourDeckShips = new FourDecksShip[1];
    private Ship [] shipsWithSpecialCoords = new Ship[10];
    private Ship [] allShipsArray = new Ship[10];
    private Deck [] decksOccupiedArr = new Deck[20];
    private int fieldWidth = 10;

    private int i = 1;
    private int j = 1;

    public int getFieldWidth() {
        return fieldWidth;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public int getJ() {
        return j;
    }

    public void setJ(int j) {
        this.j = j;
    }

    private int shipsCounter = 0;

    public boolean isDeckInCoord = false;

    public Ship[] getAllShipsArray() {
        return allShipsArray;
    }

    public void setAllShipsArray(Ship[] allShipsArray) {
        this.allShipsArray = allShipsArray;
    }

    public Ship[] getShipsWithSpecialCoords() {
        return shipsWithSpecialCoords;
    }

    public void setShipsWithSpecialCoords(Ship[] shipsWithSpecialCoords) {
        this.shipsWithSpecialCoords = shipsWithSpecialCoords;
    }

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
                ships[i] = new TwoDecksShip(new Deck((2 + (int) (Math.random() * 9)), (2 + (int) (Math.random() * 9))), new Deck(0,0));
            }

            if(shipsDescriptions.THREEDECKSSHIP.getShipNameENG().equals(ships.getClass().getSimpleName().substring(0,ships.getClass().getSimpleName().length()-2)))
            {
                ships[i] = new ThreeDecksShip(new Deck((2 + (int) (Math.random() * 9)), (2 + (int) (Math.random() * 9))), new Deck(0,0), new Deck(0,0));
            }

            if(shipsDescriptions.FOURDECKSSHIP.getShipNameENG().equals(ships.getClass().getSimpleName().substring(0,ships.getClass().getSimpleName().length()-2)))
            {
                ships[i] = new FourDecksShip(new Deck((2 + (int) (Math.random() * 9)), (2 + (int) (Math.random() * 9))), new Deck(0,0), new Deck(0,0),new Deck(0,0));
            }

        }

    }

    public void fieldGenerate() //отрисовывает и создает поле битвы
    {
        fillShipArray(oneDeckShips);
        fillShipArray(twoDeckShips);
        fillShipArray(threeDeckShips);
        fillShipArray(fourDeckShips);

        System.arraycopy(getOneDeckShips(),0,allShipsArray,0,getOneDeckShips().length);
        System.arraycopy(getTwoDeckShips(),0,allShipsArray,getOneDeckShips().length,getTwoDeckShips().length);
        System.arraycopy(getThreeDeckShips(),0,allShipsArray,getOneDeckShips().length+getTwoDeckShips().length,getThreeDeckShips().length);
        System.arraycopy(getFourDeckShips(),0,allShipsArray,getOneDeckShips().length + getTwoDeckShips().length + getThreeDeckShips().length,getFourDeckShips().length);

        makeShipsArrWithCorrectCoords(getAllShipsArray());
        System.out.println("________________________________");
        printField();
        System.out.println("________________________________");
    }

    public Ship getShipWithCorrectCoords(Ship battleShip) //возвращает корабль с корректными координатами (если нельзя построить корабль с текущими координатами)
    {
        Random rand = new Random(new Date().getTime());

        boolean isCoordsWrong = true;

        while(isCoordsWrong) //проверяем корректны ли координаты заданные рандомно
        {
            if((battleShip.getDecks()[0].getX() + (battleShip.getDecks().length - 1)) <= getFieldWidth()) // если по координате х есть место для остальных палуб корабля
            {
                int counterX = 0;
                for(Deck deck : battleShip.getDecks())
                {
                    deck.setX(battleShip.getDecks()[0].getX() + counterX);
                    deck.setY(battleShip.getDecks()[0].getY());
                    counterX++;
                }
                isCoordsWrong = false;
            }
            else if ((battleShip.getDecks()[0].getY() + (battleShip.getDecks().length - 1)) <= getFieldWidth()) // иначе если по координате у есть место для остальных палуб корабля
            {
                int counterY = 0;
                for(Deck deck : battleShip.getDecks())
                {
                    deck.setX(battleShip.getDecks()[0].getX());
                    deck.setY(battleShip.getDecks()[0].getY() + counterY);
                    counterY++;
                }
                isCoordsWrong = false;
            }
            else
            {
                battleShip.getDecks()[0].setX(2 + (int) Math.random() * 9);
                battleShip.getDecks()[0].setY(2 + (int) Math.random() * 9);
            }
        }
        return battleShip;
    }

    public void makeShipsArrWithCorrectCoords(Ship [] battleShips) // возвращает массив кораблей с непересекающимися корректными для построения координатами
    {
        Ship [] newArrShips = new Ship[battleShips.length];

        newArrShips [battleShips.length - 1] = getShipWithCorrectCoords(battleShips[battleShips.length - 1]);
        System.arraycopy(newArrShips [battleShips.length - 1].getDecks(),0,decksOccupiedArr,0,newArrShips [battleShips.length - 1].getDecks().length);
        int counter = 0;
        int tryingToFindCoordCount = 0;
        for (int i = newArrShips.length - 2; i >= 0; i--)
        {
            newArrShips[i] = getShipWithCorrectCoords(battleShips[i]);

            for(int k = 0; k < newArrShips[i].getDecks().length; k++)
            {
                mark:
                if(isDeckContainsInArr(newArrShips[i].getDecks()[k],decksOccupiedArr))
                {
                    newArrShips[i] = getShipWithRandomCoords(newArrShips[i], tryingToFindCoordCount);
                    newArrShips[i] = getShipWithCorrectCoords(battleShips[i]);
                    tryingToFindCoordCount++;
                    k = -1;
                    break mark;
                }
            }
            tryingToFindCoordCount = 0;
            setJ(1);
            setI(1);
            counter += newArrShips [i+1].getDecks().length;
            System.arraycopy(newArrShips [i].getDecks(),0,decksOccupiedArr,counter,newArrShips [i].getDecks().length);
        }
    }

    public Ship getShipWithRandomCoords(Ship ship, int tryingToFindCoordCount)
    {

        if(tryingToFindCoordCount > 2) //если количество смен координат слишком большое, начинаем перебирать все ячейки поля
        {
            for ( i = this.getI(); i < 11; i++)
            {
                for (j = this.getJ(); j < 11; j++)
                {
                    ship.getDecks()[0].setX(j);
                    ship.getDecks()[0].setY(i);

                    setJ(getJ()+1);

                    if(getJ() >= 10)
                    {
                        setI(getI()+1);
                        setJ(1);
                    }

                    for (int k = 1; k < ship.getDecks().length; k++)
                    {
                        ship.getDecks()[k].setX(0);
                        ship.getDecks()[k].setY(0);
                    }

                    return ship;
                }
            }
            return ship;
        }

        ship.getDecks()[0].setX(2 + (int) (Math.random() * 9));
        ship.getDecks()[0].setY(2 + (int) (Math.random() * 9));

        for (int k = 1; k < ship.getDecks().length; k++)
        {
            ship.getDecks()[k].setX(0);
            ship.getDecks()[k].setY(0);
        }
        return ship;
    }

    public boolean isDeckContainsInArr(Deck deck, Deck [] decks)
    {
        for(Deck deck1 : decks)
        {
            if(deck1 == null) break;
            if(deck.getX() == deck1.getX() && deck.getY() == deck1.getY())
            {
                return true;
            }
        }
        return false;
    }

    public void showCurrentShipsOnMap(Ship [] ships, char symbol, String shootColor) //распечатывает поле с расположением кораблей
    {
        for (int i = 1; i < 2; i++)
        {
            for (int j = 0; j < getFieldWidth() + 1; j++)
            {
                if(j == getFieldWidth())
                {
                    System.out.print(" " + j);
                    continue;
                }
                System.out.printf("%2d ", j);
            }
            System.out.println();
        }

        for (int i = 0; i < getFieldWidth() + 1; i++)
        {
            if(i == 0) continue;

            for (int j = 1; j < getFieldWidth() + 2; j++)
            {
                isDeckInCoord = false;
                if(j <= 1)
                {
                    System.out.printf("%2d  ", i);
                }
                else
                {
                    for (int k = 0; k < ships.length; k++)
                    {
                        for(Deck deck : ships[k].getDecks())
                        {
                            if(deck == null) continue;
                            if(deck.getX()  == j-1 && deck.getY() == i)
                            {
                                if(shipsDescriptions.SHIP.getShipNameENG().equals(ships[k].getClass().getSimpleName()))
                                {
                                    System.out.print(shootColor + symbol + "  " + textColours.ANSI_RESET.getCode());
                                }

                                if(shipsDescriptions.ONEDECKSHIP.getShipNameENG().equals(ships[k].getClass().getSimpleName()))
                                {
                                    System.out.print(textColours.ANSI_BLUE.getCode() + symbol + "  " + textColours.ANSI_RESET.getCode());
                                }

                                if(shipsDescriptions.TWODECKSSHIP.getShipNameENG().equals(ships[k].getClass().getSimpleName()))
                                {
                                    System.out.print(textColours.ANSI_CYAN.getCode() + symbol + "  " + textColours.ANSI_RESET.getCode());
                                }

                                if(shipsDescriptions.THREEDECKSSHIP.getShipNameENG().equals(ships[k].getClass().getSimpleName()))
                                {
                                    System.out.print(textColours.ANSI_YELLOW.getCode() + symbol + "  " + textColours.ANSI_RESET.getCode());
                                }

                                if(shipsDescriptions.FOURDECKSSHIP.getShipNameENG().equals(ships[k].getClass().getSimpleName()))
                                {
                                    System.out.print(textColours.ANSI_GREEN.getCode() + symbol + "  " + textColours.ANSI_RESET.getCode());
                                }

                                isDeckInCoord = true;
                                break;
                            }
                        }
                    }

                    if(!isDeckInCoord)
                    {
                        System.out.print("●  ");
                    }
                }
            }
            System.out.println();
        }
    }

    public void printField() // распечатывает пустое поле
    {
        for (int i = 1; i < 2; i++)
        {
            for (int j = 0; j < getFieldWidth() + 1; j++)
            {
                if(j == getFieldWidth())
                {
                    System.out.print(" " + j);
                    continue;
                }
                System.out.printf("%2d ", j);
            }
            System.out.println();
        }

        for (int k = 1; k < getFieldWidth() + 1; k++)
        {
            for (int j = 1; j < getFieldWidth() + 2; j++)
            {
                if(j <= 1)
                {
                    System.out.printf("%2d  ", k);
                }
                else
                {
                    System.out.print("●  ");
                }
            }
            System.out.println();
        }
    }
}
