package multithreadingtask3;

import com.sun.org.apache.xpath.internal.operations.Or;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;

public class ClientsThread implements Runnable
{
    private ArrayBlockingQueue <Order> queueOfOrdersReceived ;
    private ArrayBlockingQueue <Order> queueCookedOrdersForDelivery;

    private String [] clientsSurName = new String[]{"Иванов","Петров", "Сидоров", "Смирнов"};
    private String [] clientsName = new String[]{"Иван","Петр", "Олег", "Игорь"};
    private String [] dishes = new String[]{"Спагетти", "Кофе", "Чай", "Пицца","Салат", "Томатный суп"};

    public ClientsThread(ArrayBlockingQueue<Order> queueOfOrdersReceived, ArrayBlockingQueue<Order> queueCookedOrdersForDelivery)
    {
        this.queueOfOrdersReceived = queueOfOrdersReceived;
        this.queueCookedOrdersForDelivery = queueCookedOrdersForDelivery;
    }

    @Override
    public void run()
    {
        Random random = new Random();
        try
        {
            String clientName = clientsName[random.nextInt(clientsName.length)];
            String clientSurName = clientsSurName[random.nextInt(clientsSurName.length)];
            int table = random.nextInt(10);
            System.out.println("Клиент " + "\"" + clientName + " " + clientSurName + "\"" + " (номер стола " + table +") " + " формирует заказ...");
            Order order = new Order(
                    clientName + " " + clientSurName,
                    dishes[random.nextInt(dishes.length)],
                    table,
                    Status.SENT);
            System.out.println(order.toString());
            queueOfOrdersReceived.put(order);

            System.out.println("Клиент " + "\"" + clientName + " " + clientSurName + "\"" + " (номер стола " + table + ") " + " получает готовый заказ..." + queueCookedOrdersForDelivery.take().toString());
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }
}
