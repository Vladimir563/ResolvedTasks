package iopart2tasks;

import java.io.IOException;
import java.io.InputStream;
import java.net.ConnectException;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.Scanner;

public class Client
{
    private String ip;
    private int port;
    private Scanner scanner;

    public Client(String ip, int port)
    {
        this.ip = ip;
        this.port = port;
        scanner = new Scanner(System.in);
    }

    private Socket getSocket() throws IOException
    {
        Socket socket = new Socket(ip, port);
        return socket;
    }

    private void sendAndPrintMessage(SimpleMessage message) throws Exception
    {
        try (Connection connection = new Connection(getSocket()))
        {
            connection.sendMessage(message);
            SimpleMessage fromServer = connection.readMessage();
            System.out.println(fromServer);
        }
        catch(ConnectException e)
        {
            System.out.println("Сервер не доступен. Попробуйте подключиться позднее...");
        }
    }

    public void start() throws Exception
    {
        // /help
        // /count
        // /exit
        // /ping
        System.out.print("Введите имя: ");
        String name = scanner.nextLine();
        String command;
        while (true)
        {
            System.out.print("Введите команду: ");
            command = scanner.nextLine();
            switch (command)
            {
                case "/help":
                    sendAndPrintMessage(SimpleMessage.getInstance(name,"/help"));
                    break;
                case "/count":
                    sendAndPrintMessage(SimpleMessage.getInstance(name,"/count"));
                    break;
                case "/ping":
                    try(Connection connection = new Connection(getSocket()))
                    {
                        connection.sendMessage(SimpleMessage.getInstance(name,"/ping"));
                        Long timeBefore = System.nanoTime();
                        connection.readMessage();
                        Long timeAfter = System.nanoTime();
                        System.out.println("Пинг в наносекундах: " + (timeAfter - timeBefore));
                    }
                    break;
                case "/exit":
                    sendAndPrintMessage(SimpleMessage.getInstance(name,"/exit"));
                    return;
                default:
                    sendAndPrintMessage(SimpleMessage.getInstance(name,command));
                    break;
            }
        }
    }

    public static void main(String[] args)
    {

        int port = Integer.parseInt(ClientPropertiesLoader.getInstance("settings.properties").getProperties().getProperty("port"));
        String ip = ClientPropertiesLoader.getInstance("settings.properties").getProperties().getProperty("ip");

        try
        {
            new Client(ip, port).start();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}


//    По документации рассмотреть Socket
//
//todo:
//    Клиент должен брать настройки ip и port из properties файла
//todo:
//    Если клиент пытается подключиться к незапущенному серверу / пытается отправить сообщение,
//    а серевер не запущен, клиент не должен прекращать работу с ошибкой
//    (в консоли мы должны увидеть "попробуйте подключиться позднее")
//todo:
//    Работа с клиентом / сервером:
//    Клиент от пользователя принимает список команд:
//        /help - пользователь хочет узнать список доступных команд
//        /count - пользователь хочет узнать количество подключений сервера
//        /ping - пользователь хочет узнать время за которое сообщение доходит до сервера и возвращается обратно
//        /exit - пользователь хочет выйти из программы (завершение программы)
//todo:
//        Сервер принимает, обрабатывает запрос и отправляет ответ на следующие команды:
//        /help - список доступных команд /count - количество подключений сервера
//        /ping - время за которое сообщение доходит до сервера и возвращается обратно
