package navalbattle;

import navalbattle.battlefield.BattleField;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.Scanner;

public class Game
{

    private Deck [] usersMove = new Deck[50]; //массив в который будут записываться все ходы
    private Deck [] opponentsMove = new Deck[50];
    private Ship [] usersShips = {new Ship(usersMove)};
    private Ship [] opponentsShips = {new Ship(opponentsMove)};
    private int counterUserMoves = 0;
    private int counterOpponentsMoves = 0;
    private String shootColor = textColours.ANSI_WHITE.getCode();

    public Deck[] getOpponentsMove() {
        return opponentsMove;
    }

    public void setOpponentsMove(Deck[] opponentsMove) {
        this.opponentsMove = opponentsMove;
    }

    public Deck[] getUsersMove() {
        return usersMove;
    }

    public int getCounterOpponentsMoves() {
        return counterOpponentsMoves;
    }

    public void setCounterOpponentsMoves(int counterOpponentsMoves) {
        this.counterOpponentsMoves = counterOpponentsMoves;
    }

    public void setUsersMove(Deck[] usersMove) {
        this.usersMove = usersMove;
    }

    public int getCounterUserMoves() {
        return counterUserMoves;
    }

    public void setCounterUserMoves(int counterUserMoves) {
        this.counterUserMoves = counterUserMoves;
    }

    public void start()
    {
        BattleField battleField = new BattleField();
        battleField.fieldGenerate();
        battleField.showCurrentShipsOnMap(battleField.getAllShipsArray(),'□',textColours.ANSI_WHITE.getCode());

        LocalTime time = LocalTime.now().plusSeconds(30);
        while(LocalTime.now().isBefore(time))
        {
//todo: ход игрока
            personsMove(getCoordinatsFromPlayer(),battleField,usersMove,usersShips);

//todo: ход противника
            personsMove(opponentsMove(),battleField,opponentsMove,opponentsShips);
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
    }

    public void personsMove(Deck p, BattleField battleField, Deck [] decks, Ship [] ships)
    {
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
                    shootColor = textColours.ANSI_RED.getCode();
                    break;
                }
            }
        }

        if(isHit)
        {
            System.out.println("Попадание!");
        }
        else
        {
            System.out.println("Промах!");
        }

        battleField.showCurrentShipsOnMap(ships,'x',shootColor);
        shootColor = textColours.ANSI_WHITE.getCode();
    }
}
