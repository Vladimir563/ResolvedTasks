package thefarm;

import static thefarm.Main.*;

public class Farmer
{
    private int resourcesCount = 5;

    private static Farmer farmer;

    private boolean areWhereCanGivenAnimals;

    private Farmer()
    {
    }

    public static Farmer getInstance() //получить экземпляр единственного фермера на ферме
    {
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
        for (int i = 0; i < Farm.homeAnimals.length; i++)
        {
            if(Farm.homeAnimals[i] instanceof IGiveResourcesable && Farm.homeAnimals[i].isAnimalAlive() && Farm.homeAnimals[i].getResourcesCount() > 0)
            {
                System.out.printf(ANSI_BLUE + "Сбор ресурсов с животного %s (собрано %d)\n" + ANSI_RESET,Farm.homeAnimals[i].name, 2);
                packingResources(Farm.homeAnimals[i]);
                areWhereCanGivenAnimals = true;
            }
        }

        if(!areWhereCanGivenAnimals)
        {
            System.out.println(ANSI_RED + "На ферме не осталось животных которые могут дать ресурс\n" + ANSI_RESET);
            for (int i = 0; i < Farm.homeAnimals.length; i++)
            {
                if(Farm.homeAnimals[i] instanceof ICanBeEatable && Farm.homeAnimals[i].isAnimalAlive())
                {
                    resourcesCount += Farm.homeAnimals[i].getWeight();
                    Farm.homeAnimals[i].weight = 0;
                    Farm.homeAnimals[i].setAnimalAlive(false);
                    System.out.printf(ANSI_RED + "%s сьедено фермером\n" + ANSI_RESET,Farm.homeAnimals[i].name );
                    return;
                }
            }
        }
    }

    @Override
    public String toString()
    {
        return ANSI_CYAN + "Farmer's resourcesCount = " + resourcesCount + ANSI_RESET;
    }

    public boolean driveAwayWildAnimal(WildAnimal wildAnimal, boolean isCanDriveAway)
    {
        boolean isRunAway = false;
        if(isCanDriveAway && wildAnimal.escapeCounter <= 3)
        {
            wildAnimal.escapeCounter++;
            System.out.printf(ANSI_GREEN + "Фермер прогнал %s (сбежал(а): %d раз)\n" + ANSI_RESET,wildAnimal.name,wildAnimal.escapeCounter);
            isRunAway = true;
        }
        return isRunAway;
    }

    public void feedHomeAnimal(HomeAnimal homeAnimal)
    {
        if(homeAnimal.isAnimalAlive() && homeAnimal.getHealth() > 0)
        {
            homeAnimal.recoverHealth();
            System.out.printf(ANSI_PURPLE + "Здоровье %s восстановлено (%d)\n" + ANSI_RESET, homeAnimal.name, homeAnimal.getHealth());
        }
        else System.out.println(ANSI_RED + "Нельзя кормить умершее животное" + ANSI_RESET);
    }

    public int getResourcesCount() {
        return resourcesCount;
    }

    public void setResourcesCount(int resourcesCount) {
        this.resourcesCount = resourcesCount;
    }
}

