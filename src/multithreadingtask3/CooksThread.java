package multithreadingtask3;

import java.util.concurrent.ArrayBlockingQueue;

public class CooksThread implements Runnable
{
    private ArrayBlockingQueue <Order> queueOfOrdersForTheCook;
    private ArrayBlockingQueue <Order> queueCookedOrdersForDelivery;

    public CooksThread(ArrayBlockingQueue<Order> queueOfOrdersForTheCook, ArrayBlockingQueue<Order> queueCookedOrdersForDelivery)
    {
        this.queueOfOrdersForTheCook = queueOfOrdersForTheCook;
        this.queueCookedOrdersForDelivery = queueCookedOrdersForDelivery;
    }

    @Override
    public void run()
    {
        try
        {
            while (true)
            {
                Order order = queueOfOrdersForTheCook.take();
                System.out.println("Повар получает заказ от официанта..." + order.toString());
                order.setOrdersStatus(Status.DELIVERED);

                queueCookedOrdersForDelivery.put(order);
                System.out.println("Повар отдает готовый заказ официанту для доставки клиенту...");
                if(queueOfOrdersForTheCook.isEmpty()) return;
            }
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }
}
