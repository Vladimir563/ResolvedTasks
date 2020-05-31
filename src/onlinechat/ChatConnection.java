package onlinechat;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.time.LocalDateTime;

public class ChatConnection
{
    private Socket socket; // сокет (с помощью которого можно получить потоки чтения и записи)
    private ObjectOutputStream outputStream;
    private ObjectInputStream inputStream;

    public ChatConnection(Socket socket) throws IOException
    {
        this.socket = socket;
        //todo: получаем потоки для работы с обьектами из сокета
        outputStream = new ObjectOutputStream(this.socket.getOutputStream());
        inputStream = new ObjectInputStream(this.socket.getInputStream());
    }

    public Socket getSocket() {
        return socket;
    }

    public void sendMessage(ChatMessage chatMessage) throws IOException
    {
        chatMessage.setDepartureDate(LocalDateTime.now()); //устанавливаем время отправки сообщения
        outputStream.writeObject(chatMessage);
        outputStream.flush(); //выталкивает остатки байт после последней порции байт (чтобы очистить поток)
    }

    public ChatMessage getMessage() throws IOException, ClassNotFoundException
    {
        return (ChatMessage) inputStream.readObject();
    }


    public void closeConnection() throws IOException
    {
        //закрываем потоки и сокет
        inputStream.close();
        outputStream.close();
        socket.close();
    }
}
