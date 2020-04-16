package navalbattle.battlefield;

import navalbattle.battleships.*;
import navalbattle.Logger;
import navalbattle.textColours;

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
    private Deck [] arrayOfFilledCells = new Deck[20]; //массив всех заполненных (не пустых) клеток карты
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
//todo: копируем корабли начиная с 4х-палубного (для лучшей производительности)
        System.arraycopy(fourDeckShips,0,allTypesOfShips,0, fourDeckShips.length);
        System.arraycopy(threeDeckShips,0,allTypesOfShips,fourDeckShips.length,threeDeckShips.length);
        System.arraycopy(twoDeckShips,0,allTypesOfShips,fourDeckShips.length + threeDeckShips.length,twoDeckShips.length);
        System.arraycopy(oneDeckShips, 0, allTypesOfShips,fourDeckShips.length + threeDeckShips.length + twoDeckShips.length,oneDeckShips.length);
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

    public Deck[] getArrayOfFilledCells() {
        return arrayOfFilledCells;
    }

    public void setArrayOfFilledCells(Deck[] arrayOfFilledCells) {
        this.arrayOfFilledCells = arrayOfFilledCells;
    }

    public Ship setupFirstDeckCoords(Ship ship) //возвращает корабль с заданными координатами первой палубы
    {
        ship.getDecks()[0].setX(2 + (int) (Math.random() * 9)); // задаем координаты Х и У первой палубы корабля
        ship.getDecks()[0].setY(2 + (int) (Math.random() * 9));

        while (!isShipInFieldZone(ship)) //пока корабль не помещается повторять цикл, до того момента пока он не поместиться полностью по указанным координатам
        {
            ship.getDecks()[0].setX(2 + (int) (Math.random() * 9));
            ship.getDecks()[0].setY(2 + (int) (Math.random() * 9));
        }
        return ship;
    }

    public boolean isShipInFieldZone(Ship ship) //определяет помещается ли корабль по указанным координатам первой палубы
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
                ship.getDecks()[i].setY(ship.getDecks()[0].getY());
            }
        }
        else //иначе ставим корабль вертикально
        {
            for (int i = 1; i < ship.getDecks().length; i++)
            {
                ship.getDecks()[i].setY(ship.getDecks()[0].getY() + i);
                ship.getDecks()[i].setX(ship.getDecks()[0].getX());
            }
        }
        return ship;
    }

    public void printUpdateBattleFieldAfterShoot(UserMove [] usersMoves, textColours playersHit, textColours opponentsHit) //вывод на консоль поля битвы после выстрела
    {
        Logger logger = new Logger();
        logger.printHorizontalPart(getFieldWidth());
        logger.printVerticalPart(getFieldWidth(), this ,usersMoves,playersHit,opponentsHit);
    }

    public Deck [] fillAllShipsDecksCoords() //назначает координаты палубам всех кораблей и сводит все занятые клетки в массив который возвращает
    {
        int counter = 0;
        boolean isExclusiveCoords;
        for (Ship ship : getAllTypesOfShips())
        {
            isExclusiveCoords = false;
            while (!isExclusiveCoords) // проверка на неповторимость координат
            {
                ship = setupFirstDeckCoords(ship); //устанавливает координаты первой палубы (с проверкой может ли корабль поместиться вертикально или горизонтально)
                ship = setupCoordsAllDecksInShip(ship); //устанавливает координаты для остальных палуб

                for(Deck deck : ship.getDecks())
                {
                    if(isCellInArray(getArrayOfFilledCells(),deck)) //если массив занятых клеток содержит клетку в которую записана палуба нового корабля
                    {
                        isExclusiveCoords = false;
                        break;
                    }
                    else if(!isThisCellNearAnyShip(deck)) // проверка на несоприкасаемость кораблей
                    {
                        isExclusiveCoords = true;
                    }
                    else
                    {
                        isExclusiveCoords = false;
                        break;
                    }
                }
            }

            for (Deck deck : ship.getDecks()) //добавляем эксклюзивные координаты в массив занятых клеток
            {
                arrayOfFilledCells[counter] = deck;
                counter++;
            }
        }
        return arrayOfFilledCells;
    }

    public boolean isCellInArray(Deck [] arrayOfFilledCells, Deck deckWithCoords) //проверяет есть ли данная клетка в массиве заполненных клеток
    {
        for(Deck deck : arrayOfFilledCells)
        {
            if(deck == null) return false;
            if(deck.getX() == deckWithCoords.getX() && deck.getY() == deckWithCoords.getY())
            {
                return true;
            }
        }
        return false;
    }

    public boolean isThisCellNearAnyShip(Deck cell) //проверяет не лежит ли точка вплотную к кораблю
    {
        for(Deck cellInArr : arrayOfFilledCells)
        {
            if (cellInArr == null) return false;
            if((cell.getX() - 1) == cellInArr.getX() && cell.getY() == cellInArr.getY())
            {
                return true;
            }

            if((cell.getX() - 1) == cellInArr.getX() && (cell.getY() - 1) == cellInArr.getY())
            {
                return true;
            }

            if(cell.getX() == cellInArr.getX() && (cell.getY() - 1) == cellInArr.getY())
            {
                return true;
            }

            if(cell.getX() + 1 == cellInArr.getX() && cell.getY() - 1 == cellInArr.getY())
            {
                return true;
            }

            if(cell.getX() + 1 == cellInArr.getX() && cell.getY() == cellInArr.getY())
            {
                return true;
            }

            if(cell.getX() + 1 == cellInArr.getX() && cell.getY() + 1 == cellInArr.getY())
            {
                return true;
            }

            if(cell.getX() == cellInArr.getX() && cell.getY() + 1 == cellInArr.getY())
            {
                return true;
            }

            if(cell.getX() - 1 == cellInArr.getX() && cell.getY() + 1 == cellInArr.getY())
            {
                return true;
            }

            if(cell.getX() == cellInArr.getX() && cell.getY() == cellInArr.getY())
            {
                return true;
            }
        }
        return false;
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
