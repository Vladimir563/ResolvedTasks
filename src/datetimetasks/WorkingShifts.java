package datetimetasks;

import java.time.LocalTime;

public class WorkingShifts
{
    private static LocalTime beginMorningShift = LocalTime.of(7,0);
    private static LocalTime endMorningShift = LocalTime.of(15,0);
    private static LocalTime beginDayShift = LocalTime.of(15,0);
    private static LocalTime endDayShift = LocalTime.of(23,0);
    private static LocalTime beginNightShift = LocalTime.of(23,0);
    private static LocalTime endNightShift = LocalTime.of(7,0);

    public static void returnNameOfCurrentWorkingShift()
    {
        if(LocalTime.now().isAfter(beginMorningShift) && LocalTime.now().isBefore(endMorningShift))
        {
            System.out.println("Сейчас утренняя смена\n");
        }

        if(LocalTime.now().isAfter(beginDayShift) && LocalTime.now().isBefore(endDayShift))
        {
            System.out.println("Сейчас дневная смена\n");
        }

        if(LocalTime.now().isAfter(beginNightShift) && LocalTime.now().isBefore(endNightShift))
        {
            System.out.println("Сейчас ночная смена\n");
        }
    }
}

/*
        с 7:00 до 15:00
        с 15:00 до 23:00
        с 23:00 до 7:00
*/


