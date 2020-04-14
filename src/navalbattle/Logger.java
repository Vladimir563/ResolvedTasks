package navalbattle;

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

    public void printVerticalPart(int fieldHeight) //выводит на консоль часть поля с цифровым обозначением
    {
        for (int i = 0; i < fieldHeight + 1; i++)
        {
            if(i == 0)
            {
                System.out.println();
                continue;
            }
            System.out.printf("%2s",i);
            for (int j = 0; j < fieldHeight; j++)
            {
                System.out.print("  *");
            }
            System.out.println();
        }
    }
}
