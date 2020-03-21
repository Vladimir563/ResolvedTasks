package classandobjects;

import classandobjects.exceptionfigure.NegativeVarException;
import classandobjects.geometry.Circle;
import classandobjects.geometry.Rectangle;
import classandobjects.geometry.Triangle;
import classandobjects.cattask.Cat;
import classandobjects.cattask.CatOwner;
import com.sun.org.apache.bcel.internal.classfile.SourceFile;

import java.util.concurrent.TimeUnit;


public class ClassAndObjectsTasks
{
    public static void main(String[] args) throws InterruptedException {
/*      1) Создать фигуры: Circle, Rectangle, Triangle, у которых должны быть координаты.
           Все фигуры должны иметь методы, которые возвращают площадь и периметр (для окружности - длина окружности).*/
        try
        {
            Rectangle rec1 = new Rectangle(12,40);
            System.out.println(rec1.toString());
            System.out.println();

            Circle circ1 = new Circle(24);
            System.out.println(circ1.toString());
            System.out.println();

            Triangle triangle = new Triangle(10,3,4);
            System.out.println(triangle.toString());
            System.out.println();
        }
        catch (NegativeVarException e)
        {
            System.out.printf("\nВозникло исключение: " + e.toString());
        }

/*      2) Создать класс Cat со следующими свойствами:
            1. имя
            2. имя, вес, возраст
            3. имя, возраст
            4. вес, цвет
            5. вес, цвет, адрес владельца
            6. количество здоровья (тип int)
            7. сила удара (тип int)

            Создать 5 разных конструкторов.
            Создать соответствующие сеттеры и геттеры.
            Создать метод, который выводит полную информацию о коте.

            Реализовать метод void fight(Cat anotherCat) -  механизм битвы котов: количество здоровья
            кота уменьшается на силу удара противника (anotherCat)
            Если у кота не осталось очков здоровья, выводить информацию, что он пока не может драться

            Создать не менее 5 объектов (использовать разные конструкторы).*/

        CatOwner owner = new CatOwner("Victor","Petrov","Stroiteley 43");
        CatOwner owner1 = new CatOwner("Ivan","Smirnov","Sadovaya 1");

        Cat cat1 = new Cat(10,"grey",owner);
        System.out.println(cat1.toString());

        Cat cat2 = new Cat("Archi");
        Cat cat3 = new Cat(13,"white");
        Cat cat4 = new Cat("Baks",10,2);

        Cat fightCat1  = new Cat("Baron" ,12 ,5 ,"black",100,20,owner);
        Cat fightCat2  = new Cat("Pers" ,12 ,5 ,"red",100,30,owner1);
        System.out.println();

        do
        {
            fightCat1.Fight(fightCat2);
            TimeUnit.SECONDS.sleep(1);
        }
        while(fightCat1.isCatMayFight());
    }
}




