package arrayTasks;

import java.sql.SQLOutput;
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
            nums[i] =  10 + (int) (Math.random() * 89 + 1);
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
            System.out.printf("Элемент 0 встречается чаще всего (%d раз)\n", matchZero);
        }
        else{}

/*      Многомерные массивы
        Задача 1
        Создать двумерный массив из 8 строк по 5 столбцов в каждой из случайных целых чисел из отрезка [10;99].
        Вывести массив в консоль.
*/      System.out.println("\nTask1(multidimentional arrays)\n");
        int [][] multiDimArr = new int[8][5];
        for (int i = 0; i < multiDimArr.length; i++)
        {
            for (int j = 0; j < multiDimArr[i].length ; j++)
            {
                multiDimArr[i][j] = 10 + (int)(Math.random() * 89 + 1);
            }
        }
        System.out.println(Arrays.deepToString(multiDimArr));

/*      Задача 2
        Cоздать двумерный массив из 7 строк по 4 столбца в каждой из случайных целых чисел из отрезка [-5;5].
        Вывести массив в консоль.
        Определить и вывести на экран индекс строки с наибольшим по модулю произведением элементов.
        Если таких строк несколько, то вывести индекс первой встретившейся из них.
*/      System.out.println("\nTask2(multidimentional arrays)\n");
        int [][] multiDArr = new int [7][4];
        for (int i = 0; i <multiDArr.length ; i++)
        {
            for (int j = 0; j < multiDArr[i].length ; j++)
            {
                multiDArr[i][j] = (int) (Math.random() * 10) - 5;
            }
        }
        System.out.println(Arrays.deepToString(multiDArr));
        int [] arraysSum = new int [multiDArr.length];

        for (int i = 0; i < arraysSum.length ; i++)
        {
            arraysSum[i] = 1;
        }

        for (int i = 0; i < multiDArr.length; i++)
        {
            for (int j = 0; j < multiDArr[i].length ; j++)
            {
                arraysSum[i] *= Math.abs(multiDArr[i][j]);
            }
        }
        int [] sortArraySum = arraysSum.clone();
        Arrays.sort(sortArraySum);

//      System.out.println(Arrays.toString(arraysSum)); //FIXME: раскомментировать для просмотра массива с произведением членов строк
        int index = 0;
        for (int i = 0; i < arraysSum.length ; i++)
        {
            if(arraysSum[i] == sortArraySum[sortArraySum.length-1])
            {
                index = i;
                break;
            }
        }
        System.out.printf("%d-я строка с наибольшим по модулю произведением элементов (%d)\n", index+1, sortArraySum[sortArraySum.length-1]);

/*      Задача 3
        Создать двумерный массив из 6 строк по 7 столбцов в каждой из случайных целых чисел из отрезка [0;9]. Вывести массив в консоль.
        Преобразовать массив таким образом, чтобы на первом месте в каждой строке стоял её наибольший элемент.
        При этом изменять состав массива нельзя, а можно только переставлять элементы в рамках одной строки.
        Порядок остальных элементов строки не имеет значения (т.е. можно совершить только одну перестановку, а можно отсортировать по убыванию каждую строку).
        Вывести преобразованный массив в консоль.*/
        System.out.println("\nTask3(multidimentional arrays)\n");
        int [][] arrayTask3 = new int [6][7];
        for (int i = 0; i < arrayTask3.length ; i++)
        {
            for (int j = 0; j < arrayTask3[i].length; j++)
            {
                arrayTask3[i][j] = (int) (Math.random() * 9 + 1);
            }
        }
        System.out.println(Arrays.deepToString(arrayTask3));
        for (int i = 0; i < arrayTask3.length ; i++)
        {
            int min = 0;
            for (int j = 0; j < arrayTask3[i].length ; j++)
            {
                if(arrayTask3[i][j] > min)
                {
                    min = arrayTask3[i][j];
                    int temporaryVar = arrayTask3[i][0];
                    arrayTask3[i][0] = arrayTask3[i][j];
                    arrayTask3[i][j] = temporaryVar;
                }
            }
        }
        System.out.println(Arrays.deepToString(arrayTask3));
    }
}




