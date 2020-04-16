package navalbattle;

import navalbattle.battlefield.BattleField;
import navalbattle.battlefield.UserMove;
import navalbattle.battleships.Deck;

public class Logger
{
    public void printHorizontalPart(int fieldWidth) //выводит на консоль часть поля с буквенным обозначением
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

    public void printVerticalPart(int fieldHeight, BattleField battleField, UserMove[] usersMoves, textColours playersHit, textColours opponentsHit) //выводит на консоль часть поля с цифровым обозначением
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

                for(UserMove userMove : usersMoves)
                {
                    if(userMove == null) break;
                    if(userMove.getUserMove().getX() == j + 1 && userMove.getUserMove().getY() == i)
                    {
                        for(Deck cell : battleField.getArrayOfFilledCells())
                        {
//todo: отмечает на поле попадание пользователя
                            if(cell.getX() == userMove.getUserMove().getX() && cell.getY() == userMove.getUserMove().getY()) //если клетка куда пользователь выстрелил содержит палубу корабля
                            {
                                if(userMove.isUserIsPlayer()) //если попал игрок, отмечает на поле попадание игрока
                                {
                                    System.out.print(playersHit.getCode() + "  X" + textColours.ANSI_RESET.getCode());
                                }
                                else if (!userMove.isUserIsPlayer())
                                {
                                    System.out.print(opponentsHit.getCode() + "  X" + textColours.ANSI_RESET.getCode());
                                }
                                isCellEmpty = false;
                                break;
                            }
//todo: отмечает на поле промах пользователя
                            else if(cell.getX() != userMove.getUserMove().getX() && cell.getY() != userMove.getUserMove().getY())
                            {
                                if(userMove.isUserIsPlayer()) //если непопал игрок, отмечает на поле промах игрока
                                {
                                    System.out.print(textColours.ANSI_WHITE.getCode() + "  X" + textColours.ANSI_RESET.getCode());
                                }
                                else
                                {
                                    System.out.print(textColours.ANSI_BLACK.getCode() + "  Х" + textColours.ANSI_RESET.getCode());
                                }
                                isCellEmpty = false;
                                break;
                            }
                        }
                    }
                }

//todo: печать * если не было ни попаданий ни промахов в данной клетке
                if(isCellEmpty) System.out.print("  *");
            }
            System.out.print("   ");
            System.out.println();
        }
    }
}
