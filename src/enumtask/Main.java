package enumtask;

import java.util.Arrays;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        User [] managers = {new User("Tom Fletcher", 1500, Position.MANAGER), new User ("Fill Smith",1300, Position.MANAGER)};
        User [] cleaners = {new User("Den Santos", 500, Position.CLEANER), new User("Mark Adiana",550, Position.CLEANER)};
        User [] drivers = {new User("Mark Alister",1000,Position.DRIVER), new User("Greg Fitch", 1100, Position.DRIVER)};

        Scanner in = new Scanner(System.in);
        System.out.print("Введите полное имя: ");
        String employeeName = in.nextLine();
        System.out.print("Введите первую букву должности: ");
        String employeePos = in.nextLine();
        User [] employees;

        switch (employeePos.toUpperCase())
        {
            case "M":
                employees = new User[managers.length + 1];
                System.arraycopy(managers,0,employees,0,managers.length);
                employees[employees.length - 1] = new User(employeeName,1300,Position.MANAGER);
                managers = employees;
                break;
            case "C":
                employees = new User[cleaners.length + 1];
                System.arraycopy(cleaners,0,employees,0,cleaners.length);
                employees[employees.length - 1] = new User(employeeName,500, Position.CLEANER);
                cleaners = employees;
            case "D":
                employees = new User[drivers.length + 1];
                System.arraycopy(drivers,0,employees,0,drivers.length);
                employees[employees.length - 1] = new User(employeeName, 1000, Position.DRIVER);
                drivers = employees;
            default:
                System.out.println("Не верно введена позиция будущего работника");
        }

        System.out.println(Arrays.toString(managers));
        System.out.println(Arrays.toString(cleaners));
        System.out.println(Arrays.toString(drivers));
    }
}

enum Position
{
    MANAGER,DRIVER,CLEANER
}

/*  Дано:
    1.  enum должностей Position,
    2.  класс User со следующими полями:
        String fulName;
        int salary;
        Position position;
    3. несколько массивов, в каждом массиве будут храниться объекты класса User
    с одинаковыми должностями

    Пользователь вводит свои имя, фамилию и первую букву должности.
    Ваша задача создать объект типа User и поместить его в соответствующий массив.*/
