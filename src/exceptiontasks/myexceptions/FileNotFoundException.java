package exceptiontasks.myexceptions;

public class FileNotFoundException extends Exception
{
    private String message;

    public FileNotFoundException()
    {
        message = "Файл не найден";
    }

    @Override
    public String getMessage()
    {
        return message;
    }
}
