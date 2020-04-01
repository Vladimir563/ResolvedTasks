package gymtask;

import gymtask.interfaces.IGymable;
import gymtask.interfaces.ISwimmingPoolable;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;

public class Subscription implements IGymable
{
    protected LocalTime startVisitTime;
    protected LocalTime endVisitTime;
    protected String [] gymOptions = new String[3];
    protected LocalDate dateOfRegistration;
    protected LocalDate dateOfExclusion;
    private GymVisitor visitior;
    private int monthCount;

    public String getDescription() {
        return description;
    }

    protected String description;

    public Subscription(int mCount, GymVisitor gVisitor)
    {
        this.dateOfRegistration = LocalDate.now();
        monthCount = mCount;
        this.dateOfExclusion = dateOfRegistration.plusMonths(monthCount);
        this.visitior = gVisitor;
        gymOptions[0] = "gym";
    }

    public LocalDate getDateOfRegistration() {
        return dateOfRegistration;
    }

    public LocalDate getDateOfExclusion() {
        return dateOfExclusion;
    }

    public GymVisitor getVisitior() {
        return visitior;
    }

    public int getMonthCount() {
        return monthCount;
    }

    @Override
    public String toString() {
        return "Subscription{" +
                "startVisitTime=" + startVisitTime +
                ", endVisitTime=" + endVisitTime +
                ", gymOptions=" + Arrays.toString(gymOptions) +
                ", dateOfRegistration=" + dateOfRegistration +
                ", dateOfExclusion=" + dateOfExclusion +
                ", visitior=" + visitior +
                ", monthCount=" + monthCount +
                '}';
    }
}

