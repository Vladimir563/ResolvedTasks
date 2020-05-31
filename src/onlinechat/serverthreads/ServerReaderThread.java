package onlinechat.serverthreads;

import onlinechat.ChatConnection;
import onlinechat.ChatMessage;
import java.io.IOException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentHashMap;

//todo: reader потоки будут создаваться по одному на подключенного клиента
public class ServerReaderThread implements Runnable
{
    private ConcurrentHashMap<Integer, ChatConnection> socketContainer; //todo: ссылка на контейнер для хранения подключений к серверу
    private ArrayBlockingQueue<ChatMessage> clientsMessages; //todo: ссылка на блокирующую очередь сообщений от клиентов
    private ChatConnection chatConnection;//todo: ссылка на подключение
    private Integer generatedCode; //todo: ссылка на уникальный код

    public ServerReaderThread(ConcurrentHashMap<Integer, ChatConnection> socketContainer, ArrayBlockingQueue<ChatMessage> clientsMessages, ChatConnection chatConnection, Integer generatedCode)
    {
        this.socketContainer = socketContainer;
        this.clientsMessages = clientsMessages;
        this.chatConnection = chatConnection;
        this.generatedCode = generatedCode;
    }

    @Override
    public void run()
    {
        socketContainer.put(generatedCode,chatConnection); //todo: складываем в мапу полученное соединение
        //todo: ключ - уникальный код сгенерированный сервером
        while (true)
        {
            try
            {
                clientsMessages.put(chatConnection.getMessage()); //todo: складываем в блокирующую очередь сообщение от клиента
            }
            catch (InterruptedException | IOException | ClassNotFoundException e)
            {
                System.out.println("Client disconnected from server...");
                break;
            }
        }
    }
}
