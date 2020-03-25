package thefarm.wildanimal;

import thefarm.WildAnimal;

public class Fox extends WildAnimal
{
    public Fox(String name, double weight, int speed, int power)
    {
        super(name, weight, speed, power);
    }

    @Override
    public String toString() {
        return "Fox{" +
                "power=" + power +
                ", name='" + name + '\'' +
                ", weight=" + weight +
                ", speed=" + speed +
                '}';
    }
}
