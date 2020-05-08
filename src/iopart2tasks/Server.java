package iopart2tasks;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server
{
    private int port;
    private Connection connection;

    public Server(int port) {
        this.port = port;
    }

    public void start() throws IOException, ClassNotFoundException
    {
        int connectionsCount = 0;
        try (ServerSocket serverSocket = new ServerSocket(port))
        {
            System.out.println("Сервер запущен...");
            while (true)
            {
                // /help
                // /count
                // /ping
                Socket clientSocket = serverSocket.accept(); // слушает порт, чтобы когда кто-то пытается подключиться получить его сокет
                connection = new Connection(clientSocket); // устанавливает соединение при помощи полученного сокета
                String str = connection.readMessage().getText();
                if(str.equals("/count"))
                {
                    connectionsCount++;
                    connection.sendMessage(SimpleMessage.getInstance("server", "количество запросов на сервер: " + connectionsCount));
                }
                else if(str.equals("/help"))
                {
                    connectionsCount++;
                    connection.sendMessage(SimpleMessage.getInstance("server","\n/help - список доступных команд\n/count - количество подключений сервера\n" +
                            "/ping - время за которое сообщение доходит до сервера и возвращается обратно\n/exit - выйти из программы (завершение программы)\n"));
                }
                else if(str.equals("/ping"))
                {
                    connectionsCount++;
                    connection.sendMessage(SimpleMessage.getInstance("server","ответ"));

                }
                else if (str.equals("/exit"))
                {
                    connectionsCount++;
                    connection.sendMessage(SimpleMessage.getInstance("server", "завершение программы..."));
                }
                else
                {
                    connectionsCount++;
                    connection.sendMessage(SimpleMessage.getInstance("server", "несуществующая команда"));
                }
            }
        }
    }

    public static void main(String[] args)
    {
        int port = 8099;
        Server server = new Server(port);
        try
        {
            server.start();
        }
        catch (IOException | ClassNotFoundException e)
        {
            e.printStackTrace();
        }
    }
}
