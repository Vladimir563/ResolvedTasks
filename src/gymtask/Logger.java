package gymtask;

import java.time.LocalDate;
import java.time.LocalTime;

public class Logger
{
    private static Gym gym;

    public Logger(Gym gym)
    {
        this.gym = gym;
    }

    public static void printCurrentVisitorInfo(GymVisitor visitor, String zone)
    {
        System.out.printf(textColours.ANSI_GREEN.getCode() + "Посетитель: %s %s; Вид занятия: %s; Дата и время посещения: %d:%d (%d.%d.%d)\n" + textColours.ANSI_RESET.getCode(),visitor.getSurname(),visitor.getName(),zone,
                LocalTime.now().getHour(),LocalTime.now().getMinute(), LocalDate.now().getDayOfMonth(),LocalDate.now().getMonth().getValue(),LocalDate.now().getYear());
    }
    public void printVisitorsInfo()
    {
        if(gym.getCurrentGymZoneVisitors() != null && gym.getCurrentGymZoneVisitors()[0] != null)
        {
            printAllVisitors(sortVisitorsBySurname(gym.getCurrentGymZoneVisitors()), "\"тренажерный зал\"");
        }
        else System.out.println(textColours.ANSI_PURPLE.getCode() + "Тренажерный зал пуст" + textColours.ANSI_RESET.getCode());

        if(gym.getCurrentSwimmingPoolZoneVisitors() != null && gym.getCurrentSwimmingPoolZoneVisitors()[0] != null)
        {
            printAllVisitors(sortVisitorsBySurname(gym.getCurrentSwimmingPoolZoneVisitors()), "\"плавательный бассейн\"");
        }
        else System.out.println(textColours.ANSI_PURPLE.getCode() + "Плавательный бассейн пуст" + textColours.ANSI_RESET.getCode());

        if(gym.getCurrentGroupClassesVisitors() != null && gym.getCurrentGroupClassesVisitors()[0] != null)
        {
            printAllVisitors(sortVisitorsBySurname(gym.getCurrentGroupClassesVisitors()), "\"групповые занятия\"");
        }
        else System.out.println(textColours.ANSI_PURPLE.getCode() + "Зал для групповых занятий пуст" + textColours.ANSI_RESET.getCode());
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
        System.out.printf(textColours.ANSI_CYAN.getCode() + "Посетители зоны %s: " + textColours.ANSI_RESET.getCode(),zoneName);
        String str = "";
        for(GymVisitor v : visitors)
        {
            str += (" " + v.getSurname() + " " + v.getName() + ", ");
        }
        System.out.println(str.substring(0,str.length()-2));
    }
}

