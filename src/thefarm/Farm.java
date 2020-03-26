package thefarm;

import thefarm.homeanimal.Cat;
import thefarm.homeanimal.Chicken;
import thefarm.homeanimal.Cow;
import thefarm.homeanimal.Rabbit;

import java.util.Date;
import java.util.Random;

import static thefarm.Main.*;

public class Farm
{
    public static HomeAnimal [] homeAnimals = new HomeAnimal[10];
    static Random rand = new Random(new Date().getTime());

    public Farm()
    {
        int animalNum;
        HomeAnimal animal = null;
        for (int i = 0; i < homeAnimals.length; i++)
        {
            animalNum = rand.nextInt(4);
            switch (animalNum)
            {
                case 0:
                    animal = new Cow("Cow", rand.nextInt(20 + 1) + 5, rand.nextInt(20 + 1) + 5,rand.nextInt(20 + 1) + 5, rand.nextInt(20 + 1) + 5);
                    break;
                case 1:
                    animal = new Chicken("Chicken", rand.nextInt(20 + 1) + 5, rand.nextInt(20 + 1) + 5,rand.nextInt(20 + 1) + 5, rand.nextInt(20 + 1) + 5);
                    break;
                case 2:
                    animal = new Rabbit("Rabbit", rand.nextInt(20 + 1) + 5, rand.nextInt(20 + 1) + 5,rand.nextInt(20 + 1) + 5, 0);
                    break;
                case 3:
                    animal = new Cat("Cat", rand.nextInt(20 + 1) + 5, rand.nextInt(20 + 1) + 5,rand.nextInt(20 + 1) + 5, 0);
                    break;
            }
            homeAnimals[i] = animal;
        }
    }


    public static void printHomeAnimals()
    {
        for(HomeAnimal animal : homeAnimals)
        {
            System.out.println(animal);
        }
    }

    public void passDay(Farmer farmer)
    {
        while(farmer.getResourcesCount() >= 1) //если ресурсов не осталось, игра заканчивается
        {
            System.out.println(farmer.toString());
            farmer.setResourcesCount(farmer.getResourcesCount() - 2); //Фермер тратит 2 единицы ресурсов
            WildAnimal wildAnimal1 = WildField.wildAnimals[rand.nextInt(3)]; //Приходит дикое животное (выбирается рандомно из массива с дикими животными)
            HomeAnimal homeAnimal = Farm.homeAnimals[rand.nextInt(10)]; //домашнее животное (выбирается рандомно)
            if(!farmer.driveAwayWildAnimal(wildAnimal1,rand.nextBoolean())) //Иногда (рандомно) фермер может прогнать дикое животное
            {
                if(wildAnimal1.escapeCounter < 3)
                {
                    homeAnimal.runningAwayFromWildAnimal(wildAnimal1,homeAnimal); /*пытается поймать
                    (съесть, либо ранить) домашнее животное (выбирается рандомно). Если домашнее животное убежало, дикое уходит
                    ни с чем*/
                }
            }

            if (wildAnimal1.escapeCounter > 3)
            {
                System.out.printf(ANSI_RED + "%s больше не может прийти на ферму\n" + ANSI_RESET,wildAnimal1.name);
                wildAnimal1.canComeToTheFarm = false;
            }
            for(HomeAnimal hm : Farm.homeAnimals)
            {
                farmer.feedHomeAnimal(hm);
            }

            farmer.eatHomeAnimal();
            System.out.printf(ANSI_CYAN + "Текущие ресурсы фермера: %d\n" + ANSI_RESET, farmer.getResourcesCount());
            System.out.println("____________________________");
        }

        System.out.printf(ANSI_RED + "GameOver" + ANSI_RESET);
    }
/*    День на ферме (метод passDay):
        1. Фермер тратит 2 единицы ресурсов (если ресурсов не осталось, игра заканчивается).
        2. Приходит дикое животное (выбирается рандомно из массива с дикими животными), пытается поймать
            (съесть, либо ранить) домашнее животное (выбирается рандомно). Если домашнее животное убежало,
            дикое уходит ни с чем.
        3. Иногда (рандомно) фермер может прогнать дикое животное.
        4. Фермер кормит всех животных (животные восполняют здоровье)
        5. Фермер собирает ресурсы с животных, с которых можно их собирать. Если таких не осталось,
             съедает животное, пригодное в пищу (если такие остались).*/

}
