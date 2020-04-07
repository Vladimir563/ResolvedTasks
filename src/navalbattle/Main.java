package navalbattle;

import com.sun.org.glassfish.gmbal.GmbalException;
import navalbattle.battlefield.BattleField;
import navalbattle.battleships.OneDeckShip;
import navalbattle.shipsDescriptions;

import java.util.Date;
import java.util.Random;

public class Main
{
    public static void main(String[] args)
    {
        Game game = new Game();
        game.start();
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
