package navalbattle;

import navalbattle.battlefield.BattleField;
import navalbattle.battlefield.UserMove;
import navalbattle.battleships.Cell;

import javax.jws.soap.SOAPBinding;

public class Logger
{
    public void printHorizontalPartOfMap(int fieldWidth) //выводит на консоль часть поля с буквенным обозначением
    {
        char startSymbol;
        int counter = -1;
        for (int i = 0; i < fieldWidth + 1; i++)
        {
            if(i == 0)
            {
                System.out.print("    "); //буквы начинаются после пробела
                continue;
            }
            counter++;
            startSymbol = (char) (1072 + counter);
            if(startSymbol != 'й') //печатаем без буквы 'й'
            {
                System.out.print(startSymbol + "  ");
            }
            else
            {
                i--;
            }
        }
    }

    public void printMainPartOfMap(int fieldHeight, BattleField battleField, UserMove[] usersMoves, textColours playersHit, textColours opponentsHit)
    {
        for (int i = 0; i < fieldHeight + 1; i++)
        {
//todo: печать цифрового символа в начале строки
            if(i == 0)
            {
                System.out.println(" ");
                continue;
            }
            System.out.printf("%2s",i);
//todo: end

            //todo: печать основного поля (всех кораблей и выстрелов если они сделаны)
            for (int j = 0; j < fieldHeight; j++)
            {
                boolean isCellEmpty = true;
                for (int k = 0; k < usersMoves.length; k++)
                {
                    if(usersMoves[k] == null) break;
                    if(usersMoves[k].getUserMove().getX() == j + 1 && usersMoves[k].getUserMove().getY() == i)
                    {
                        if(isUserHit(usersMoves[k],battleField.getArrayOfFilledCells()))
                        {
                            if(usersMoves[k].isUserIsPlayer())
                            {
                                System.out.print(playersHit.getCode() + "  X" + textColours.ANSI_RESET.getCode());
                                isCellEmpty = false;
                                break;
                            }
                            System.out.print(opponentsHit.getCode() + "  X" + textColours.ANSI_RESET.getCode());
                        }
                        else
                        {
                            if(usersMoves[k].isUserIsPlayer())
                            {
                                System.out.print(playersHit.getCode() + "  О" + textColours.ANSI_RESET.getCode());
                                isCellEmpty = false;
                                break;
                            }
                            System.out.print(opponentsHit.getCode() + "  О" + textColours.ANSI_RESET.getCode());
                        }
                        isCellEmpty = false;
                    }
                }
//todo: печать * если не было ни попаданий ни промахов в данной клетке
                if(isCellEmpty) System.out.print("  *");
            }
            System.out.print("   ");
            System.out.println();
        }
    }

    public boolean isUserHit(UserMove userMove, Cell [] filledCells )
    {
        for(Cell cell : filledCells)
        {
            if(userMove.getUserMove().getX() == cell.getX() && userMove.getUserMove().getY() == cell.getY())
            {
                return true;
            }
        }
        return false;
    }
}

