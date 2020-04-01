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
            System.out.printf(textColours.ANSI_RED.getCode() + "!!!%s пришел на ферму!!!\n" + textColours.ANSI_RESET.getCode(),this.name);
            System.out.println(textColours.ANSI_YELLOW.getCode() + "Нельзя съесть умершее животное" + textColours.ANSI_RESET.getCode());
        }
        else if(this.power > homeAnimal.getHealth())
        {
            System.out.printf(textColours.ANSI_RED.getCode() + "!!!%s пришел на ферму!!!\n" + textColours.ANSI_RESET.getCode(),this.name);
            System.out.printf(textColours.ANSI_RED.getCode() + "%s был(а) сьеден(а) %s\n" + textColours.ANSI_RESET.getCode(), homeAnimal.name, this.name);
            homeAnimal.setAnimalAlive(false);
            homeAnimal.setHealth(0);
        }
        else
        {
            System.out.printf(textColours.ANSI_RED.getCode()+ "!!!%s пришел на ферму!!!\n" + textColours.ANSI_RESET.getCode(),this.name);
            System.out.printf(textColours.ANSI_RED.getCode() + "Животное %s ранено %s (-%d hp)\n" + textColours.ANSI_RESET.getCode(), homeAnimal.name, this.name, this.power);
            homeAnimal.setHealth(homeAnimal.getHealth() - this.power);
        }
    }
}
