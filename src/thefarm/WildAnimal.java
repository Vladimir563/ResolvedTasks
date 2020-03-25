package thefarm;

public class WildAnimal extends Animal
{
    protected int power;
    protected int escapeCounter = 0;

    public WildAnimal(String name, double weight, int speed, int power)
    {
        super(name, weight, speed);
        this.power = power;
    }

    public int getPower()
    {
        return power;
    }

    public void setPower(int power)
    {
        this.power = power;
    }

    public void eatOrHurtHomeAnimal(HomeAnimal homeAnimal)
    {
        if(this.power >= homeAnimal.getHealth())
        {
            System.out.printf("%s был(а) сьеден(а) %s\n", homeAnimal.name, this.name);
            homeAnimal.setAnimalAlive(false);
        }
        else
        {
            homeAnimal.setHealth(homeAnimal.getHealth() - this.power);
        }

    }
}
