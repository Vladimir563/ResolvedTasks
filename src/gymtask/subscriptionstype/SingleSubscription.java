package gymtask.subscriptionstype;

import gymtask.GymVisitor;
import gymtask.Subscription;
import gymtask.interfaces.ISwimmingPoolable;
import java.time.LocalTime;

public class SingleSubscription extends Subscription implements ISwimmingPoolable
{
    public SingleSubscription(GymVisitor gVisitor)
    {
        super(gVisitor);
        startVisitTime = LocalTime.of(8,0);
        endVisitTime = LocalTime.of(22,0);
        gymOptions[1] = swimmingPoolClassesZonesZone;
        description = singleSudDescription;
    }

}
