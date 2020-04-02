package gymtask;

public class Logger
{
    private static Gym gym;

    public Logger(Gym gym)
    {
        this.gym = gym;
    }

    public void printVisitorsInfo()
    {
        if(gym.getCurrentGymZoneVisitors()[0] != null)
        {
            printAllVisitors(sortVisitorsBySurname(gym.getCurrentGymZoneVisitors()), "\"тренажерный зал\"");
        }
        else System.out.println("Тренажерный зал пуст");

        if(gym.getCurrentSwimmingPoolZoneVisitors()[0] != null)
        {
            printAllVisitors(sortVisitorsBySurname(gym.getCurrentSwimmingPoolZoneVisitors()), "\"плавательный бассейн\"");
        }
        else System.out.println("Плавательный бассейн пуст");

        if(gym.getCurrentGroupClassesVisitors()[0] != null)
        {
            printAllVisitors(sortVisitorsBySurname(gym.getCurrentGroupClassesVisitors()), "\"групповые занятия\"");
        }
        else System.out.println("Зал для групповых занятий пуст");
    }


//todo: вспомогательные методы

    private static GymVisitor [] sortVisitorsBySurname(GymVisitor [] visitors)
    {
        GymVisitor [] sortVisitors = new GymVisitor[visitors.length];
        for (int i = 0; i < sortVisitors.length; i++)
        {
            sortVisitors[i] = returnVisitorWithSmallerSurname(visitors);
            visitors = returnArrWithoutSmallerObj(visitors, returnVisitorWithSmallerSurname(visitors));
        }
        return sortVisitors;
    }

    private static GymVisitor returnVisitorWithSmallerSurname(GymVisitor [] visitors)
    {
        GymVisitor v = null;
        String smallerSurname = "Zzzzzz";
        for (int i = 0; i < visitors.length; i++)
        {
            if(visitors[i].getSurname().compareTo(smallerSurname) < 0)
            {
                smallerSurname = visitors[i].getSurname();
                v = visitors[i];
            }
        }
        return v;
    }

    private static GymVisitor [] returnArrWithoutSmallerObj(GymVisitor [] visitors, GymVisitor gmWithSmallerSurname)
    {
        GymVisitor [] newVisitors = new GymVisitor [visitors.length - 1];
        int counter = 0;
        for (int i = 0; i < visitors.length; i++)
        {
            if(!visitors[i].getSurname().equals(gmWithSmallerSurname.getSurname()))
            {
                newVisitors [counter] = visitors[i];
                counter++;
            }
        }
        return newVisitors;
    }

    private static void printAllVisitors (GymVisitor [] visitors, String zoneName)
    {
        System.out.printf("Посетители зоны %s: ",zoneName);
        String str = "";
        for(GymVisitor v : visitors)
        {
            str += (" " + v.getSurname() + " " + v.getName() + ", ");
        }
        System.out.println(str.substring(0,str.length()-2));
    }
}

/*      Реализовать возможность вывода информации о посетителях:
        сначала посетителях тренажерного зала, потом бассейна, потом групповых занятий.
        Выводить имя и фамилию посетителей в отсортированном порядке (сначала фамилия, потом имя).

        Реализовать возможность выводить информацию в консоль каждый раз, когда клиент посещает фитнес клуб.
        Информация для вывода:
        Фамилия
        Имя
        Вид занятия
        Дата и время посещения

        Методы вывода информации в консоль можно описать в отдельном классе Logger (методы можно сделать static)*/
