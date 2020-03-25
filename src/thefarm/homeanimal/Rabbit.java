package thefarm.homeanimal;

import thefarm.HomeAnimal;
import thefarm.ICanBeEatable;

public class Rabbit extends HomeAnimal implements ICanBeEatable
{

    public Rabbit(String name, double weight, int speed, int health, int resourcesCount)
    {
        super(name, weight, speed, health, resourcesCount);
    }
}
