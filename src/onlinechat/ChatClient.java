package onlinechat;

import onlinechat.clientsthreads.ClientReaderThread;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class ChatClient
{
    private String ip;
    private int port;
    private Scanner scanner;
    private ChatConnection chatConnection;
    private Thread clientReaderThread;
    private int hashCode;
    private String strFromUser;

    public ChatClient(String ip, int port)
    {
        this.ip = ip;
        this.port = port;
        scanner = new Scanner(System.in);
    }

    public void start() throws IOException, ClassNotFoundException
    {
        chatConnection = new ChatConnection(new Socket(ip,port)); //todo: соединяемся с сервером

        System.out.println("Connection successful!\n");

        ChatMessage messageFromServer = chatConnection.getMessage(); //todo: получаем сообщение с уникальным кодом от сервера

        hashCode = Integer.parseInt(messageFromServer.getTextMessage()); //todo: получаем уникальный код из сообщения

        //todo: формируем сообщение и отправляем на сервер в основном потоке
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        System.out.printf("(Authorized as %s)\n", name);

        //todo: запускаем reader поток для чтения собщений от сервера
        clientReaderThread = new Thread(new ClientReaderThread(ip,port,chatConnection));
        clientReaderThread.start();

        while (true)
        {
            System.out.print("Your message: ");
            strFromUser = scanner.nextLine();

            if(strFromUser.equalsIgnoreCase("exit"))
            {
                System.out.println("Client offline...");
                chatConnection.closeConnection();
                break;
            }

            try
            {
                chatConnection.sendMessage(new ChatMessage(name, strFromUser + " " + hashCode));
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        System.exit(0);
    }


    public static void main(String[] args) throws InterruptedException, IOException
    {
        String ip = "127.0.0.1";
        int port = 8099;
        ChatClient chatClient = new ChatClient(ip,port);
        try
        {
            chatClient.start();
        }
        catch (Exception e)
        {
            System.out.println("Server is not available. Attempt to reconnect..");

            for (int i = 1; i < 6; i++)
            {
                System.out.printf("Trying to connect (%d)...\n",i);
                try
                {
                    Thread.sleep(5000);
                    chatClient.start();
                }
                catch (Exception ex)
                {
                    System.out.println("Failed connection attempt.\n");
                    Thread.sleep(1000);
                }
            }
            System.out.println("Server is not available.The Application will be closed");
        }
    }
}


