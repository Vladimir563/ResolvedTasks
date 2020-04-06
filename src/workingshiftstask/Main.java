package workingshiftstask;

import java.time.LocalDate;

public class Main
{
    public static void main(String[] args)
    {
        WorkingShifts.returnNameOfCurrentWorkingShift();

        LessonsCounter counter = new LessonsCounter();
        counter.returnRemainingLessons();
    }
}


/*
//todo:
    Задача 2
            Есть три рабочие смены :
            с 7:00 до 15:00
            с 15:00 до 23:00
            с 23:00 до 7:00
            Определить, какая сейчас смена (относительно текущего времени)
//todo:
            Задача 3
            Допустим, занятия закончатся 20 июня 2020 года.
            Сколько занятий осталось, если они проходят 3 раза в неделю (пн, ср, пт)?*/
