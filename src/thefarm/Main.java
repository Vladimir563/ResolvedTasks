package thefarm;

import java.util.Arrays;

public class Main
{
    public static void main(String[] args)
    {
        Farm farm = new Farm();
        farm.printHomeAnimals();
        System.out.println(Arrays.toString(WildField.wildAnimals));
        System.out.println();
        farm.passDay(Farmer.getInstance(farm));
    }

    enum textColours
    {
        ANSI_RESET ("\u001B[0m"),
        ANSI_BLACK ("\u001B[30m"),
        ANSI_RED ("\u001B[31m"),
        ANSI_GREEN ("\u001B[32m"),
        ANSI_YELLOW ("\u001B[33m"),
        ANSI_BLUE ("\u001B[34m"),
        ANSI_PURPLE ("\u001B[35m"),
        ANSI_CYAN ("\u001B[36m"),
        ANSI_WHITE ("\u001B[37m");
        private String code;
        textColours(String s)
        {
            this.code = s;
        }
        public String getCode()
        {
            return code;
        }
    }

/*    1. На данный момент это не важно, но на будущее отметьте,
         что такой вариант singleton нельзя использовать в многопоточных средах.
      2. Создание объектов конкретных животных по rand.nextInt() правильно
         вынести из конструктора в отдельный статический фабричный метод,
         а в конструкторе его только вызывать
      3. Массив homeAnimals не корректно делать static
         (это характеристика объекта и предполагается, что на каждой ферме массив животных будет уникален).
      4. Мы их еще не смотрели, но для констант, которые у
         Вас собраны в Main классе отлично подойдет тип данных enum*/
}
