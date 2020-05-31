package onlinechat;

import onlinechat.serverthreads.ServerReaderThread;
import onlinechat.serverthreads.ServerWriterThread;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentHashMap;

public class ChatServer
{
    private int port;
    private int generatedCode;

    public ChatServer(int port)
    {
        this.port = port;
    }

    private ConcurrentHashMap<Integer, ChatConnection> connectionsContainer = new ConcurrentHashMap<>(); //контейнер для хранения подключений клиентов к серверу
    private ArrayBlockingQueue<ChatMessage> clientsMessages = new ArrayBlockingQueue<>(50); //блокирующая очередь сообщений от клиентов

    public void start() throws IOException
    {
        new Thread(new ServerWriterThread(connectionsContainer,clientsMessages)).start(); //todo: создаем и запускаем поток рассылающий все сообщения всем клиентам

        try(ServerSocket serverSocket = new ServerSocket(port))
        {
            System.out.println("Waiting for connection....");
            while (true)
            {
                Socket sSocket = serverSocket.accept(); //todo: слушаем порт и получаем клиентский сокет
                generatedCode = ((int) (Math.random() * 1_000_000)); //todo: генерируем уникальный код (для распознания клиентских сообщений)
                ChatConnection chatConnection = new ChatConnection(sSocket); //todo: создаем подключение на основе полученного клиентского сокета
                chatConnection.sendMessage(new ChatMessage("server","" + generatedCode)); //todo: сервер отправляет уникальный код клиенту
                new Thread(new ServerReaderThread(connectionsContainer,clientsMessages, chatConnection,generatedCode)).start(); //todo: создаем и стартуем поток для подключившегося клиента
            }
        }
    }

    public static void main(String[] args)
    {
        int port = 8099;
        ChatServer chatServer = new ChatServer(port);
        try
        {
            chatServer.start();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}

//todo:проблемы на текущий момент:
// 1) подумать как сделать чтобы когда принимаешь сообщение оно не было в одной строчке с "Your message"
// 2) разобраться до конца с закрытием соединения (убрать лишний код, если можно)

