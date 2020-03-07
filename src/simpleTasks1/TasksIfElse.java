package simpleTasks1;

import java.util.Scanner;

public class TasksIfElse
{
    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
//      Задача 1
//      В переменной n хранится натуральное трёхзначное число. Вывсети в консоль сумму цифр числа n.
        System.out.println("Task1:");
        int n1 = 924;
        int sum = n1/100 + (n1%100/10) + n1%10;
        System.out.println(sum+"\n");

//      Задача 2
//      Проверить является ли целое число записанное в переменную n, чётным либо нечётным. Результат вывсети в консоль.
        System.out.println("Task2:");
        int n2 = 9;
        System.out.println((n2 % 2 == 0)? "Четное":"Нечетное");
        System.out.println();

//      Задача 3
//      Даны два целых числа n и m. Наименьшее из них сохранить в переменную res и вывести ее на экран.
        System.out.println("Task3:");
        int a = 5, b = 7;
        int res = (a > b) ? b : a;
        System.out.println(res + "\n");

//      Задача 4
//      Написать код, который будет проверять попало ли случайно сгенерированное целое число
//      из отрезка [5;122] в интервал (25;100) и выводить результат в консоль.
//      Примеры работы программы:
//      Число 113 не содержится в интервале (25,100)
//      Число 72 содержится в интервале (25,100)
        System.out.println("Task4:");
        int numRand = (int) (Math.random() * 122) + 1;
        if(numRand >= 25 && numRand <= 100)
            System.out.printf("Число %d содержится в интервале (25, 100)\n", numRand);
        else System.out.printf("Число %d не содержится в интервале (25, 100)\n", numRand);

//      Задача 5
//      Написать код, выводящий на экран случайно сгенерированное трёхзначное число и его наибольшую цифру.
//      Примеры работы программы:
//      В числе 208 наибольшая цифра 8
//      В числе 774 наибольшая цифра 7
//      В числе 613 наибольшая цифра 6
        System.out.println("\nTask5:");
        int numRand1 = 100 + (int) (Math.random() * 999);
        System.out.println(numRand1);
        int a1 = numRand1 / 100;
        int a2 = numRand1 % 100 / 10;
        int a3 = numRand1 % 10;
        System.out.printf("В числе %d Наибольшее число: %d \n",numRand1,(a1 > a2 && a1 > a3) ? a1 : (a2 > a1 && a2 > a3) ? a2 : a3);

//      Задача 6
//      Создайте программу, выводящую на экран все четырёхзначные числа последовательности 1000 1003 1006 1009 1012 1015 ….
        System.out.println("\nTask6:");
        int counter = 1000;
        while (counter < 10000)
        {
            System.out.print(counter < 10000 ? counter + " " : "");
            counter += 3;
        }
        System.out.println();

//      Задача 7
//      Создайте программу, выводящую на экран первые 55 элементов последовательности 1 3 5 7 9 11 13 15 17 ….
        System.out.println("\nTask7:");
        int c = 1;
        int taskCounter = 0;
        while(taskCounter < 55)
        {
            System.out.printf("%d ", c);
            c+=2;
            taskCounter++;
        }
        System.out.printf("\ncounter: %d\n", taskCounter);

//      Задача 8
//      Создайте программу, выводящую на экран все неотрицательные элементы последовательности 90 85 80 75 70 65 60 ….
        System.out.println("\nTask8:");
        for(int i = 90; i >= 0; i-=5)
        {
            System.out.printf("%d ",i);
        }
        System.out.println();

//      Задача 9
//      Создайте программу, выводящую на экран первые 20 элементов последовательности 2 4 8 16 32 64 128 ….
        System.out.println("\nTask9:");
        int count = 0;
        int var = 1;
        while (count < 20)
        {
            System.out.printf("%d ", var*=2);
            count++;
        }
        System.out.println();

//      Задача 10
//      Создайте программу, вычисляющую факториал натурального числа n, которое пользователь введёт с клавиатуры.
        System.out.println("\nTask10:");
        System.out.print("Введите число для вычисления факториала: ");
        int userNum = in.nextInt();
        int num = userNum;
        int factorial = 1;
        while(userNum > 0)
        {
            factorial*=userNum;
            userNum--;
        }
        if(num == 0)
        {
            factorial = 1;
        }
        System.out.printf("Факториал %d = %d\n",num,factorial);

//      Задача 11
//      Выведите на экран первые 11 членов последовательности Фибоначчи.
        System.out.println("\nTask11:");
        int [] fibbonaciArr = new int [11];
        fibbonaciArr[0] = 0;
        fibbonaciArr[1] = 1;
        for (int i = 2; i < 11; i++)
        {
            fibbonaciArr[i] = fibbonaciArr[i-2] + fibbonaciArr[i-1];
        }
        for(int e : fibbonaciArr)
        {
            System.out.printf("%d ",e);
        }
        System.out.println();

//      Задача 12
//      Электронные часы показывают время в формате от 00:00 до 23:59. Подсчитать сколько раз за сутки случается так,
//      что слева от двоеточия показывается симметричная комбинация для той, что справа от двоеточия
//      (например, 02:20, 11:11 или 15:51).
        System.out.println("\nTask11:");
        int coincedenceCounter = 0; //FIXME: счетчик совпадений
        for (int hour = 0; hour <= 23; hour++)
        {
            for (int min = 0; min <= 59 ; min++)
            {
                String o = Integer.toString(min / 10);
                String u = Integer.toString(min % 10);
                int r = Integer.valueOf(String.valueOf(u + o));
                if(hour == r)
                {
//                    System.out.printf("hour = %d, min = %d \n",hour,min); //FIXME: раскомменитровать для просмотра совпадений
                    coincedenceCounter++;
                }
            }
        }
        System.out.printf("\ncoincedenceCounter = %d \n",coincedenceCounter);
    }

}




