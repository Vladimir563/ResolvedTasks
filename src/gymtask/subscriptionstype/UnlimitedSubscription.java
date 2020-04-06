package gymtask.subscriptionstype;

import gymtask.GymVisitor;
import gymtask.Subscription;
import gymtask.interfaces.IGroupClassable;
import gymtask.interfaces.ISwimmingPoolable;
import java.time.LocalTime;

public class UnlimitedSubscription extends Subscription implements ISwimmingPoolable, IGroupClassable
{
    public UnlimitedSubscription(int mCount, GymVisitor gVisitor)
    {
        super(mCount, gVisitor);
        startVisitTime = LocalTime.of(8,0);
        endVisitTime = LocalTime.of(22,00);
        gymOptions [1] = swimmingPoolClassesZonesZone;
        gymOptions [2] = groupClassesZonesZone;
        description = unlimitedSudDescription;
    }

}
