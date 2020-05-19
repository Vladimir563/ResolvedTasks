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
        for (int i = 0; i <= 600 ; i++)
        {
            final int finalI = i;
            System.out.print("\rCurrent temperature: " + i);
            alarms.forEach(alarm -> alarm.tempChanged(finalI));
            try
            {
                Thread.sleep(30);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }
}
