package thefarm;

public class Main
{
    public static void main(String[] args)
    {
        Farm farm = new Farm();
        farm.passDay(Farmer.getInstance());
    }
}
