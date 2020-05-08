package iopart2tasks;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
// объект типа Connection можно создавать в try-with-resources
// благодаря implements AutoCloseable и переопределенному
// методу close()
public class Connection implements AutoCloseable
{
    private Socket socket;
    private ObjectOutputStream output;
    private ObjectInputStream input;

    public Connection(Socket socket) throws IOException
    {
        this.socket = socket;
        output = new ObjectOutputStream(
                this.socket.getOutputStream());
        input = new ObjectInputStream(
                this.socket.getInputStream());
    }

    public void sendMessage(SimpleMessage message) throws IOException
    {
        message.setDateTime();
        output.writeObject(message);
        output.flush();
    }

    public SimpleMessage readMessage() throws IOException, ClassNotFoundException
    {
        return (SimpleMessage) input.readObject();
    }

    // если объект типа Connection создан в try-with-resources
    // при завершении блока try метод close будет вызван
    // автоматически
    @Override
    public void close() throws Exception
    {
        input.close();
        output.close();
        socket.close();
    }
}
