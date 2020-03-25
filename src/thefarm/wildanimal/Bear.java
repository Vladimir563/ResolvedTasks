package thefarm.wildanimal;

import thefarm.WildAnimal;

public class Bear extends WildAnimal
{
    public Bear(String name, double weight, int speed, int power)
    {
        super(name, weight, speed, power);
    }

    @Override
    public String toString() {
        return "Bear{" +
                "power=" + power +
                ", name='" + name + '\'' +
                ", weight=" + weight +
                ", speed=" + speed +
                '}';
    }
}
