package thefarm;

import static thefarm.Main.*;

public class WildAnimal extends Animal
{
    protected int power;
    protected int escapeCounter = 0;
    protected boolean canComeToTheFarm = true;

    public WildAnimal(String name, double weight, int speed, int power)
    {
        super(name, weight, speed);
        this.power = power;
    }

    public int getPower()
    {
        return power;
    }

    public void setPower(int power)
    {
        this.power = power;
    }

    public void eatOrHurtHomeAnimal(HomeAnimal homeAnimal)
    {
        if(!homeAnimal.isAnimalAlive())
        {
            System.out.printf(ANSI_RED + "!!!%s пришел на ферму!!!\n" + ANSI_RESET,this.name);
            System.out.println(ANSI_YELLOW + "Нельзя съесть умершее животное" + ANSI_RESET);
        }
        else if(this.power > homeAnimal.getHealth())
        {
            System.out.printf(ANSI_RED + "!!!%s пришел на ферму!!!\n" + ANSI_RESET,this.name);
            System.out.printf(ANSI_RED + "%s был(а) сьеден(а) %s\n" + ANSI_RESET, homeAnimal.name, this.name);
            homeAnimal.setAnimalAlive(false);
            homeAnimal.setHealth(0);
        }
        else
        {
            System.out.printf(ANSI_RED + "!!!%s пришел на ферму!!!\n" + ANSI_RESET,this.name);
            System.out.printf(ANSI_RED + "Животное %s ранено %s (-%d hp)\n" + ANSI_RESET, homeAnimal.name, this.name, this.power);
            homeAnimal.setHealth(homeAnimal.getHealth() - this.power);
        }
    }
}
