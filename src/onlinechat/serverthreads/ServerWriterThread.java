package onlinechat.serverthreads;
import onlinechat.ChatConnection;
import onlinechat.ChatMessage;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentHashMap;

public class ServerWriterThread implements Runnable
{
    private ConcurrentHashMap <Integer, ChatConnection> connectionsContainer;
    private ArrayBlockingQueue <ChatMessage> clientsMessages;
    private ChatConnection chatConnection;
    private Integer connectionKey;

    public ServerWriterThread(ConcurrentHashMap<Integer, ChatConnection> connectionsContainer, ArrayBlockingQueue<ChatMessage> clientsMessages)
    {
        this.connectionsContainer = connectionsContainer;
        this.clientsMessages = clientsMessages;
    }


    @Override
    public void run()
    {
        System.out.println("ServerWriterThread running...");
        //todo: поток writer берет каждое сообщение из очереди и рассылает всем (пока что) клиентам
        while (true)
        {
            try
            {
                ChatMessage chatMessage = clientsMessages.take(); //todo: берем первое сообщение в очереди
                for(Map.Entry<Integer, ChatConnection> connectionEntry : connectionsContainer.entrySet()) //todo: рассылаем его всем подключившимся клиентам
                {
                    String [] s = chatMessage.getTextMessage().split(" ");
                    int code = Integer.parseInt(s[s.length-1]); //todo: парсим из сообщения клента код

                    if(code != connectionEntry.getKey())
                    {
                        chatConnection = connectionEntry.getValue(); //todo: берем соединений из мапы
                        connectionKey = connectionEntry.getKey(); //todo: берем соответствующий этому соединению ключ
                        //todo: (чтобы была возможность удалить соединение из мапы по ключу)
                        chatConnection.sendMessage(chatMessage);
                    }
                }
            }
            catch (InterruptedException | IOException e)
            {
                connectionsContainer.remove(connectionKey); //todo: если соединений закрыто удаляем его из мапы
            }
        }
    }
}

