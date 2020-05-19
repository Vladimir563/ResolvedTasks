package observertask;
import navalbattle.*;

public class Main
{
    enum AlarmColors
    {
        GREEN_ALARM("Green alarm worked!"), YELLOW_ALARM("Yellow alarm worked!"),RED_ALARM("Red alarm worked!");

        private String alarmColor;

        AlarmColors(String alarmColor) {
            this.alarmColor = alarmColor;
        }

        public String getAlarmColor()
        {
            return alarmColor;
        }
    }

    public static void main(String[] args)
    {
        Alarm greenAlarm = temp ->
        {
            if(temp == 100)
            {
                System.out.println("\n" + textColours.ANSI_GREEN.getCode() + AlarmColors.GREEN_ALARM.getAlarmColor() + textColours.ANSI_RESET.getCode() +"\n");
            }
        };

        Alarm yellowAlarm = temp ->
        {
            if(temp == 300)
            {
                System.out.println("\n" + textColours.ANSI_GREEN.getCode() + AlarmColors.GREEN_ALARM.getAlarmColor() + textColours.ANSI_RESET.getCode());
                System.out.println(textColours.ANSI_YELLOW.getCode() + AlarmColors.YELLOW_ALARM.getAlarmColor() + textColours.ANSI_RESET.getCode() +"\n");
            }
        };

        Alarm redAlarm = temp ->
        {
            if(temp == 600)
            {
                System.out.println("\n" + textColours.ANSI_GREEN.getCode() + AlarmColors.GREEN_ALARM.getAlarmColor() + textColours.ANSI_RESET.getCode());
                System.out.println(textColours.ANSI_YELLOW.getCode() + AlarmColors.YELLOW_ALARM.getAlarmColor() + textColours.ANSI_RESET.getCode());
                System.out.println(textColours.ANSI_RED.getCode() + AlarmColors.RED_ALARM.getAlarmColor() + textColours.ANSI_RESET.getCode() +"\n");
            }
        };

        Sensor sensor = new Sensor();
        sensor.addAlarm(greenAlarm);
        sensor.addAlarm(redAlarm);
        sensor.addAlarm(yellowAlarm);
        sensor.tempIncStart();
    }
}
