package dateandtime;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class dateandtimetask
{
    public static void main(String[] args)
    {
        String departureStr = "16 июня 2020 в 19:00";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy в HH:mm");
        LocalDateTime departure = LocalDateTime.parse(departureStr,formatter);

        ZonedDateTime syd = departure.atZone(ZoneId.of("Australia/Sydney"));
        syd = syd.plusHours(15).plusMinutes(35);

        ZonedDateTime sydHus = syd.withZoneSameInstant(ZoneId.of("America/Chicago"));
        sydHus = sydHus.plusHours(1);
        System.out.println("Дата и время вылета из эаропорта Хьюстона : " + sydHus.format(formatter) + " [" + sydHus.getZone() + "]");
        sydHus = sydHus.plusHours(2).plusMinutes(49);

        ZonedDateTime husWash = sydHus.withZoneSameInstant(ZoneId.of("America/New_York"));
        husWash = husWash.plusHours(1).plusMinutes(10);
        System.out.println("Дата и время вылета из эаропорта Вашингтона: " + husWash.format(formatter) + " [" + husWash.getZone() + "]");
        husWash = husWash.plusHours(1).plusMinutes(40);

        ZonedDateTime ott = husWash.withZoneSameInstant(ZoneId.of("America/Toronto"));
        System.out.println("Дата и время прибытия в эаропорт Оттавы: " + ott.format(formatter) + " [" + ott.getZone() + "]");
    }
}
// TODO: задача на работу с датой и временем
        /*Самолет летит из Сиднея в Оттаву с двумя остановками в Хьюстоне и Вашингтоне.
        Самолет вылетает из Сиднея (дата любая, например 16 июня 2020) в 19:00 (время местное для Сиднея).
        Время в пути Сидней -  Хьюстон - 15 часов 35 минут
        Время ожидания в аэропорту Хьюстона - 1 час
        Время в пути  Хьюстон - Вашингтон - 2 часа 49 минут
        Время ожидания в аэропорту Вашингтона - 1 час 10 минут
        Время в пути Вашингтон - Оттава - 1 час 40 минут
        Вывести в консоль:
        Время прибытия! в аэропорт Оттавы (время местное для Оттавы)
        Время вылета! из аэропорта Хьюстона (время местное для Хьюстона)
        Время вылета! из аэропорта Вашингтона (время местное для Вашингтона)
        Часовые пояса:
        Сидней - Australia/Sydney
        Хьюстон - America/Chicago
        Вашингтон - America/New_York
        Оттава - America/Toronto*/