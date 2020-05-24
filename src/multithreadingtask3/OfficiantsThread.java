package multithreadingtask3;

import java.util.concurrent.ArrayBlockingQueue;

public class OfficiantsThread implements Runnable
{
    private ArrayBlockingQueue <Order> queueOfOrdersReceived;
    private ArrayBlockingQueue <Order> queueOfOrdersForTheCook;

    public OfficiantsThread(ArrayBlockingQueue<Order> queueOfOrdersReceived, ArrayBlockingQueue<Order> queueOfOrdersForTheCook)
    {
        this.queueOfOrdersReceived = queueOfOrdersReceived;
        this.queueOfOrdersForTheCook = queueOfOrdersForTheCook;
    }

    @Override
    public void run()
    {
        try
        {
            while (true)
            {
                Order order = queueOfOrdersReceived.take();
                System.out.println("Официант забирает заказ из очереди на готовку.." + order.toString());
                order.setOrdersStatus(Status.GETTING_READY);

                System.out.println("Официант передает заказ повару..");
                queueOfOrdersForTheCook.put(order);
                if(queueOfOrdersReceived.isEmpty()) return;
            }
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }
}
