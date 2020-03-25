package lesson10.thefarm.farmexceptions;

public class WrongTypeOfAnimal extends Exception
{
    public WrongTypeOfAnimal(String message)
    {
        System.out.println(message);
    }
}
