package gymtask;

import gymtask.subscriptionstype.DaySubscription;
import gymtask.subscriptionstype.UnlimitedSubscription;

import java.time.LocalDate;
import java.util.Arrays;

public class Main
{
    public static void main(String[] args)
    {
        Gym gym1 = new Gym();
        GymVisitor visitor1 = new GymVisitor("Oleg","Petrov", LocalDate.of(1991,3,23));
        GymVisitor visitor2 = new GymVisitor("Ivan", "Petrov", LocalDate.of(1990,2,14));
        Subscription daily = new DaySubscription(3,visitor1);
        Subscription unlim = new UnlimitedSubscription(12,visitor2);

        GymVisitor visitor3 = new GymVisitor("Petr", "Smirnov", LocalDate.of(1994,9,24));
        Subscription unlim2 = new UnlimitedSubscription(5,visitor3);


        gym1.registerSubscription(daily);
        gym1.registerSubscription(unlim);

        System.out.println(Arrays.toString(gym1.getDataBaseRegisteredVisitors()));
        System.out.println(gym1.getDataBaseRegisteredVisitors().length);

        gym1.visitorReception("group classes",daily);
        System.out.println("____________");

        gym1.visitorReception("group classes",unlim);
        System.out.println("____________");

        gym1.visitorReception("group classes",unlim2);
        System.out.println("____________");

        System.out.println(gym1.getDataBaseRegisteredVisitors().length);

        System.out.println(gym1.toString());
    }
}


/*
Задача «Фитнес»
//todo
        В фитнес клубе есть три типа абонементов:
        Разовый. По разовому абонементу клиенты могут посещать бассейн и тренажерный зал с 8 до 22 часов.
        Дневной. По данному абонементу клиенты могут посещать тренажерный зал и групповые занятия (но не бассейн) с 8 до 16 часов.
        Полный. По данному абонементу клиенты могут посещать тренажерный зал, бассейн и групповые занятия с 8 до 22 часов.
//todo
        Каждый абонемент хранит дату регистрации (текущая дата) и дату окончания регистрации.
        Каждый абонемент хранит информацию о владельце. Данные о владельце: имя, фамилия, год рождения.

        Фитнес содержит информацию об абонементах:
        которые зарегистрированы в данный момент в тренажерном зале;
        абонементах, которые зарегистрированы в бассейне;
        абонементах, которые зарегистрированы на групповых занятиях.

        В каждой зоне (бассейн, тренажерный зал, групповые занятия) одновременно может быть зарегистрировано 20 абонентов.

        Когда  фитнес клуб закрывается, клиенты покидают его.

        Когда клиент приходит в фитнес клуб, он сообщает желаемую зону и показывает абонемент.
        Необходимо проверить может ли данный посетитель пройти в желаемую зону и пропустить его,
        если возможность существует; если посетитель не может пройти, необходимо сообщить ему причину.

        Посетитель не может пройти, если время абонемента не соответсвует текущему времени,
        если он пытается пройти в зону, которая не разрешена по его абонементу,
        если в зоне нет свободных мест.

        Реализовать возможность вывода информации о посетителях:
        сначала посетителях тренажерного зала, потом бассейна, потом групповых занятий.
        Выводить имя и фамилию посетителей в отсортированном порядке (сначала фамилия, потом имя).

        Реализовать возможность выводить информацию в консоль каждый раз, когда клиент посещает фитнес клуб.
        Информация для вывода:
        Фамилия
        Имя
        Вид занятия
        Дата и время посещения

        Методы вывода информации в консоль можно описать в отдельном классе Logger (методы можно сделать static)*/
