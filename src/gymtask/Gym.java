package gymtask;

import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;
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

    private GymVisitor [] dataBaseRegisteredVisitors = new GymVisitor[1]; //база данных зарегистрированных пользователей

    private int gymZoneRegisteredVisitorsCounter = 0; //данные о зарегистрированных местах в зонах
    private int swimmingPoolZoneRegisteredVisitorsCounter = 0;
    private int groupClassesZoneRegisteredVisitorsCounter = 0;
    private int allVisitorsCounter = 0; //общее число зарегистрированных людей
    private int currentVisitorsCounter = 0; //число людей в данный момент времени

    private GymVisitor [] currentGymZoneVisitors = new GymVisitor[1]; //люди в данный момент времени в определенной зоне
    private GymVisitor [] currentSwimmingPoolZoneVisitors = new GymVisitor[1];
    private GymVisitor [] currentGroupClassesVisitors = new GymVisitor[1];

    private int currentGymZoneVisitorsCounter = 0; //число людей в данный момент времени в определенной зоне
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

    public GymVisitor[] getCurrentGymZoneVisitors() {
        return currentGymZoneVisitors;
    }

    public void setCurrentGymZoneVisitors(GymVisitor[] currentGymZoneVisitors) {
        this.currentGymZoneVisitors = currentGymZoneVisitors;
    }

    public GymVisitor[] getCurrentSwimmingPoolZoneVisitors() {
        return currentSwimmingPoolZoneVisitors;
    }

    public void setCurrentSwimmingPoolZoneVisitors(GymVisitor[] currentSwimmingPoolZoneVisitors) {
        this.currentSwimmingPoolZoneVisitors = currentSwimmingPoolZoneVisitors;
    }

    public GymVisitor[] getCurrentGroupClassesVisitors() {
        return currentGroupClassesVisitors;
    }

    public void setCurrentGroupClassesVisitors(GymVisitor[] currentGroupClassesVisitors) {
        this.currentGroupClassesVisitors = currentGroupClassesVisitors;
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

    //todo: регистрация абонемента
    public void registerSubscription (Subscription subscription)
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

    //todo: закрытие клуба
    public void closeGym() throws InterruptedException
    {
        for (int i = 5; i > 0; i--)
        {
            System.out.printf("\rКлуб закрывается через %d...",i);
            Thread.sleep(1000);
        }
        setCurrentGroupClassesVisitors(null);
        setCurrentGymZoneVisitors(null);
        setCurrentSwimmingPoolZoneVisitors(null);
        setCurrentVisitorsCounter(0);
        System.out.println("\nКлиенты покинули клуб");
    }

    //todo: обслуживание клиента
    public void visitorReception(String zone, Subscription subscription)
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

    @Override
    public String toString() {
        return "Gym{" +
                "allVisitorsCounter=" + allVisitorsCounter +
                ", currentVisitorsCounter=" + currentVisitorsCounter +
                ", currentGymZoneVisitors=" + Arrays.toString(currentGymZoneVisitors) +
                ", currentSwimmingPoolZoneVisitors=" + Arrays.toString(currentSwimmingPoolZoneVisitors) +
                ", currentGroupClassesVisitors=" + Arrays.toString(currentGroupClassesVisitors) +
                '}';
    }

    //todo: проверка доступа клиента к зоне
    public void checkAccess(Subscription subscription, String zone)
    {
        LocalTime currentTime = LocalTime.now();
        switch (zone)
        {
            case "gym":
                if(getCurrentGymZoneVisitors().length >= 20)
                {
                    System.out.printf("В зоне %s нет свободных мест\n",zone);
                    return;
                }

            case "swimming pool":
                if(getCurrentSwimmingPoolZoneVisitors().length >= 20)
                {
                    System.out.printf("В зоне %s нет свободных мест\n",zone);
                    return;
                }

            case "group classes":
                if(getCurrentGroupClassesVisitors().length >= 20)
                {
                    System.out.printf("В зоне %s нет свободных мест\n",zone);
                    return;
                }

        }

        for(String s : subscription.gymOptions)
        {
            if(s != null && s.equals(zone) && currentTime.isAfter(subscription.startVisitTime) && currentTime.isBefore(subscription.endVisitTime))
            {
                System.out.printf("Вы можете пройти в %s\n", zone);
                switch (zone)
                {
                    case "gym":
                        if (getCurrentGymZoneVisitors()[0] == null)
                        {
                            getCurrentGymZoneVisitors()[0] = subscription.getVisitor();
                            setCurrentGymZoneVisitors(getCurrentGymZoneVisitors());
                            setCurrentGymZoneVisitorsCounter(getCurrentGymZoneVisitorsCounter() + 1);
                            setCurrentVisitorsCounter(getCurrentVisitorsCounter() + 1);
                        }
                        else if (!Arrays.asList(getCurrentGymZoneVisitors()).contains(subscription.getVisitor()))
                        {
                            GymVisitor [] gymVisitors = Arrays.copyOfRange(getCurrentGymZoneVisitors(),0,getCurrentGymZoneVisitors().length+1);
                            gymVisitors[getCurrentGymZoneVisitorsCounter()] = subscription.getVisitor();
                            setCurrentGymZoneVisitors(gymVisitors);
                            setCurrentGymZoneVisitorsCounter(getCurrentGymZoneVisitorsCounter() + 1);
                            setCurrentVisitorsCounter(getCurrentVisitorsCounter() + 1);
                        }
                        break;

                    case "swimming pool":
                        if (getCurrentSwimmingPoolZoneVisitors()[0] == null)
                        {
                            getCurrentSwimmingPoolZoneVisitors()[0] = subscription.getVisitor();
                            setCurrentSwimmingPoolZoneVisitors(getCurrentSwimmingPoolZoneVisitors());
                            setCurrentSwimmingPoolZoneVisitorsCounter(getCurrentSwimmingPoolZoneVisitorsCounter() + 1);
                            setCurrentVisitorsCounter(getCurrentVisitorsCounter() + 1);
                        }
                        else if(!Arrays.asList(getCurrentSwimmingPoolZoneVisitors()).contains(subscription.getVisitor()))
                        {
                            GymVisitor [] swimmigPoolVisitors = Arrays.copyOfRange(getCurrentSwimmingPoolZoneVisitors(),0,getCurrentSwimmingPoolZoneVisitors().length+1);
                            swimmigPoolVisitors[getCurrentSwimmingPoolZoneVisitorsCounter()] = subscription.getVisitor();
                            setCurrentSwimmingPoolZoneVisitors(swimmigPoolVisitors);
                            setCurrentSwimmingPoolZoneVisitorsCounter(getCurrentSwimmingPoolZoneVisitorsCounter() + 1);
                            setCurrentVisitorsCounter(getCurrentVisitorsCounter() + 1);
                        }
                        break;

                    case "group classes":
                        if (getCurrentGroupClassesVisitors()[0] == null)
                        {
                            getCurrentGroupClassesVisitors()[0] = subscription.getVisitor();
                            setCurrentGroupClassesVisitors(getCurrentGroupClassesVisitors());
                            setCurrentGroupClassesVisitorsCounter(getCurrentGroupClassesVisitorsCounter() + 1);
                            setCurrentVisitorsCounter(getCurrentVisitorsCounter() + 1);
                        }
                        else if (!Arrays.asList(getCurrentGroupClassesVisitors()).contains(subscription.getVisitor()))
                        {
                            GymVisitor [] groupClassesVisitors = Arrays.copyOfRange(getCurrentGroupClassesVisitors(),0,getCurrentGroupClassesVisitors().length+1);
                            groupClassesVisitors[getCurrentGroupClassesVisitorsCounter()] = subscription.getVisitor();
                            setCurrentGroupClassesVisitors(groupClassesVisitors);
                            setCurrentGroupClassesVisitorsCounter(getCurrentGroupClassesVisitorsCounter() + 1);
                            setCurrentVisitorsCounter(getCurrentVisitorsCounter() + 1);
                        }
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

    }

    //todo: распределение подписок
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





