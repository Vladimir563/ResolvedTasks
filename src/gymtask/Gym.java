package gymtask;

import gymtask.interfaces.IGroupClassable;
import gymtask.interfaces.IGymable;
import gymtask.interfaces.ISwimmingPoolable;
import gymtask.subscriptionstype.DaySubscription;
import gymtask.subscriptionstype.SingleSubscription;
import gymtask.subscriptionstype.UnlimitedSubscription;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Objects;

public class Gym
{
    private IGroupClassable [] groupClassSubscriptions = new IGroupClassable[20];
    private int groupClassCounter = 0;
    private IGymable [] gymSubscriptions = new IGymable[20];
    private int gymCounter = 0;
    private ISwimmingPoolable [] swimmingPoolSubscriptions = new ISwimmingPoolable[20];
    private int swimmingPoolCounter = 0;
    private GymVisitor [] visitors = new GymVisitor[1];
    private int visitorsCounter = 0;

    public void registerSubscription(Subscription subscription)
    {
        if(subscription instanceof IGroupClassable)
        {
            if(groupClassCounter >= 20)
            {
                System.out.println("Лимит подписок на групповые занятия исчерпан\n");
                return;
            }
            groupClassSubscriptions[groupClassCounter] = (IGroupClassable) subscription;
            groupClassCounter++;
            for (int i = 0; i < visitors.length ; i++)
            {
                if(visitors[0] == null)
                {
                    visitors[0] = subscription.getVisitior();
                    break;
                }
                else if(!visitors[i].equals(subscription.getVisitior()))
                {
                    GymVisitor [] newVisitors = new GymVisitor[visitors.length + 1];
                    newVisitors[newVisitors.length - 1] = subscription.getVisitior();
                }
            }
        }

        if (subscription instanceof IGymable)
        {
            if(gymCounter >= 20)
            {
                System.out.println("Лимит подписок на тренажерный зал исчерпан\n");
                return;
            }
            gymSubscriptions[gymCounter] = (IGymable) subscription;
            gymCounter++;
            for (int i = 0; i < visitors.length ; i++)
            {
                if(visitors[0] == null)
                {
                    visitors[0] = subscription.getVisitior();
                    break;
                }
                else if(!visitors[i].equals(subscription.getVisitior()))
                {
                    GymVisitor [] newVisitors = new GymVisitor[visitors.length + 1];
                    newVisitors[newVisitors.length - 1] = subscription.getVisitior();
                }
            }
        }

        if (subscription instanceof ISwimmingPoolable)
        {
            if(swimmingPoolCounter >= 20)
            {
                System.out.println("Лимит подписок на бассейн исчерпан\n");
                return;
            }
            swimmingPoolSubscriptions[swimmingPoolCounter] = (ISwimmingPoolable) subscription;
            swimmingPoolCounter++;
            for (int i = 0; i < visitors.length ; i++)
            {
                if(visitors[0] == null)
                {
                    visitors[0] = subscription.getVisitior();
                    break;
                }
                else if(!visitors[i].equals(subscription.getVisitior()))
                {
                    GymVisitor [] newVisitors = new GymVisitor[visitors.length + 1];
                    newVisitors[newVisitors.length - 1] = subscription.getVisitior();
                }
            }
        }
    }

    public GymVisitor[] getVisitors() {
        return visitors;
    }

    public int getVisitorsCounter() {
        return visitorsCounter;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Gym gym = (Gym) o;
        return groupClassCounter == gym.groupClassCounter &&
                gymCounter == gym.gymCounter &&
                swimmingPoolCounter == gym.swimmingPoolCounter &&
                visitorsCounter == gym.visitorsCounter &&
                Arrays.equals(groupClassSubscriptions, gym.groupClassSubscriptions) &&
                Arrays.equals(gymSubscriptions, gym.gymSubscriptions) &&
                Arrays.equals(swimmingPoolSubscriptions, gym.swimmingPoolSubscriptions) &&
                Arrays.equals(visitors, gym.visitors);
    }

    public void closeGym()
    {
        visitors = new GymVisitor[]{};
    }

    public void visitorReception(GymVisitor visitor, String zone, Subscription subscription)
    {
        if(visitors[0] == null)
        {
            visitors[0] = visitor;
        }
        for (GymVisitor v : visitors)
        {
            if(v.equals(visitor))
            {
                if(subscription instanceof DaySubscription)
                {
                    helpMethod(subscription, zone);
                }

                if( subscription instanceof UnlimitedSubscription)
                {
                    helpMethod(subscription, zone);
                }

                if( subscription instanceof SingleSubscription)
                {
                    helpMethod(subscription, zone);
                }
                return;
            }
        }
        System.out.println("Данный посетитель не зарегистрирован!\n");
    }

/*    Когда клиент приходит в фитнес клуб, он сообщает желаемую зону и показывает абонемент.
    Необходимо проверить может ли данный посетитель пройти в желаемую зону и пропустить его,
    если возможность существует; если посетитель не может пройти, необходимо сообщить ему причину.

    Посетитель не может пройти, если время абонемента не соответсвует текущему времени,
    если он пытается пройти в зону, которая не разрешена по его абонементу,
    если в зоне нет свободных мест.*/

    @Override
    public String toString() {
        return "Gym{" +
                "groupClassSubscriptions=" + Arrays.toString(groupClassSubscriptions) +
                ", groupClassCounter=" + groupClassCounter +
                ", gymSubscriptions=" + Arrays.toString(gymSubscriptions) +
                ", gymCounter=" + gymCounter +
                ", swimmingPoolSubscriptions=" + Arrays.toString(swimmingPoolSubscriptions) +
                ", swimmingPoolCounter=" + swimmingPoolCounter +
                '}';
    }

    public void helpMethod(Subscription subscription, String zone)
    {
        LocalTime currentTime = LocalTime.now();
        for(String s : subscription.gymOptions)
        {
            if(s != null && s.equals(zone) && currentTime.isAfter(subscription.startVisitTime) && currentTime.isBefore(subscription.endVisitTime))
            {
                System.out.printf("Вы можете пройти в %s\n", zone);
                visitorsCounter++;
                return;
            }
        }
        for (int i = 0; i < subscription.gymOptions.length ; i++)
        {
            if(subscription.gymOptions[i].equals(zone))
            {
                break;
            }
            else System.out.printf("Вы не можете пройти в %s (причина: абонемент не включает в себя \"%s\")\n",zone,zone);
            break;
        }

        if(!currentTime.isAfter(subscription.startVisitTime) || !currentTime.isBefore(subscription.endVisitTime))
        {
            System.out.printf("Вы не можете пройти в %s (причина: абонемент не действует в данное время %,d:%,d)\n",zone,currentTime.getHour(),currentTime.getMinute());
        }
/*        if()
        {

        }*/

    }
}


