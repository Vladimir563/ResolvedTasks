package navalbattle;

import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;
import navalbattle.battlefield.BattleField;
import navalbattle.battlefield.UserMove;
import navalbattle.battleships.Deck;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.Scanner;

public class Game
{
    private UserMove[] usersMoves = new UserMove[100]; //массив в который будут записываться все ходы
    private int counterUsersMoves = 0;
    public static int usersScore = 0;
    public static int opponentScore = 0;
    private textColours playersHit;
    private textColours opponentsHit;

    public textColours getPlayersHit() {
        return playersHit;
    }

    public void setPlayersHit(textColours playersHit) {
        this.playersHit = playersHit;
    }

    public textColours getOpponentsHit() {
        return opponentsHit;
    }

    public void setOpponentsHit(textColours opponentsHit) {
        this.opponentsHit = opponentsHit;
    }

    public void start()
    {
        Scanner in = new Scanner(System.in);
        BattleField battleField = new BattleField(10);
        System.out.println("Запуск игры...");
        battleField.fillAllShipsDecksCoords();
        System.out.println(Arrays.toString(battleField.getAllTypesOfShips()));
        System.out.println("Выберите свой цвет!");
        System.out.println("1 -" + textColours.ANSI_GREEN.getCode() + "зеленый\n" + textColours.ANSI_RESET.getCode() + "2 -" +
                textColours.ANSI_BLUE.getCode() + "голубой\n" + textColours.ANSI_RESET.getCode() + "3 -" + textColours.ANSI_RED.getCode() + "красный\n" + textColours.ANSI_RESET.getCode() +
                "4 -" + textColours.ANSI_CYAN.getCode() + "циан\n" + textColours.ANSI_RESET.getCode() + "5 -" + textColours.ANSI_YELLOW.getCode() + "желтый\n" + textColours.ANSI_RESET.getCode() +
                "6 -" + textColours.ANSI_PURPLE.getCode() + "пурпурный" + textColours.ANSI_RESET.getCode());
        System.out.print("Выш цвет: ");
        int hitColourPlayer = in.nextInt();
        setPlayersHit(chooseColoursForHits(hitColourPlayer, playersHit));

        System.out.println();

        System.out.println("Выберите цвет оппонента!");
        System.out.println("1 -" + textColours.ANSI_GREEN.getCode() + "зеленый\n" + textColours.ANSI_RESET.getCode() + "2 -" +
                textColours.ANSI_BLUE.getCode() + "голубой\n" + textColours.ANSI_RESET.getCode() + "3 -" + textColours.ANSI_RED.getCode() + "красный\n" + textColours.ANSI_RESET.getCode() +
                "4 -" + textColours.ANSI_CYAN.getCode() + "циан\n" + textColours.ANSI_RESET.getCode() + "5 -" + textColours.ANSI_YELLOW.getCode() + "желтый\n" + textColours.ANSI_RESET.getCode() +
                "6 -" + textColours.ANSI_PURPLE.getCode() + "пурпурный" + textColours.ANSI_RESET.getCode());
        System.out.print("Цвет оппонента: ");
        int hitColourOpponent = in.nextInt();
        setOpponentsHit(chooseColoursForHits(hitColourOpponent, opponentsHit));

        System.out.println(textColours.ANSI_CYAN.getCode() + "Морской бой начался!" + textColours.ANSI_RESET.getCode());
        LocalTime time = LocalTime.now().plusSeconds(30);
        while(LocalTime.now().isBefore(time))
        {
//todo: ход игрока
            personsMove(getCoordinatsFromPlayer(), battleField);

//todo: ход противника
            personsMove(opponentsMove(), battleField);
        }

        System.out.println("Время вышло! Игра окончена\n");
        if(Game.usersScore > Game.opponentScore)
        {
            System.out.println("✔Вы выйграли! Поздравляем!✔\n");
        }
        else if (Game.usersScore < Game.opponentScore)
        {
            System.out.println("†Вы проиграли...†\n");
        }
        else
        {
            System.out.println("☆☆☆Ничья!☆☆☆\n");
        }
    }

//todo: !Arrays.asList(usersMoves).contains(userMove) не работает!!!
    public UserMove getCoordinatsFromPlayer()
    {
        UserMove userMove;
        Scanner in = new Scanner(System.in);
        while (true)
        {
            System.out.println("Ваш ход");
            System.out.print("По горизонтали: ");
            int horizontal = in.nextInt();
            System.out.print("По вертикали: ");
            int vertical = in.nextInt();
            userMove = new UserMove(new Deck(horizontal,vertical),true);

            if(!Arrays.asList(usersMoves).contains(userMove))
            {
                usersMoves[counterUsersMoves] = userMove; //записываем ход игрока в массив
                counterUsersMoves++;
                break;
            }
            System.out.println("Данный ход уже был сделан, выберите другой!");
        }

        return userMove;
    }

    public UserMove opponentsMove()
    {
        UserMove opponentsMove;
//todo: имитация ожидания хода противника

        String str = "";
        while (true) // пока противник не сделает ход который еще не делал
        {
            try
            {
                for (int i = 0; i < 3; i++)
                {
                    System.out.print("Ожидание хода противника");
                    for (int j = 0; j < 7 ; j++)
                    {
                        str += ".";
                        System.out.print(str);
                        Thread.sleep(300);
                        str = "";
                    }
                    str = "\rОжидание хода противника";
                }
            }
            catch (InterruptedException e)
            {
                System.out.println(e.getMessage());
            }
//todo: end

            opponentsMove = new UserMove(new Deck((2 + (int) (Math.random() * 9)), (2 + (int) (Math.random() * 9))),false);
            if(!Arrays.asList(usersMoves).contains(opponentsMove))
            {
                usersMoves[counterUsersMoves] = opponentsMove; //записываем ход противника в массив
                counterUsersMoves++;
                break;
            }
            System.out.println("Данный ход уже был сделан, выберите другой!");
        }
        return opponentsMove;
    }

    public void personsMove(UserMove userMove, BattleField battleField)
    {
        boolean isHit = false;
        System.out.println();

        for (int i = 0; i < battleField.getArrayOfFilledCells().length; i++) //сравниваем имеющиеся координаты кораблей с ходом пользователя
        {
            if(userMove.getUserMove().getX() == battleField.getArrayOfFilledCells()[i].getX() && userMove.getUserMove().getY() == battleField.getArrayOfFilledCells()[i].getY())
            {
                isHit = true;
                break;
            }
        }
//todo: прибавляем очки если произошло попадание
        if(isHit)
        {
            System.out.println("Попадание!");
            if(userMove.isUserIsPlayer()) //если попал игрок +игроку
            {
                Game.usersScore = Game.usersScore + 1;

            }
            else //иначе +оппоненту
            {
                Game.opponentScore = Game.opponentScore + 1;
            }
        }
        else
        {
            System.out.println("Промах!");
        }
        battleField.printUpdateBattleFieldAfterShoot(usersMoves,getPlayersHit(),getOpponentsHit());
    }

    public textColours chooseColoursForHits(int num, textColours userColour)
    {
        switch (num)
        {
            case 1:
                userColour = textColours.ANSI_GREEN;
                System.out.println("Вы выбрали " + textColours.ANSI_GREEN.getCode() + "зеленый" + textColours.ANSI_RESET.getCode());
                break;
            case 2:
                userColour = textColours.ANSI_BLUE;
                System.out.println("Вы выбрали " + textColours.ANSI_BLUE.getCode() + "голубой" + textColours.ANSI_RESET.getCode());
                break;
            case 3:
                userColour = textColours.ANSI_RED;
                System.out.println("Вы выбрали " + textColours.ANSI_RED.getCode() + "красный" + textColours.ANSI_RESET.getCode());
                break;
            case 4:
                userColour = textColours.ANSI_CYAN;
                System.out.println("Вы выбрали " + textColours.ANSI_CYAN.getCode() + "циан" + textColours.ANSI_RESET.getCode());
                break;
            case 5:
                userColour = textColours.ANSI_YELLOW;
                System.out.println("Вы выбрали " + textColours.ANSI_YELLOW.getCode() + "желтый" + textColours.ANSI_RESET.getCode());
                break;
            case 6:
                userColour = textColours.ANSI_PURPLE;
                System.out.println("Вы выбрали " + textColours.ANSI_PURPLE.getCode() + "пурпурный" + textColours.ANSI_RESET.getCode());
                break;
            default:
                userColour = textColours.ANSI_RED;
                System.out.println("Не существующий цвет. Будет установлен цвет по молчанию (" + textColours.ANSI_RED.getCode() + "красный" + textColours.ANSI_RESET.getCode() + ")");
        }
        return userColour;
    }
}
