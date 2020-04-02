package gymtask;

import gymtask.subscriptionstype.*;
import sun.rmi.runtime.Log;

import java.time.LocalDate;
import java.util.Arrays;

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

enum gymZones
{
    GROUP_CLASSES_ZONES("group classes"), GYM_ZONES("gym"), SWIMMING_POOL_ZONES("swimming pool");
    private String zone;
    gymZones(String z)
    {
        zone = z;
    }
    public String getZone()
    {
        return zone;
    }
}


public class Main
{
    public static void main(String[] args) throws InterruptedException
    {

        Gym gym1 = new Gym();
        Logger logger = new Logger(gym1);
//todo: создали посетителей
        GymVisitor visitor1 = new GymVisitor("Oleg","Petrov", LocalDate.of(1991,3,23));
        GymVisitor visitor2 = new GymVisitor("Ivan", "Vasilyev", LocalDate.of(1990,2,14));
        GymVisitor visitor3 = new GymVisitor("Petrov", "Antonov", LocalDate.of(2001,9,8));
        GymVisitor visitor4 = new GymVisitor("Genry", "Abdula", LocalDate.of(1980,1,4));
//todo: создали абонементы
        Subscription v1Daily = new DaySubscription(3,visitor1);
        Subscription v2Unlim = new UnlimitedSubscription(10,visitor2);
        Subscription v3Single = new SingleSubscription(visitor3);
        Subscription v4Daily = new DaySubscription(1,visitor4);
//todo: регистрируем абонементы (не все)
        gym1.registerSubscription(v1Daily);
        gym1.registerSubscription(v2Unlim);
        gym1.registerSubscription(v3Single);
//todo: попытка доступа к зонам (имитация ресепшн)
        gym1.visitorReception(gymZones.GROUP_CLASSES_ZONES.getZone(),v1Daily);
        gym1.visitorReception(gymZones.GYM_ZONES.getZone(),v1Daily);
        gym1.visitorReception(gymZones.SWIMMING_POOL_ZONES.getZone(),v1Daily);
//todo: попытка пройти несколько раз по разовому абонементу
        gym1.visitorReception(gymZones.GYM_ZONES.getZone(),v3Single);
        gym1.visitorReception(gymZones.GYM_ZONES.getZone(),v3Single);
        gym1.visitorReception(gymZones.GYM_ZONES.getZone(),v3Single);
        gym1.visitorReception(gymZones.SWIMMING_POOL_ZONES.getZone(),v2Unlim);
        gym1.visitorReception(gymZones.GYM_ZONES.getZone(),v3Single);
        gym1.visitorReception(gymZones.SWIMMING_POOL_ZONES.getZone(),v4Daily);
        logger.printVisitorsInfo();
//todo: закрытие зала
        gym1.closeGym();
        logger.printVisitorsInfo();
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
//todo
        Фитнес содержит информацию об абонементах:
        которые зарегистрированы в данный момент в тренажерном зале;
        абонементах, которые зарегистрированы в бассейне;
        абонементах, которые зарегистрированы на групповых занятиях.
//todo
        В каждой зоне (бассейн, тренажерный зал, групповые занятия) одновременно может быть зарегистрировано 20 абонентов.
//todo
        Когда  фитнес клуб закрывается, клиенты покидают его.
//todo
        Когда клиент приходит в фитнес клуб, он сообщает желаемую зону и показывает абонемент.
        Необходимо проверить может ли данный посетитель пройти в желаемую зону и пропустить его,
        если возможность существует; если посетитель не может пройти, необходимо сообщить ему причину.
//todo
        Посетитель не может пройти, если время абонемента не соответсвует текущему времени,
        если он пытается пройти в зону, которая не разрешена по его абонементу,
        если в зоне нет свободных мест.
//todo
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
