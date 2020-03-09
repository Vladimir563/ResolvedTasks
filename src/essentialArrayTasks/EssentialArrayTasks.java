package essentialArrayTasks;

import java.awt.image.AreaAveragingScaleFilter;
import java.util.Arrays;
import java.util.Scanner;

public class EssentialArrayTasks
{
    public static void main(String[] args)
    {
//      1.Найти наибольший общий делитель (НОД) двух чисел
        System.out.println("Task1:\n");
        int a = (int)(Math.random() * 100) + 1;
        int b = (int)(Math.random() * 100) + 1;
        System.out.printf("a = %d, b = %d", a,b);

        for (int i = 9; i > 0; i--)
        {
            if(a % i == b % i && a % i == 0)
            {
                System.out.printf("\nНаибольший общий делитель %d и %d = %d\n",a,b,i);
                break;
            }
        }

/*      2.Дан массив целых чисел. Массив не отсортирован, числа могут повторяться.
        Необходимо найти в данном массиве такие два числа n и m, чтобы их сумма была равна 7.
        Например, 2 + 5 = 7, 6 + 1 = 7, -2 + 9 = 7. Постарайтесь решить задачу наиболее оптимальным способом*/
        System.out.println("\nTask2:\n");
        int [] arr = new int [10];
        for (int i = 0; i < arr.length ; i++)
        {
            arr[i] = (int) (Math.random() * 10) + 1;
        }
        System.out.println("Сгенерированный массив:");
        System.out.println(Arrays.toString(arr));
        endLabel:for (int i = 0; i < arr.length; i++)
        {
            for (int j = 0; j < arr.length - 1; j++)
            {
                if(arr[i] + arr[j] == 7)
                {
                    System.out.printf("Числа с индексами %d и %d дают в сумме 7\n",i,j);
                    break endLabel;
                }
            }
        }

//      3.Заполните массив на N элементов случайным числами и выведете максимальное, минимальное и среднее значение.
        System.out.println("\nTask3:\n");
        int arrLenght = (int) (Math.random() * 10) + 1;
        int [] randomArr = new int[arrLenght];
        int sumArr = 0;
        for (int i = 0; i < randomArr.length; i++)
        {
            randomArr[i] = (int) (Math.random() * 100);
            sumArr += randomArr[i];
        }
        System.out.println(Arrays.toString(randomArr));
        Arrays.sort(randomArr);
        System.out.println(Arrays.toString(randomArr));
        System.out.printf("Min = %d \nMiddle(среднее арифметическое) = %d \nMax = %d\n",randomArr[0],sumArr/randomArr.length,randomArr[randomArr.length-1]);

   /*   4.Пользователь вводит с клавиатуры натуральное число большее 3, которое сохраняется в переменную n.
        Если пользователь ввёл не подходящее число, то программа должна просить пользователя повторить ввод.
        Создать массив из n случайных целых чисел из отрезка [0;n] и вывести его на экран.
        Создать второй массив только из чётных элементов первого массива, если они там есть, и вывести его в консоль*/
        System.out.println("\nTask4:\n");
        Scanner in = new Scanner(System.in);
        int usersNum;
        for(;;)
        {
            System.out.print("Введите размер массива: ");
            usersNum = in.nextInt();
            if(usersNum < 3)
            {
                System.out.printf("Число %d меньше 3, повторите ввод\n",usersNum);
            }
            else break;
        }
        int [] usersArray = new int [usersNum];
        for (int i = 0; i < usersArray.length ; i++)
        {
            usersArray[i] = (int) (Math.random() * usersNum) + 1;
        }
        System.out.println("Исходный массив: " + Arrays.toString(usersArray));
        int clearNumsCount = 0;
        for (int i = 0; i < usersArray.length; i++)
        {
            if(usersArray[i] % 2 == 0)
            {
                clearNumsCount++;
            }
        }
        int [] clearNumsArr = new int[clearNumsCount];
        int counter = 0;
        for (int i = 0; i < usersArray.length; i++)
        {
            if(usersArray[i] % 2 == 0)
            {
                clearNumsArr[counter] = usersArray[i];
                counter++;
            }
        }
        System.out.println(clearNumsArr.length > 0 ? "Массив с четными элементами: " + Arrays.toString(clearNumsArr) : "Четные элементы в исходном массиве отсутствуют\n");

/*      5.Создать двумерный массив из 5 строк по 8 столбцов в каждой из случайных целых чисел из отрезка [-99;99].
        Вывести массив в консоль. После на отдельной строке вывести в консоль значение максимального элемента этого массива.*/
        System.out.println("\nTask5:\n");
        int [][] randArray = new int [5][8];
        for (int i = 0; i < randArray.length; i++)
        {
            for (int j = 0; j < randArray[i].length; j++)
            {
                randArray[i][j] = (int)(Math.random() * 198) - 99;
            }
        }
        System.out.println(Arrays.deepToString(randArray));
        int maxVar = 0;
        for (int i = 0; i < randArray.length; i++)
        {
            for (int j = 0; j < randArray[i].length; j++)
            {
                if(randArray[i][j] > maxVar)
                {
                    maxVar = randArray[i][j];
                }
            }
        }
        System.out.printf("Максимальный элемент многомерного массива: %d\n",maxVar);
    }
}
