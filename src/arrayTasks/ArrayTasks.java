package arrayTasks;

import java.util.Arrays;
import java.util.Collections;

public class ArrayTasks
{
    public static void main(String[] args)
    {
/*      Задача 1
        Создайте массив из всех чётных чисел от 2 до 20 и выведите элементы массива в консоль в обратном порядке (20 18 16 ... 4 2).*/
        System.out.println("Task1\n");
        int arrLength = 0;
        for (int i = 2; i <= 20 ; i++)
        {
            if(i % 2 == 0)
            {
                arrLength++;
            }
        }

        int [] clearNums = new int[arrLength];

        for (int i = 0, j = 2; i < clearNums.length || j <= 20 ; j++)
        {
            if(j % 2 == 0)
            {
                clearNums[i] = j;
                i++;
            }
        }

        int [] reverseArr = new int[arrLength];
        for (int i = 0 , j = 1; i < reverseArr.length ; i++, j++)
        {
            reverseArr[i] = clearNums[arrLength - j];
        }
        System.out.println(Arrays.toString(reverseArr));

/*      Задача 2
        Создайте массив из 15 случайных целых чисел из отрезка [0;9].
        Выведите массив в консоль.
        Подсчитайте сколько в массиве чётных элементов и выведете это количество в консоль.*/
        System.out.println("\nTask2\n");
        int [] randomNumsArr = new int [15];
        for (int i = 0; i < randomNumsArr.length ; i++)
        {
            randomNumsArr[i] = (int) (Math.random() * 9 + 1);
        }
        System.out.println(Arrays.toString(randomNumsArr));
        int counter = 0;
        for (int i = 0; i < randomNumsArr.length ; i++)
        {
            if(randomNumsArr[i] % 2 == 0)
            {
               counter++;
            }
        }
        System.out.printf("В массиве %d четных элементов \n",counter);

        /*Задача 3
        Создайте массив из 4 случайных целых чисел из отрезка [10;99], выведите его в консоль.
        Определить и вывести в консоль сообщение о том, является ли массив строго возрастающей последовательностью.
*/      System.out.println("\nTask3\n");
        int [] nums = new int [4];
        for (int i = 0; i < nums.length ; i++)
        {
            nums[i] =  10 + (int) (Math.random() * 99 + 1);
        }
        System.out.println(Arrays.toString(nums));
        int increasingNum = 0;
        for (int i = 0; i < nums.length - 1 ; i++)
        {
            if(nums[i] < nums[i + 1])
            {
                increasingNum++;
            }
            else break;
        }
        System.out.printf("массив%s является строго возрастающей последовательностью\n",(increasingNum == nums.length - 1) ? "":" не");

/*      Задача 4
        Создайте массив из 11 случайных целых чисел из отрезка [-1;1], выведите массив в консоль.
        Определите какой элемент встречается в массиве чаще всего и выведите об этом в консоль.
        Если два каких-то элемента встречаются одинаковое количество раз, то не выводите ничего.
*/      System.out.println("\nTask4\n");
        int [] randArr = new int[11];
        for (int i = 0; i <randArr.length ; i++)
        {
            randArr[i] = (int)(Math.random() * 3) - 1;
        }
        System.out.println(Arrays.toString(randArr));
        int matchMinusOne = 0;
        int matchZero = 0;
        int matchOne = 0;
        for(int num : randArr)
        {
            if(num == 0)
            {
                matchZero++;
            }
            else if(num == 1)
            {
                matchOne++;
            }
            else matchMinusOne++;
        }

        if(matchMinusOne > matchOne && matchMinusOne > matchZero)
        {
            System.out.printf("Элемент -1 встречается чаще всего (%d раз)", matchMinusOne);
        }
        else if(matchOne > matchMinusOne && matchOne > matchZero)
        {
            System.out.printf("Элемент 1 встречается чаще всего (%d раз)", matchOne);
        }
        else if (matchZero > matchOne && matchZero > matchMinusOne)
        {
            System.out.printf("Элемент 0 встречается чаще всего (%d раз)", matchZero);
        }
        else{}
    }
}

/*Массивы

        Многомерные массивы
        Задача 1
        Создать двумерный массив из 8 строк по 5 столбцов в каждой из случайных целых чисел из отрезка [10;99]. Вывести массив в консоль.

        Задача 2
        Cоздать двумерный массив из 7 строк по 4 столбца в каждой из случайных целых чисел из отрезка [-5;5]. Вывести массив в консоль.
        Определить и вывести на экран индекс строки с наибольшим по модулю произведением элементов.
        Если таких строк несколько, то вывести индекс первой встретившейся из них.

        Задача 3
        Создать двумерный массив из 6 строк по 7 столбцов в каждой из случайных целых чисел из отрезка [0;9]. Вывести массив в консоль.
        Преобразовать массив таким образом, чтобы на первом месте в каждой строке стоял её наибольший элемент.
        При этом изменять состав массива нельзя, а можно только переставлять элементы в рамках одной строки.
        Порядок остальных элементов строки не имеет значения (т.е. можно соврешить только одну перестановку, а можно отсортировать по убыванию каждую строку).
        Вывести преобразованный массив в консоль.*/
