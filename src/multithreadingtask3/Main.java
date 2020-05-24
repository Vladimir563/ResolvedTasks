package multithreadingtask3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ArrayBlockingQueue;

enum Status
{
    SENT, GETTING_READY, DELIVERED
}

public class Main
{
    public static void main(String[] args)
    {
        ArrayBlockingQueue <Order> queueOfOrdersReceived = new ArrayBlockingQueue<>(10, true);
        ArrayBlockingQueue <Order> queueOfOrdersForTheCook = new ArrayBlockingQueue<>(10, true);
        ArrayBlockingQueue <Order> queueCookedOrdersForDelivery = new ArrayBlockingQueue<>(10, true);


        ArrayList<Thread> clients = new ArrayList<>(Arrays.asList(new Thread(new ClientsThread(queueOfOrdersReceived,queueCookedOrdersForDelivery)),
                new Thread(new ClientsThread(queueOfOrdersReceived,queueCookedOrdersForDelivery)),
                new Thread(new ClientsThread(queueOfOrdersReceived,queueCookedOrdersForDelivery)),
                new Thread(new ClientsThread(queueOfOrdersReceived,queueCookedOrdersForDelivery)))
        );

        Thread officiant1 = new Thread(new OfficiantsThread(queueOfOrdersReceived, queueOfOrdersForTheCook));
        Thread cook1 = new Thread(new CooksThread(queueOfOrdersForTheCook,queueCookedOrdersForDelivery));


        for (Thread thread : clients)
        {
            thread.start();
        }
        officiant1.start();
        cook1.start();

        try
        {
            for(Thread thread : clients)
            {
                thread.join();
            }
            officiant1.join();
            cook1.join();
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }
}

//todo:
//  Потоки:
//        Клиент формирует заказ (создает объект)
//        передает в очередь 1 / забирает готовый из очереди 3
//        Официант
//        забирает из очереди 1 и передает в очередь 2
//        Повар
//        забирает из очереди 2 и передает в очередь 3
//        Очереди:
//        1 клиент-официант
//        2 официант-повар
//        3 повар-клиент
