package thefarm;

import static thefarm.Main.*;

public class Farmer
{
    //данный вариант синглтон нельзя использовать в многопоточных средах
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
    public void eatHomeAnimal(HomeAnimal [] animals) // сьесть домашнее животное
    {
        areWhereCanGivenAnimals = false;
        for (int i = 0; i < animals.length; i++)
        {
            if(animals[i] instanceof IGiveResourcesable && animals[i].isAnimalAlive() && animals[i].getResourcesCount() > 0)
            {
                System.out.printf(textColours.ANSI_BLUE.getCode() + "Сбор ресурсов с животного %s (собрано %d)\n" + textColours.ANSI_RESET.getCode(),animals[i].name, 2);
                packingResources(animals[i]);
                areWhereCanGivenAnimals = true;
            }
        }

        if(!areWhereCanGivenAnimals)
        {
            System.out.println(textColours.ANSI_RED.getCode()+ "На ферме не осталось животных которые могут дать ресурс\n" + textColours.ANSI_RESET.getCode());
            for (int i = 0; i < animals.length; i++)
            {
                if(animals[i] instanceof ICanBeEatable && animals[i].isAnimalAlive())
                {
                    resourcesCount += animals[i].getWeight();
                    animals[i].weight = 0;
                    animals[i].setAnimalAlive(false);
                    System.out.printf(textColours.ANSI_RED.getCode() + "%s сьедено фермером\n" + textColours.ANSI_RESET.getCode(),animals[i].name );
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

