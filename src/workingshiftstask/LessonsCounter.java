package workingshiftstask;

import java.time.LocalDate;

public class LessonsCounter
{
    private LocalDate today = LocalDate.now();
    private LocalDate endOfLessonsDate = LocalDate.of(2020,6,20);
    private int lessonsCount = 0;

    public LocalDate getToday() {
        return today;
    }

    public void setToday(LocalDate today) {
        this.today = today;
    }

    public LocalDate getEndOfLessonsDate() {
        return endOfLessonsDate;
    }

    public void setEndOfLessonsDate(LocalDate endOfLessonsDate) {
        this.endOfLessonsDate = endOfLessonsDate;
    }

    public int getLessonsCount() {
        return lessonsCount;
    }

    public void setLessonsCount(int lessonsCount) {
        this.lessonsCount = lessonsCount;
    }

    public void returnRemainingLessons()
    {
        while (today.isBefore(getEndOfLessonsDate()))
        {
            if(getToday().getDayOfWeek().getValue() % 2 != 0 && getToday().getDayOfWeek().getValue() != 7 && today.getDayOfMonth() != LocalDate.now().getDayOfMonth())
            {
                setLessonsCount(getLessonsCount()+1);
            }
            setToday(getToday().plusDays(1));
        }

        System.out.printf("Осталось уроков: %s\n",getLessonsCount());
    }
}

