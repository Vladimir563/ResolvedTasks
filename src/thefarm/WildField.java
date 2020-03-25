package thefarm;

import thefarm.wildanimal.Bear;
import thefarm.wildanimal.Wolf;
import thefarm.wildanimal.Fox;

import java.util.Arrays;
import java.util.Date;
import java.util.Random;

public class WildField
{
     static Random rand = new Random(new Date().getTime());
    public static WildAnimal [] wildAnimals = {
    new Wolf("Wolf", rand.nextInt(20 + 1) + 5, rand.nextInt(20 + 1) + 5,rand.nextInt(20 + 1) + 5),
            new Fox("Fox", rand.nextInt(20 + 1) + 5, rand.nextInt(20 + 1) + 5,rand.nextInt(20 + 1) + 5),
            new Bear("Bear", rand.nextInt(20 + 1) + 5, rand.nextInt(20 + 1) + 5,rand.nextInt(20 + 1) + 5)
    };

    public WildAnimal[] getWildAnimals() {
        return wildAnimals;
    }

    public void setWildAnimals(WildAnimal[] wildAnimals) {
        this.wildAnimals = wildAnimals;
    }

    @Override
    public String toString() {
        return "WildField{" +
                "wildAnimals=" + Arrays.toString(wildAnimals) +
                '}';
    }
}
