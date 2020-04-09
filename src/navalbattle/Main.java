package navalbattle;

import navalbattle.battlefield.BattleField;
import navalbattle.battleships.FourDecksShip;
import navalbattle.battleships.OneDeckShip;
import navalbattle.battleships.ThreeDecksShip;
import navalbattle.battleships.TwoDecksShip;

import java.util.Arrays;

public class Main
{
    public static void main(String[] args)
    {
        Game game = new Game();
        game.start();
/*        BattleField battleField = new BattleField();

        Ship [] ships = new Ship[5];
        ships[0] = new ThreeDecksShip(new Deck((2 + (int) (Math.random() * 9)), (2 + (int) (Math.random() * 9))), new Deck(0,0), new Deck(0,0));
        ships[1] = new TwoDecksShip(new Deck((2 + (int) (Math.random() * 9)), (2 + (int) (Math.random() * 9))), new Deck(0,0));
        ships[2] = new TwoDecksShip(new Deck((2 + (int) (Math.random() * 9)), (2 + (int) (Math.random() * 9))), new Deck(0,0));
        ships[3] = new FourDecksShip(new Deck((2 + (int) (Math.random() * 9)), (2 + (int) (Math.random() * 9))), new Deck(0,0), new Deck(0,0),new Deck(0,0));
        ships[4] = new OneDeckShip(new Deck((2 + (int) (Math.random() * 9)), (2 + (int) (Math.random() * 9))));
        System.out.println(Arrays.toString(ships));

        System.out.println();

        for(Ship ship : ships)
        {
            battleField.getShipWithCorrectCoords(ship,10);
        }

        System.out.println(Arrays.toString(ships));*/
    }
}

/*    Написать простую реализацию морского боя.
        Программа генерирует корабли на поле, пользователь угадывает их расположение.
        Пользователь общается с программой через консоль (формат ввода выбирайте сами).
        Можно усложнить задачу игрока (установить количество попыток или ограничение по времени).
        Программа должна сгененировать поле 10x10 и расположить на нем корабли:
        1 корабль с 4мя палубами
        2 корабля с 3мя палубами
        3 корабля с 2мя палубами
        4 корабля с 1й палубой

        Не пишите весь код в одном файле!
        Определитесь, из каких объектов будет состоять игра,
        опишите необходимые классы, разделите их по пакетам.

        В main должен быть только запуск игры,
        например,
        Game game = new Game();
        game.start();*/
