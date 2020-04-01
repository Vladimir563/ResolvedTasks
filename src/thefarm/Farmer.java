package thefarm;

import static thefarm.Main.*;

public class Farmer
{
    //данный вариант синглтон нельзя использовать в многопоточных средах
    private int resourcesCount = 5;

    private static Farmer farmer;

    private boolean areWhereCanGivenAnimals;

    private static Farm farm;

    private Farmer()
    {
    }

    public static Farmer getInstance(Farm f) //получить экземпляр единственного фермера на ферме
    {
        farm = f;
        if(farmer == null)
        {
            farmer = new Farmer();
        }
        return farmer;
    }

    public void packingResources(HomeAnimal animal) //собрать ресурсы
    {
        resourcesCount += 2;
        animal.setResourcesCount(animal.getResourcesCount() - 2);
    }

    public void eatHomeAnimal() // сьесть домашнее животное
    {
        areWhereCanGivenAnimals = false;
        for (int i = 0; i < farm.getHomeAnimals().length; i++)
        {
            if(farm.getHomeAnimals()[i] instanceof IGiveResourcesable && farm.getHomeAnimals()[i].isAnimalAlive() && farm.getHomeAnimals()[i].getResourcesCount() > 0)
            {
                System.out.printf(textColours.ANSI_BLUE.getCode() + "Сбор ресурсов с животного %s (собрано %d)\n" + textColours.ANSI_RESET.getCode(),farm.getHomeAnimals()[i].name, 2);
                packingResources(farm.getHomeAnimals()[i]);
                areWhereCanGivenAnimals = true;
            }
        }

        if(!areWhereCanGivenAnimals)
        {
            System.out.println(textColours.ANSI_RED.getCode()+ "На ферме не осталось животных которые могут дать ресурс\n" + textColours.ANSI_RESET.getCode());
            for (int i = 0; i < farm.getHomeAnimals().length; i++)
            {
                if(farm.getHomeAnimals()[i] instanceof ICanBeEatable && farm.getHomeAnimals()[i].isAnimalAlive())
                {
                    resourcesCount += farm.getHomeAnimals()[i].getWeight();
                    farm.getHomeAnimals()[i].weight = 0;
                    farm.getHomeAnimals()[i].setAnimalAlive(false);
                    System.out.printf(textColours.ANSI_RED.getCode() + "%s сьедено фермером\n" + textColours.ANSI_RESET.getCode(),farm.getHomeAnimals()[i].name );
                    return;
                }
            }
        }
    }

    @Override
    public String toString()
    {
        return textColours.ANSI_CYAN.getCode() + "Farmer's resourcesCount = " + resourcesCount + textColours.ANSI_RESET.getCode();
    }

    public boolean driveAwayWildAnimal(WildAnimal wildAnimal, boolean isCanDriveAway)
    {
        boolean isRunAway = false;
        if(isCanDriveAway && wildAnimal.escapeCounter <= 3)
        {
            wildAnimal.escapeCounter++;
            System.out.printf(textColours.ANSI_GREEN.getCode() + "Фермер прогнал %s (сбежал(а): %d раз)\n" + textColours.ANSI_RESET.getCode(),wildAnimal.name,wildAnimal.escapeCounter);
            isRunAway = true;
        }
        return isRunAway;
    }

    public void feedHomeAnimal(HomeAnimal homeAnimal)
    {
        if(homeAnimal.isAnimalAlive() && homeAnimal.getHealth() > 0)
        {
            homeAnimal.recoverHealth();
            System.out.printf(textColours.ANSI_PURPLE.getCode() + "Здоровье %s восстановлено (%d)\n" + textColours.ANSI_RESET.getCode(), homeAnimal.name, homeAnimal.getHealth());
        }
        else System.out.println(textColours.ANSI_RED.getCode() + "Нельзя кормить умершее животное" + textColours.ANSI_RESET.getCode());
    }

    public int getResourcesCount() {
        return resourcesCount;
    }

    public void setResourcesCount(int resourcesCount) {
        this.resourcesCount = resourcesCount;
    }
}

