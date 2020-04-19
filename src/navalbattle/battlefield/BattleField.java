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
    private Cell[] arrayOfFilledCells = new Cell[20]; //массив всех заполненных кораблями клеток карты
    private int fieldWidth; //размер поля

    public BattleField(int fieldWidth)
    {
        this.fieldWidth = fieldWidth;
//todo: создание массивов кораблей при создании обьекта поля битвы
        this.oneDeckShips = new OneDeckShip[]{new OneDeckShip(new Cell(0,0)), new OneDeckShip(new Cell(0,0)), new OneDeckShip(new Cell(0,0)), new OneDeckShip(new Cell(0,0))};
        this.twoDeckShips = new TwoDecksShip[]{new TwoDecksShip(new Cell(0,0),new Cell(0,0)),new TwoDecksShip(new Cell(0,0),new Cell(0,0)),new TwoDecksShip(new Cell(0,0),new Cell(0,0))};
        this.threeDeckShips = new ThreeDecksShip[]{new ThreeDecksShip(new Cell(0,0), new Cell(0,0), new Cell(0,0)),new ThreeDecksShip(new Cell(0,0), new Cell(0,0), new Cell(0,0))};
        this.fourDeckShips = new FourDecksShip[]{new FourDecksShip(new Cell(0,0),new Cell(0,0),new Cell(0,0), new Cell(0,0))};

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

    public Cell[] getArrayOfFilledCells() {
        return arrayOfFilledCells;
    }

    public void setArrayOfFilledCells(Cell[] arrayOfFilledCells) {
        this.arrayOfFilledCells = arrayOfFilledCells;
    }

    public Ship setupFirstDeckCords(Ship ship) //возвращает корабль с заданными координатами первой палубы
    {
        ship.getCells()[0].setX(2 + (int) (Math.random() * 9)); // задаем координаты Х и У первой палубы корабля
        ship.getCells()[0].setY(2 + (int) (Math.random() * 9));

        while (!isShipInFieldZone(ship)) //пока корабль не помещается повторять цикл, до того момента пока он не поместиться полностью по указанным координатам
        {
            ship.getCells()[0].setX(2 + (int) (Math.random() * 9));
            ship.getCells()[0].setY(2 + (int) (Math.random() * 9));
        }
        return ship;
    }

    public boolean isShipInFieldZone(Ship ship) //определяет помещается ли корабль по указанным координатам первой палубы
    {
        Random rand = new Random();
        if(((ship.getCells()[0].getX() + ship.getCells().length) <= fieldWidth + 1) && (((ship.getCells()[0].getY() + ship.getCells().length) <= fieldWidth + 1)))
//todo: если корабль помещается по обеим координатам
        {
            ship.setHorizontalPos(rand.nextBoolean()); //рандомно задаем положение корабля на карте
            return true;
        }
//todo: если корабль помещается только по Х - располагаем горизонтально
        else if((ship.getCells()[0].getX() + ship.getCells().length) <= fieldWidth + 1)
        {
            ship.setHorizontalPos(true);
            return true;
        }
//todo: если корабль помещается только по У - располагаем вертикально
        else if ((ship.getCells()[0].getY() + ship.getCells().length) <= fieldWidth + 1)
        {
            ship.setHorizontalPos(false);
            return true;
        }
        else return false;
    }

    public Ship setupCordsAllDecksInShip(Ship ship) // устанавливаем координаты для остальных палуб корабля
    {
        if(ship.isHorizontalPos()) //если корабль можно поставить горизонтально
        {
            for (int i = 1; i < ship.getCells().length; i++)
            {
                ship.getCells()[i].setX(ship.getCells()[0].getX() + i);
                ship.getCells()[i].setY(ship.getCells()[0].getY());
            }
        }
        else //иначе ставим корабль вертикально
        {
            for (int i = 1; i < ship.getCells().length; i++)
            {
                ship.getCells()[i].setY(ship.getCells()[0].getY() + i);
                ship.getCells()[i].setX(ship.getCells()[0].getX());
            }
        }
        return ship;
    }

    public void printUpdateBattleFieldAfterShoot(UserMove [] usersMoves, textColours playersHit, textColours opponentsHit, Logger logger) //вывод на консоль поля битвы после выстрела
    {
        logger.printHorizontalPartOfMap(getFieldWidth());
        logger.printMainPartOfMap(getFieldWidth(), this ,usersMoves,playersHit,opponentsHit);
    }

    public void fillAllShipsDecksCords() //назначает координаты палубам всех кораблей и сводит все занятые клетки в массив который возвращает
    {
        int counter = 0;
        boolean isExclusiveCoords;
        for (Ship ship : getAllTypesOfShips())
        {
            isExclusiveCoords = false;
            while (!isExclusiveCoords) // проверка на неповторимость координат
            {
                ship = setupFirstDeckCords(ship); //устанавливает координаты первой палубы (с проверкой может ли корабль поместиться вертикально или горизонтально)
                ship = setupCordsAllDecksInShip(ship); //устанавливает координаты для остальных палуб

                for(Cell cell : ship.getCells())
                {
                    if(isCellInArray(getArrayOfFilledCells(), cell)) //если массив занятых клеток содержит клетку в которую записана палуба нового корабля
                    {
                        isExclusiveCoords = false;
                        break;
                    }
                    else if(!isThisCellNearAnyShip(cell)) // проверка на несоприкасаемость кораблей
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

            for (Cell cell : ship.getCells()) //добавляем эксклюзивные координаты в массив занятых клеток
            {
                arrayOfFilledCells[counter] = cell;
                counter++;
            }
        }
    }

    public boolean isCellInArray(Cell[] arrayOfFilledCells, Cell cellWithCords) //проверяет есть ли данная клетка в массиве заполненных клеток
    {
        for(Cell cell : arrayOfFilledCells)
        {
            if(cell == null) return false;
            if(cell.getX() == cellWithCords.getX() && cell.getY() == cellWithCords.getY())
            {
                return true;
            }
        }
        return false;
    }

    public boolean isThisCellNearAnyShip(Cell cell) //проверяет не лежит ли точка вплотную к кораблю
    {
        for(Cell cellInArr : arrayOfFilledCells)
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
