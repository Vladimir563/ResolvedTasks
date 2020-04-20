package collectionstasks;

import java.util.*;

public class MessageTask {
    public static void countEachPriority(List<Message> messageList)
    {
        // TODO:  Подсчитать количество сообщений для каждого приоритета
        //  Ответ в консоль
        ArrayList<Message> lowPriorityMessages = new ArrayList<>();
        ArrayList<Message> highPriorityMessages = new ArrayList<>();
        ArrayList<Message> mediumPriorityMessages = new ArrayList<>();
        ArrayList<Message> urgentPriorityMessages = new ArrayList<>();

        for(Message message : messageList)
        {
            if (message.getPriority().equals(MessagePriority.HIGH)) highPriorityMessages.add(message);
            if (message.getPriority().equals(MessagePriority.MEDIUM)) mediumPriorityMessages.add(message);
            if (message.getPriority().equals(MessagePriority.URGENT)) urgentPriorityMessages.add(message);
            if (message.getPriority().equals(MessagePriority.LOW)) lowPriorityMessages.add(message);
        }
        System.out.println("HIGH = " + highPriorityMessages.size());
        System.out.println("MEDIUM = " + mediumPriorityMessages.size());
        System.out.println("URGENT = " + urgentPriorityMessages.size());
        System.out.println("LOW = " + lowPriorityMessages.size());
    }

    public static void countEachCode(List<Message> messageList)
    {
        // TODO: Подсчитать количество сообщений для каждого кода сообщения
        //  Ответ в консоль
        ArrayList<ArrayList<Message>> listListMessages = new ArrayList<>();
        int [] codeArr = new int[10];
        for (int i = 0; i < codeArr.length; i++)
        {
            codeArr[i] = i;
            listListMessages.add(new ArrayList<>());
            for(Message message : messageList)
            {
                if(message.getCode() == i)
                {
                    listListMessages.get(i).add(message);
                }
            }
            System.out.println("Сообщений с кодом " + i + " = " + listListMessages.get(i).size());
        }
    }

    public static void uniqueMessageCount(List<Message> messageList)
    {
        // TODO: Подсчитать количество уникальных сообщений
        //  Ответ в консоль
        HashSet<Message> uniqueMessages = new HashSet<>(messageList);
        System.out.println(uniqueMessages.size());
    }

    public static List<Message> uniqueMessagesInOriginalOrder(List<Message> messageList)
    {
        // TODO: вернуть только неповторяющиеся сообщения и в том порядке,
        //  в котором они встретились в первоначальном списке
        //  Например, было: [{URGENT, 4}, {HIGH, 9}, {LOW, 3}, {HIGH, 9}]
        //  на выходе: [{URGENT, 4}, {HIGH, 9}, {LOW, 3}]
        LinkedHashSet<Message> messageLinkedHashSet = new LinkedHashSet<>(messageList);
        messageList.clear();
        messageList.addAll(messageLinkedHashSet);
        return messageList;
    }

    public static void removeEach(List<Message> messageList, MessagePriority priority)
    {
        // TODO: удалить из коллекции каждое сообщение с заданным приоритетом
        //  вывод в консоль до удаления и после удаления
        System.out.println("До удаления сообщений с приоритетом " + "(" + priority.name() +"):\n" + messageList);
        Iterator<Message> messageIterator = messageList.listIterator();
        while (messageIterator.hasNext())
        {
            if(messageIterator.next().getPriority().name().equals(priority.name()))
            {
                messageIterator.remove();
            }
        }
        System.out.println("После удаления сообщений с приоритетом " + "(" + priority.name() +"):\n" + messageList);
    }

    public static void removeOther(List<Message> messageList, MessagePriority priority)
    {
        // TODO: удалить из коллекции все сообщения, кроме тех, которые имеют заданный приоритет
        //  вывод в консоль до удаления и после удаления
        System.out.println("До удаления всех сообщений кроме приоритета " + "(" + priority.name() +"):\n" + messageList);
        Iterator<Message> messageIterator = messageList.listIterator();
        while (messageIterator.hasNext())
        {
            if(!messageIterator.next().getPriority().name().equals(priority.name()))
            {
                messageIterator.remove();
            }
        }
        System.out.println("После удаления всех сообщений кроме приоритета " + "(" + priority.name() +"):\n" + messageList);
    }

    public static void main(String[] args) {
        // вызов методов
        List<Message> messages = MessageGenerator.generate(34);
        System.out.println(messages);
        System.out.println("\nКоличество сообщений для каждого приоритета");
        MessageTask.countEachPriority(messages);
        System.out.println("\nКоличество сообщений для каждого кода сообщения");
        MessageTask.countEachCode(messages);
        System.out.println("\nКоличество уникальных сообщений");
        MessageTask.uniqueMessageCount(messages);
        System.out.println("\nВернуть только неповторяющиеся сообщения и в том порядке, в котором они встретились в первоначальном списке");
        System.out.println("До: " + messages);
        System.out.println("После: " + MessageTask.uniqueMessagesInOriginalOrder(messages));
        System.out.println("\nУдалить из коллекции каждое сообщение с заданным приоритетом");
        MessageTask.removeEach(messages, MessagePriority.LOW);
        System.out.println("\nУдалить из коллекции все сообщения, кроме тех, которые имеют заданный приоритет");
        MessageTask.removeOther(messages, MessagePriority.HIGH);
    }
}
