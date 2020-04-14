package navalbattle.battlefield;

import navalbattle.battleships.*;
import navalbattle.Logger;
import java.util.Arrays;
import java.util.Random;

public class BattleField
{
//todo: массивы всех кораблей
    private OneDeckShip [] oneDeckShips;
    private TwoDecksShip[] twoDeckShips;
    private ThreeDecksShip[] threeDeckShips;
    private FourDecksShip[] fourDeckShips;
//todo: end
    private Ship [] allTypesOfShips; //общий массив кораблей
    private int fieldWidth; //размер поля

    public BattleField(int fieldWidth)
    {
        this.fieldWidth = fieldWidth;
//todo: создание массивов кораблей при создании обьекта поля битвы
        this.oneDeckShips = new OneDeckShip[]{new OneDeckShip(new Deck(0,0)), new OneDeckShip(new Deck(0,0)), new OneDeckShip(new Deck(0,0)), new OneDeckShip(new Deck(0,0))};
        this.twoDeckShips = new TwoDecksShip[]{new TwoDecksShip(new Deck(0,0),new Deck(0,0)),new TwoDecksShip(new Deck(0,0),new Deck(0,0)),new TwoDecksShip(new Deck(0,0),new Deck(0,0))};
        this.threeDeckShips = new ThreeDecksShip[]{new ThreeDecksShip(new Deck(0,0), new Deck(0,0), new Deck(0,0)),new ThreeDecksShip(new Deck(0,0), new Deck(0,0), new Deck(0,0))};
        this.fourDeckShips = new FourDecksShip[]{new FourDecksShip(new Deck(0,0),new Deck(0,0),new Deck(0,0), new Deck(0,0))};

//todo: копирование всех кораблей в один массив
        this.allTypesOfShips = new Ship[oneDeckShips.length + twoDeckShips.length + threeDeckShips.length + fourDeckShips.length];
        System.arraycopy(oneDeckShips, 0, allTypesOfShips,0,oneDeckShips.length);
        System.arraycopy(twoDeckShips,0,allTypesOfShips,oneDeckShips.length,twoDeckShips.length);
        System.arraycopy(threeDeckShips,0,allTypesOfShips,oneDeckShips.length + twoDeckShips.length,threeDeckShips.length);
        System.arraycopy(fourDeckShips,0,allTypesOfShips,oneDeckShips.length + twoDeckShips.length + threeDeckShips.length, fourDeckShips.length);
    }

    public Ship[] getAllTypesOfShips() {
        return allTypesOfShips;
    }

    public void setAllTypesOfShips(Ship[] allTypesOfShips) {
        this.allTypesOfShips = allTypesOfShips;
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

    public int getFieldWidth() {
        return fieldWidth;
    }

    public Ship setupFirstDeckCoords(Ship ship) //возвращает корабль с заданными координатами первой палубы
    {
        ship.getDecks()[0].setX(2 + (int) (Math.random() * 9)); // задаем координаты Х и У первой палубы корабля
        ship.getDecks()[0].setY(2 + (int) (Math.random() * 9));

        while (!isShipInField(ship)) //пока корабль не помещается повторять цикл, до того момента пока он не поместиться полностью по указанным координатам
        {
            ship.getDecks()[0].setX(2 + (int) (Math.random() * 9));
            ship.getDecks()[0].setY(2 + (int) (Math.random() * 9));
        }
        return ship;
    }

    public boolean isShipInField(Ship ship) //определяет помещается ли корабль по указанным координатам первой палубы
    {
        Random rand = new Random();
        if(((ship.getDecks()[0].getX() + ship.getDecks().length) <= fieldWidth + 1) && (((ship.getDecks()[0].getY() + ship.getDecks().length) <= fieldWidth + 1)))
//todo: если корабль помещается по обеим координатам
        {
            ship.setHorizontalPos(rand.nextBoolean()); //рандомно задаем положение корабля на карте
            return true;
        }
//todo: если корабль помещается только по Х - располагаем горизонтально
        else if((ship.getDecks()[0].getX() + ship.getDecks().length) <= fieldWidth + 1)
        {
            ship.setHorizontalPos(true);
            return true;
        }
//todo: если корабль помещается только по У - располагаем вертикально
        else if ((ship.getDecks()[0].getY() + ship.getDecks().length) <= fieldWidth + 1)
        {
            ship.setHorizontalPos(false);
            return true;
        }
        else return false;
    }

    public Ship setupCoordsAllDecksInShip(Ship ship) // устанавливаем координаты для остальных палуб корабля
    {
        if(ship.isHorizontalPos()) //если корабль можно поставить горизонтально
        {
            for (int i = 1; i < ship.getDecks().length; i++)
            {
                ship.getDecks()[i].setX(ship.getDecks()[0].getX() + i);
                ship.getDecks()[i].setY(ship.getDecks()[0].getX());
            }
        }
        else //иначе ставим корабль вертикально
        {
            for (int i = 1; i < ship.getDecks().length; i++)
            {
                ship.getDecks()[i].setY(ship.getDecks()[0].getY() + i);
                ship.getDecks()[i].setY(ship.getDecks()[0].getY());
            }
        }
        return ship;
    }

    public void printBattleField() //вывод на консоль поля битвы
    {
        Logger logger = new Logger();
        logger.printHorizontalPart(getFieldWidth());
        logger.printVerticalPart(getFieldWidth());
    }


    @Override
    public String toString() {
        return "BattleField{" +
                "oneDeckShips=" + Arrays.toString(oneDeckShips) + "\n" +
                ", twoDeckShips=" + Arrays.toString(twoDeckShips) + "\n" +
                ", threeDeckShips=" + Arrays.toString(threeDeckShips) + "\n" +
                ", fourDeckShips=" + Arrays.toString(fourDeckShips) + "\n" +
                ", allTypesOfShips=" + Arrays.toString(allTypesOfShips) + "\n" +
                '}';
    }
}
