package exceptiontasks.myexceptions;

public class JarException extends Exception
{
    public JarException(String message)
    {
        super(message);
    }

    @Override
    public String getMessage()
    {
        return super.getMessage();
    }
}
