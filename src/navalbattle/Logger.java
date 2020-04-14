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

    public void printVerticalPart(int fieldHeight, BattleField battleField, UserMove[] usersMoves, char hit, char miss) //выводит на консоль часть поля с цифровым обозначением
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

            for (int j = 0; j < fieldHeight; j++)
            {
                boolean isCellEmpty = true;
                for(Deck deck : battleField.getArrayOfFilledCells())
                {
                    if(deck.getX() == j + 1 && deck.getY() == i)
                    {
                        System.out.print("  x");
                        isCellEmpty = false;
                        break;
                    }
                }
                if(isCellEmpty) System.out.print("  *");
            }
            System.out.print("   ");
            System.out.println();
        }
    }

}
