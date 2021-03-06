package thefarm;

import static thefarm.Main.*;

public class HomeAnimal extends Animal
{
    private int health;
    private int startHealth;
    private int resourcesCount;
    private boolean isAnimalAlive = true;
    public HomeAnimal(String name, double weight, int speed, int health, int resourcesCount)
    {
        super(name, weight, speed);
        this.health = health;
        this.resourcesCount = resourcesCount;
        this.startHealth = health;
    }

    public boolean isAnimalAlive()
    {
        return isAnimalAlive;
    }

    public void setAnimalAlive(boolean animalAlive) {
        isAnimalAlive = animalAlive;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getResourcesCount() {
        return resourcesCount;
    }

    public void setResourcesCount(int resourcesCount) {
        this.resourcesCount = resourcesCount;
    }

    @Override
    public String toString() {
        return "HomeAnimal{" +
                "health=" + health +
                ", resourcesCount=" + resourcesCount +
                ", isAnimalAlive=" + isAnimalAlive +
                ", name='" + name + '\'' +
                ", weight=" + weight +
                ", speed=" + speed +
                '}';
    }

    public void recoverHealth()
    {
        if(startHealth > health)
        {
            health++;
        }
    }

    public void runningAwayFromWildAnimal(WildAnimal wildAnimal, HomeAnimal homeAnimal)
    {
        if(homeAnimal.speed > wildAnimal.speed)
        {
            System.out.printf(textColours.ANSI_GREEN.getCode() + "%s убежал(а) от %s\n" + textColours.ANSI_RESET.getCode(), homeAnimal.name,wildAnimal.name);
            System.out.printf( textColours.ANSI_RED.getCode() + "%s уходит ни с чем...\n" + textColours.ANSI_RESET.getCode(), wildAnimal.name);
        }
        else
        {
            wildAnimal.eatOrHurtHomeAnimal(homeAnimal);
        }
    }
}
