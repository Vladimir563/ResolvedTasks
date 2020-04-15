package exceptiontasks.myexceptions;

public class AccessDeniedException extends Exception
{
    private String message;

    public AccessDeniedException()
    {
        message = "В доступе отказано";
    }

    @Override
    public String getMessage()
    {
        return message;
    }
}
