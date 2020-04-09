package navalbattle;

import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;
import navalbattle.battlefield.BattleField;

import java.io.Console;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Scanner;

public class Game
{

    private Deck [] usersMove = new Deck[100]; //массив в который будут записываться все ходы
    private int counterMoves = 0;
    public Deck[] getUsersMove() {
        return usersMove;
    }

    public void setUsersMove(Deck[] usersMove) {
        this.usersMove = usersMove;
    }

    public int getCounterMoves() {
        return counterMoves;
    }

    public void setCounterMoves(int counterMoves) {
        this.counterMoves = counterMoves;
    }

    public void start()
    {
        BattleField battleField = new BattleField();
        battleField.fieldGenerate();
        LocalTime time = LocalTime.now().plusSeconds(30);
        while(LocalTime.now().isBefore(time))
        {
            getCoordinatsFromPlayer();
            opponentsMove();
            System.out.println();
            System.out.println(Arrays.toString(usersMove));
        }
        System.out.println("Время вышло! Игра окончена");
    }


    public Deck getCoordinatsFromPlayer()
    {
        Deck deck;
        Scanner in = new Scanner(System.in);
        System.out.println("Ваш ход");
        System.out.print("По горизонтали: ");
        int horizontal = in.nextInt();
        System.out.print("По вертикали: ");
        int vertical = in.nextInt();
        deck = new Deck(horizontal,vertical);

        getUsersMove()[getCounterMoves()] = deck; //записываем ход игрока в массив
        setCounterMoves(getCounterMoves() + 1);

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
                    Thread.sleep(400);
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
        getUsersMove()[getCounterMoves()] = deck; //записываем ход компьютера в массив
        setCounterMoves(getCounterMoves() + 1);
        return deck;
    }
}
