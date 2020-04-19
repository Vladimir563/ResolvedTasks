package navalbattle.nbexceptions;

public class WrongLevelGameException extends Exception
{
    private String message = "Некорректный уровень сложности!";

    public String getMessage()
    {
        return message;
    }
}
