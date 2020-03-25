package thefarm;

public class Farmer
{
    private int resourcesCount = 5;

    private static Farmer farmer;

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
        resourcesCount += animal.getResourcesCount();
        animal.setResourcesCount(0);
    }

    public void eatHomeAnimal() // сьесть домашнее животное
    {
        for (int i = 0; i < Farm.homeAnimals.length; i++)
        {
            if(Farm.homeAnimals[i] instanceof IGiveResourcesable && Farm.homeAnimals[i].isAnimalAlive() && Farm.homeAnimals[i].getResourcesCount() != 0)
            {
                System.out.println("На ферме еще есть животные которые могут дать ресурс\n");
                packingResources(Farm.homeAnimals[i]);
                break;
            }
            else if (Farm.homeAnimals[i] instanceof ICanBeEatable && Farm.homeAnimals[i].isAnimalAlive() && !(Farm.homeAnimals[i] instanceof IGiveResourcesable))
            {
                resourcesCount += Farm.homeAnimals[i].getWeight();
                Farm.homeAnimals[i].setAnimalAlive(false);
                System.out.printf("%s сьедено фермером\n",Farm.homeAnimals[i].name );
                break;
            }
        }
    }

    @Override
    public String toString() {
        return "Farmer{" +
                "resourcesCount=" + resourcesCount +
                '}';
    }

    public void driveAwayWildAnimal(WildAnimal wildAnimal, boolean isCanDriveAway)
    {
        if(isCanDriveAway)
        {
            wildAnimal.escapeCounter++;
            System.out.printf("Фермер прогнал %s (сбежал(а): %d раз)\n",wildAnimal.name,wildAnimal.escapeCounter);
        }
    }

    public void feedHomeAnimal(HomeAnimal homeAnimal)
    {
        homeAnimal.recoverHealth();
        System.out.printf("Здоровье %s восстановлено (%d)\n", homeAnimal.name, homeAnimal.getHealth());
    }

    public int getResourcesCount() {
        return resourcesCount;
    }

    public void setResourcesCount(int resourcesCount) {
        this.resourcesCount = resourcesCount;
    }
}

