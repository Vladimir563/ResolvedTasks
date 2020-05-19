package observertask;


import java.util.ArrayList;

public class Sensor
{
    private ArrayList <Alarm> alarms = new ArrayList<>();

    public void addAlarm(Alarm alarm)
    {
        alarms.add(alarm);
    }

    public void removeAlarm(Alarm alarm)
    {
        alarms.remove(alarm);
    }

    public void tempIncStart()
    {
        int counter = 0;
        for (int i = 0; i <= 600 ; i++)
        {
            final int finalI = i;
            System.out.print("\rCurrent temperature: " + i);
            alarms.forEach(alarm -> alarm.tempChanged(finalI));
            if(i == 356 && counter < 1)
            {
                System.out.println("\nПадение температуры...");
                i = 45;
                counter++;
            }
            try
            {
                Thread.sleep(15);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }
}
