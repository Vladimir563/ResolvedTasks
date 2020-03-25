package thefarm.wildanimal;

import thefarm.WildAnimal;

public class Wolf extends WildAnimal
{
    public Wolf(String name, double weight, int speed, int power)
    {
        super(name, weight, speed, power);
    }

    @Override
    public String toString() {
        return "Wolf{" +
                "power=" + power +
                ", name='" + name + '\'' +
                ", weight=" + weight +
                ", speed=" + speed +
                '}';
    }
}
