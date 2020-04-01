package gymtask;

import gymtask.interfaces.IGroupClassable;
import gymtask.interfaces.IGymable;
import gymtask.interfaces.ISwimmingPoolable;
import gymtask.subscriptionstype.DaySubscription;
import gymtask.subscriptionstype.SingleSubscription;
import gymtask.subscriptionstype.UnlimitedSubscription;

import java.time.LocalTime;
import java.util.Arrays;

public class Gym
{
    private IGroupClassable [] groupClassSubscriptions = new IGroupClassable[20];
    private IGymable [] gymSubscriptions = new IGymable[20];
    private ISwimmingPoolable [] swimmingPoolSubscriptions = new ISwimmingPoolable[20];
    private GymVisitor [] dataBaseRegisteredVisitors = new GymVisitor[1];

    private int gymZoneRegisteredVisitorsCounter = 0; //данные о зарегистрированных местах в зонах
    private int swimmingPoolZoneRegisteredVisitorsCounter = 0;
    private int groupClassesZoneRegisteredVisitorsCounter = 0;
    private int allVisitorsCounter = 0; //общее число зарегистрированных людей
    private int currentVisitorsCounter = 0; //число людей в данный момент времени

    private int currentGymZoneVisitorsCounter = 0; //число людей в данный момент времени в определнной зоне
    private int currentSwimmingPoolZoneVisitorsCounter = 0;
    private int currentGroupClassesVisitorsCounter = 0;

    public int getCurrentGymZoneVisitorsCounter() {
        return currentGymZoneVisitorsCounter;
    }

    public void setCurrentGymZoneVisitorsCounter(int currentGymZoneVisitorsCounter) {
        this.currentGymZoneVisitorsCounter = currentGymZoneVisitorsCounter;
    }

    public int getCurrentSwimmingPoolZoneVisitorsCounter() {
        return currentSwimmingPoolZoneVisitorsCounter;
    }

    public void setCurrentSwimmingPoolZoneVisitorsCounter(int currentSwimmingPoolZoneVisitorsCounter) {
        this.currentSwimmingPoolZoneVisitorsCounter = currentSwimmingPoolZoneVisitorsCounter;
    }

    public int getCurrentGroupClassesVisitorsCounter() {
        return currentGroupClassesVisitorsCounter;
    }

    public void setCurrentGroupClassesVisitorsCounter(int currentGroupClassesVisitorsCounter) {
        this.currentGroupClassesVisitorsCounter = currentGroupClassesVisitorsCounter;
    }

    public int getCurrentVisitorsCounter() {
        return currentVisitorsCounter;
    }

    public void setCurrentVisitorsCounter(int currentVisitorsCounter) {
        this.currentVisitorsCounter = currentVisitorsCounter;
    }

    public void setDataBaseRegisteredVisitors(GymVisitor[] dataBaseRegisteredVisitors) {
        this.dataBaseRegisteredVisitors = dataBaseRegisteredVisitors;
    }

    public GymVisitor[] getDataBaseRegisteredVisitors() {
        return dataBaseRegisteredVisitors;
    }

    public int getGymZoneRegisteredVisitorsCounter() {
        return gymZoneRegisteredVisitorsCounter;
    }

    public void setGymZoneRegisteredVisitorsCounter(int gymZoneRegisteredVisitorsCounter) {
        this.gymZoneRegisteredVisitorsCounter = gymZoneRegisteredVisitorsCounter;
    }

    public int getSwimmingPoolZoneRegisteredVisitorsCounter() {
        return swimmingPoolZoneRegisteredVisitorsCounter;
    }

    public void setSwimmingPoolZoneRegisteredVisitorsCounter(int swimmingPoolZoneRegisteredVisitorsCounter) {
        this.swimmingPoolZoneRegisteredVisitorsCounter = swimmingPoolZoneRegisteredVisitorsCounter;
    }

    public int getGroupClassesZoneRegisteredVisitorsCounter() {
        return groupClassesZoneRegisteredVisitorsCounter;
    }

    public void setGroupClassesZoneRegisteredVisitorsCounter(int groupClassesZoneRegisteredVisitorsCounter) {
        this.groupClassesZoneRegisteredVisitorsCounter = groupClassesZoneRegisteredVisitorsCounter;
    }

    public void setAllVisitorsCounter(int allVisitorsCounter) {
        this.allVisitorsCounter = allVisitorsCounter;
    }

    public int getAllVisitorsCounter() {
        return allVisitorsCounter;
    }

    public void registerSubscription (Subscription subscription) //регистрация абонемента
    {

        distributeSubscription(subscription, "group classes", getGroupClassesZoneRegisteredVisitorsCounter(), groupClassSubscriptions);

        distributeSubscription(subscription, "gym", getGymZoneRegisteredVisitorsCounter(), gymSubscriptions);

        distributeSubscription(subscription, "swimming pool", getSwimmingPoolZoneRegisteredVisitorsCounter(), swimmingPoolSubscriptions);

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Gym gym = (Gym) o;
        return getAllVisitorsCounter() == gym.getAllVisitorsCounter() &&
                Arrays.equals(groupClassSubscriptions, gym.groupClassSubscriptions) &&
                Arrays.equals(gymSubscriptions, gym.gymSubscriptions) &&
                Arrays.equals(swimmingPoolSubscriptions, gym.swimmingPoolSubscriptions) &&
                Arrays.equals(getDataBaseRegisteredVisitors(), gym.getDataBaseRegisteredVisitors());
    }

    //todo: переделать
    public void closeGym()
    {
        dataBaseRegisteredVisitors = new GymVisitor[]{};
    }

    public void visitorReception(String zone, Subscription subscription) //обслуживание клиента
    {
        if(getDataBaseRegisteredVisitors()[0] == null)
        {
            getDataBaseRegisteredVisitors()[0] = subscription.getVisitor();
            setDataBaseRegisteredVisitors(getDataBaseRegisteredVisitors());
        }

        if (Arrays.asList(getDataBaseRegisteredVisitors()).contains(subscription.getVisitor())) // если есть пользователь в базе
        {
            if(subscription instanceof DaySubscription)
            {
                checkAccess(subscription, zone);
            }

            if( subscription instanceof UnlimitedSubscription)
            {
                checkAccess(subscription, zone);
            }

            if( subscription instanceof SingleSubscription)
            {
                checkAccess(subscription, zone);
            }
            return;
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
                "allVisitorsCounter=" + allVisitorsCounter +
                ", currentVisitorsCounter=" + currentVisitorsCounter +
                ", currentGymZoneVisitorsCounter=" + currentGymZoneVisitorsCounter +
                ", currentSwimmingPoolZoneVisitorsCounter=" + currentSwimmingPoolZoneVisitorsCounter +
                ", currentGroupClassesVisitorsCounter=" + currentGroupClassesVisitorsCounter +
                '}';
    }

    public void checkAccess(Subscription subscription, String zone) //проверка доступа клиента к зоне
    {
        LocalTime currentTime = LocalTime.now();
        for(String s : subscription.gymOptions)
        {
            if(s != null && s.equals(zone) && currentTime.isAfter(subscription.startVisitTime) && currentTime.isBefore(subscription.endVisitTime))
            {
                System.out.printf("Вы можете пройти в %s\n", zone);
                setCurrentVisitorsCounter(getCurrentVisitorsCounter() + 1);
                switch (zone)
                {
                    case "gym":
                        setCurrentGymZoneVisitorsCounter(getCurrentGymZoneVisitorsCounter() + 1);
                        break;
                    case "swimming pool":
                        setCurrentSwimmingPoolZoneVisitorsCounter(getCurrentSwimmingPoolZoneVisitorsCounter() + 1);
                        break;
                    case "group classes":
                        setCurrentGroupClassesVisitorsCounter(getCurrentGroupClassesVisitorsCounter() + 1);
                        break;
                }
                return;
            }
        }

        if(!Arrays.asList(subscription.gymOptions).contains(zone))
        {
            System.out.printf("Вы не можете пройти в %s (причина: абонемент не включает в себя \"%s\")\n",zone,zone);
        }

        if(!currentTime.isAfter(subscription.startVisitTime) || !currentTime.isBefore(subscription.endVisitTime))
        {
            System.out.printf("Вы не можете пройти в %s (причина: абонемент не действует в данное время %,d:%,d)\n",zone,currentTime.getHour(),currentTime.getMinute());
        }
/*        if()
        {

        }*/

    }

    public <T> void distributeSubscription (Subscription subscription, String type, int counter, T[] subscriptions)
    {

        if(Arrays.asList(subscription.gymOptions).contains(type)) // если подписка реализует интерфейс (содержит в массиве нужную зону)
        {
            if(counter >= 20)
            {
                System.out.printf("Лимит подписок на %s исчерпан\n",subscription.getClass().getName());
                return;
            }

            subscriptions[counter] = (T)subscription;
            switch (type)
            {
                case "gym":
                    setGymZoneRegisteredVisitorsCounter(getGymZoneRegisteredVisitorsCounter() + 1);
                    break;
                case "group classes":
                    setGroupClassesZoneRegisteredVisitorsCounter(getGroupClassesZoneRegisteredVisitorsCounter() + 1);
                    break;
                case "swimming pool":
                    setSwimmingPoolZoneRegisteredVisitorsCounter(getSwimmingPoolZoneRegisteredVisitorsCounter() + 1);
                    break;
            }

            if(getDataBaseRegisteredVisitors()[0] == null)
            {
                getDataBaseRegisteredVisitors()[0] = subscription.getVisitor();
                setDataBaseRegisteredVisitors(getDataBaseRegisteredVisitors());
                setAllVisitorsCounter(getAllVisitorsCounter()+1);
            }
            else if(!Arrays.asList(getDataBaseRegisteredVisitors()).contains(subscription.getVisitor()))
            {
                GymVisitor [] newVisitors = Arrays.copyOfRange(getDataBaseRegisteredVisitors(),0,getDataBaseRegisteredVisitors().length+1);
                newVisitors[newVisitors.length-1] = subscription.getVisitor();
                setDataBaseRegisteredVisitors(newVisitors);
                setAllVisitorsCounter(getAllVisitorsCounter()+1);
            }

        }
    }
}





