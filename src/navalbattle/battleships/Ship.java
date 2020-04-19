package navalbattle.battleships;

import java.util.Arrays;

public class Ship //суперкласс для всех типов кораблей
{
    private Cell[] cells; //массив палуб для каждого типа корабля свой
    private boolean isHorizontalPos = true; //ориентация корабля на карте

    public Ship(Cell... cell)
    {
        cells = cell;
    }

    public Cell[] getCells() {
        return cells;
    }

    public void setCells(Cell[] cells) {
        this.cells = cells;
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
                "decks=" + Arrays.toString(cells) +
                ", isHorizontalPos=" + isHorizontalPos +
                '}';
    }
}
