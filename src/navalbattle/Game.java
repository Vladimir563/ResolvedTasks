package navalbattle;

import navalbattle.battlefield.BattleField;
import navalbattle.battleships.Deck;

import java.time.LocalTime;

public class Game
{
    private Deck[] usersMove = new Deck[50]; //массив в который будут записываться все ходы
    private Deck [] opponentsMove = new Deck[50];
    private int counterUserMoves = 0;
    private int counterOpponentsMoves = 0;
    public static int usersScore = 0;
    public static int opponentScore = 0;

    public void start()
    {
        BattleField battleField = new BattleField(10);
        System.out.println("Морской бой начался!");
//        battleField.fieldGenerate();



        LocalTime time = LocalTime.now().plusSeconds(30);
        while(LocalTime.now().isBefore(time))
        {
//todo: ход игрока
//            personsMove(getCoordinatsFromPlayer(),battleField,usersShips);

//todo: ход противника
//            personsMove(opponentsMove(),battleField,opponentsShips);
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
        System.out.println("Расположение кораблей");
    }


/*    public Deck getCoordinatsFromPlayer()
    {
        Deck deck;
        Scanner in = new Scanner(System.in);
        System.out.println("Ваш ход");
        System.out.print("По горизонтали: ");
        int horizontal = in.nextInt();
        System.out.print("По вертикали: ");
        int vertical = in.nextInt();
        deck = new Deck(horizontal,vertical);

        if(!Arrays.asList(usersMove).contains(deck))
        {
            getUsersMove()[getCounterUserMoves()] = deck; //записываем ход игрока в массив
            setCounterUserMoves(getCounterUserMoves() + 1);
        }

        return deck;
    }

    public Deck opponentsMove()
    {
        Deck deck;
        String str = "";
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

        deck = new Deck((2 + (int) (Math.random() * 9)), (2 + (int) (Math.random() * 9)));
        if(!Arrays.asList(opponentsMove).contains(deck))
        {
            getOpponentsMove()[getCounterOpponentsMoves()] = deck; //записываем ход противника в массив
            setCounterOpponentsMoves(getCounterOpponentsMoves() + 1);
        }
        return deck;
    }*/

    /*public void personsMove(Deck p, BattleField battleField, Ship [] ships)
    {
        String userCode = usersShips.toString();
        String opponentCode = opponentsShips.toString();
        boolean isHit = false;
        Deck d = p;
        System.out.println();
        for (int i = 0; i < battleField.getAllShipsArray().length; i++)
        {
            for(Deck deck1 : battleField.getAllShipsArray()[i].getDecks())
            {
                if(d.getX() == deck1.getX() && d.getY() == deck1.getY())
                {
                    isHit = true;
                    break;
                }
            }
        }

        if(isHit)
        {
            System.out.println("Попадание!");
            if(ships.toString().equals(opponentCode))
            {
                Game.opponentScore = Game.opponentScore + 1;
            }
            if(ships.toString().equals(userCode))
            {
                Game.usersScore = Game.usersScore + 1;
            }
        }
        else
        {
            System.out.println("Промах!");
        }
        battleField.showCurrentShipsOnMap(ships,'x');
    }*/
}
