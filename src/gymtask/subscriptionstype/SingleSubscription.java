package gymtask.subscriptionstype;

import gymtask.GymVisitor;
import gymtask.Subscription;
import gymtask.interfaces.ISwimmingPoolable;

import java.time.LocalTime;
import java.util.Arrays;

public class SingleSubscription extends Subscription implements ISwimmingPoolable
{
    public SingleSubscription(int mCount, GymVisitor gVisitor)
    {
        super(mCount, gVisitor);
        startVisitTime = LocalTime.of(8,0);
        endVisitTime = LocalTime.of(22,0);
        gymOptions[1] = "swimming pool";
        description = "Разовый";
    }

}
