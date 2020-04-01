package gymtask.subscriptionstype;

import gymtask.GymVisitor;
import gymtask.Subscription;
import gymtask.interfaces.IGroupClassable;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;

public class DaySubscription extends Subscription implements IGroupClassable
{
    public DaySubscription(int mCount, GymVisitor gVisitor)
    {
        super(mCount, gVisitor);
        startVisitTime = LocalTime.of(8,0);
        endVisitTime = LocalTime.of(16,0);
        gymOptions[1] = "group classes";
        description = "Дневной";
    }

}