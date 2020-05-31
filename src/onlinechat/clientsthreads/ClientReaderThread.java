package onlinechat.clientsthreads;
import onlinechat.ChatConnection;
import java.io.IOException;


public class ClientReaderThread implements Runnable
{
    private String ip;
    private int port;
    private ChatConnection chatConnection;

    public ClientReaderThread(String ip, int port, ChatConnection chatConnection)
    {
        this.ip = ip;
        this.port = port;
        this.chatConnection = chatConnection;
    }

    @Override
    public void run()
    {
        while (true)
        {
            try
            {
                if(Thread.currentThread().isInterrupted()) //todo: проверить работает или нет?
                {
                    System.out.println("Close connection...");
                    chatConnection.closeConnection();
                    break;
                }
                else
                {
                    System.out.println(chatConnection.getMessage().toString());
                }
            }
            catch (IOException | ClassNotFoundException e)
            {
                System.out.println("Client disconnected...");
                break;
            }
        }
    }
}
