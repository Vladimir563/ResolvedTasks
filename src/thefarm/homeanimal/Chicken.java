package thefarm.homeanimal;

import thefarm.HomeAnimal;
import thefarm.ICanBeEatable;
import thefarm.IGiveResourcesable;

public class Chicken extends HomeAnimal implements IGiveResourcesable, ICanBeEatable
{
    public Chicken(String name, double weight, int speed, int health, int resourcesCount)
    {
        super(name, weight, speed, health, resourcesCount);
    }
}
