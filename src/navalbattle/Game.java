package navalbattle;

import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;
import navalbattle.battlefield.BattleField;
import navalbattle.battlefield.UserMove;
import navalbattle.battleships.Cell;
import navalbattle.nbexceptions.WrongLevelGameException;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Game
{
    private UserMove[] usersMoves = new UserMove[100]; //массив в который будут записываться все ходы
    private int counterUsersMoves = 0;
    public static int usersScore = 0;
    public static int opponentScore = 0;
    public static textColours playersHit;
    public static textColours opponentsHit;
    private int userLvl;
    private int timeForEndOfGame = 0;

    public int getTimeForEndOfGame() {
        return timeForEndOfGame;
    }

    public void setTimeForEndOfGame(int timeForEndOfGame) {
        this.timeForEndOfGame = timeForEndOfGame;
    }

    public int getUserLvl() {
        return userLvl;
    }

    public void setUserLvl(int userLvl)
    {
        this.userLvl = userLvl;
    }

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
//todo: инициализация системных обьектов
        Scanner in = new Scanner(System.in);
        Logger logger = new Logger();
        BattleField battleField = new BattleField(10);

//todo: начало игры
        System.out.println("Запуск игры...");
        battleField.fillAllShipsDecksCords(); //заполняет карту кораблями

/*//FIXME: будет удален (выводит координаты всех кораблей)
        System.out.println(Arrays.toString(battleField.getAllTypesOfShips()));
//FIXME: end*/

//todo: преднастройка игры, выбор цветов для игроков и уровня сложности
//todo: обработка установки уровня сложности
        System.out.print("Выберите уровень сложности (1 - легкий, 2 - средний, 3 - трудный): ");
        try
        {
            int lvl = in.nextInt();
            setUserLvl(lvl);
        }
        catch (InputMismatchException e)
        {
            System.out.println(textColours.ANSI_RED.getCode() + "Неверный формат ввода" + textColours.ANSI_RESET.getCode());
        }
//todo: end

        setTimeForEndOfGame(chooseYourGameLevel(getUserLvl(), getTimeForEndOfGame())); //установка времени длительности игры

        System.out.println("Выберите свой цвет и цвет оппонента!");
        printChooseColoursInfo(); //вывод информации для выбора цвета игрока и оппонента

        Scanner in1 = new Scanner(System.in);
        System.out.print("Выш цвет: ");
//todo: обработка ввода цвета игрока
        try
        {
            int hitColourPlayer = in1.nextInt();
            playersHit = chooseColoursForHits(hitColourPlayer, playersHit);
        }
        catch (InputMismatchException e)
        {
            System.out.println(textColours.ANSI_RED.getCode() + "Неверный формат ввода" + textColours.ANSI_RESET.getCode());
            playersHit = chooseColoursForHits(9, playersHit);
        }
//todo: end

        Scanner in2 = new Scanner(System.in);
        System.out.print("Цвет оппонента: ");
//todo: обработка ввода цвета оппонента
        try
        {
            int hitColourOpponent = in2.nextInt();
            opponentsHit = chooseColoursForHits(hitColourOpponent, opponentsHit);
        }
        catch (InputMismatchException e)
        {
            System.out.println(textColours.ANSI_RED.getCode() + "Неверный формат ввода" + textColours.ANSI_RESET.getCode());
            opponentsHit = chooseColoursForHits(9, opponentsHit);
        }
//todo: end
        System.out.println();
        System.out.println(textColours.ANSI_CYAN.getCode() + "Морской бой начался!" + textColours.ANSI_RESET.getCode());

        LocalTime timeEndOfGame = LocalTime.now().plusSeconds(getTimeForEndOfGame()); //задание времени игры (можно тоже будет сделать опцией)

        while(LocalTime.now().isBefore(timeEndOfGame)) //пока не закончилось время продолжать игру
        {
//todo: ход игрока
            personsMove(getCoordinatesFromPlayer(), battleField, logger);

//todo: ход противника
            personsMove(getCoordinatesFromOpponent(), battleField, logger);
        }

        printEndOfGame();
    }

    public UserMove getCoordinatesFromPlayer() //получение координат выстрела от игрока
    {
        UserMove userMove;
        Scanner in = new Scanner(System.in);
        while (true)
        {
            int [] userCoordinates;

            userCoordinates = getUserCoordinate();

            if(userCoordinates != null)
            {
                userMove = new UserMove(new Cell(userCoordinates[0],userCoordinates[1]),true);

                if(!isThisMoveWasAlready(usersMoves,userMove)) //определяем был ли уже сделан такой ход
                {
                    usersMoves[counterUsersMoves] = userMove; //записываем ход игрока в массив
                    counterUsersMoves++;
                    break;
                }
                System.out.println("Данный ход уже был сделан, выберите другой!");
            }
        }

        return userMove;
    }

    public UserMove getCoordinatesFromOpponent() //получение координат выстрела от оппонента
    {
        UserMove opponentsMove;
//todo: имитация ожидания хода противника
        String str = "";
        while (true) // пока противник не сделает ход который еще не делал
        {
            try
            {
                for (int i = 0; i < 2; i++)
                {
                    System.out.print("Ожидание хода противника");
                    for (int j = 0; j < 3 ; j++)
                    {
                        str += ".";
                        System.out.print(str);
                        Thread.sleep(500);
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
            opponentsMove = new UserMove(new Cell((2 + (int) (Math.random() * 9)), (2 + (int) (Math.random() * 9))),false);
            if(!isThisMoveWasAlready(usersMoves,opponentsMove))
            {
                usersMoves[counterUsersMoves] = opponentsMove; //записываем ход противника в массив
                counterUsersMoves++;
                break;
            }
        }
        return opponentsMove;
    }

    public void personsMove(UserMove userMove, BattleField battleField, Logger logger) //ход пользователя
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
        battleField.printUpdateBattleFieldAfterShoot(usersMoves,getPlayersHit(),getOpponentsHit(),logger);
    }

    public textColours chooseColoursForHits(int num, textColours userColour) //выбор цвета пользователя
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

    public boolean isThisMoveWasAlready(UserMove [] usersMoves, UserMove userMove) //возвращает булеву переменную показывающую есть ли уже такой ход в массиве ходов
    {
        for(UserMove userMoveInArr : usersMoves)
        {
            if(userMoveInArr == null) return false;
            if( userMoveInArr.getUserMove().getX() == userMove.getUserMove().getX() && userMoveInArr.getUserMove().getY() == userMove.getUserMove().getY())
            {
                return true;
            }
        }
        return false;
    }

    public void printChooseColoursInfo() //вывод на консоль информации о выборе цветов игроков
    {
        System.out.println("1 -" + textColours.ANSI_GREEN.getCode() + "зеленый\n" + textColours.ANSI_RESET.getCode() + "2 -" +
                textColours.ANSI_BLUE.getCode() + "голубой\n" + textColours.ANSI_RESET.getCode() + "3 -" + textColours.ANSI_RED.getCode() + "красный\n" + textColours.ANSI_RESET.getCode() +
                "4 -" + textColours.ANSI_CYAN.getCode() + "циан\n" + textColours.ANSI_RESET.getCode() + "5 -" + textColours.ANSI_YELLOW.getCode() + "желтый\n" + textColours.ANSI_RESET.getCode() +
                "6 -" + textColours.ANSI_PURPLE.getCode() + "пурпурный" + textColours.ANSI_RESET.getCode());
    }

    public void printEndOfGame() //вывод на консоль информации об окончании игры
    {
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

    public int chooseYourGameLevel(int lvl, int timeForEnd)
    {
        switch (lvl)
        {
            case 1:
                System.out.println("Вы выбрали легкий уровень");
                return 360;
            case 2:
                System.out.println("Вы выбрали средний уровень");
                return 120;
            case 3:
                System.out.println("Вы выбрали трудный уровень");
                return 60;
            default:
                System.out.println("Выбранного уровня сложности не существует. Будет установлен режим по умолчанию (легкий)");
                return 360;
        }
    }

    public int [] getUserCoordinate()
    {
        Scanner in1 = new Scanner(System.in);
        Scanner in2 = new Scanner(System.in);
        int [] usersShoot = new int[2];
        int horizontal;
        int vertical;

        System.out.print("Ваш ход (пример а1): ");
        try
        {
           String str = in1.nextLine();
           if(str.length() > 3)
           {
               System.out.println(textColours.ANSI_RED.getCode() + "Неверный формат ввода (причина: вводимая строка не может быть больше 3 символов)" + textColours.ANSI_RESET.getCode());
               return null;
           }

           if((int)str.toCharArray()[0] < 1072 || (int)str.toCharArray()[0] > 1082)
           {
               System.out.println(textColours.ANSI_RED.getCode() + "Неверный формат ввода (буквенные символы могут быть только от \"а\" до \"к\")" + textColours.ANSI_RESET.getCode());
               return null;
           }

           if(str.toCharArray()[0] == 'к')
           {
               horizontal = ((int) str.toCharArray()[0]) - 1072;
           }
           else
           {
               horizontal = ((int) str.toCharArray()[0]) - 1071;
           }

           String [] newStrArr = str.split("");

           try
           {
               if(newStrArr.length > 2)
               {
                   vertical = Integer.parseInt(newStrArr[1] + newStrArr[2]);
               }
               else
               {
                   vertical = Integer.parseInt(newStrArr[1]);
               }
           }
           catch (InputMismatchException e)
           {
               return null;
           }

           if (vertical == 0 || vertical > 10 || vertical < 0)
           {
               System.out.println(textColours.ANSI_RED.getCode() + "Неверный формат ввода (по вертикали может быть только число из диапазона (1:10))" + textColours.ANSI_RESET.getCode());
               return null;
           }

           usersShoot[0] = horizontal;
           usersShoot[1] = vertical;
        }
        catch (NumberFormatException e)
        {
            System.out.println(textColours.ANSI_RED.getCode() + "Неверный формат ввода (строка имела неверный формат)" + textColours.ANSI_RESET.getCode());
            return null;
        }

        return usersShoot;
    }
}
