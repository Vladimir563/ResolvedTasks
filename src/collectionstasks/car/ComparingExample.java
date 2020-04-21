package collectionstasks.car;

import java.util.*;


enum Color
{
    BLACK("#000000"),RED("#FF0000"),YELLOW("#FFFF00"),GREEN("#008000");
    private String code;
    Color(String c)
    {
        code = c;
    }
    public String getCode()
    {
        return code;
    }
}

public class ComparingExample
{
    public static void main(String[] args)
    {
        // Дополнительное домашнее задание
        Car blackOpel = new Car(Color.BLACK, "opel", 2000);
        Car redOpel = new Car(Color.RED, "opel", 2500);
        Car yellowMazda = new Car(Color.YELLOW, "mazda", 3000);
        Car greenMazda = new Car(Color.GREEN, "mazda", 3000);

        // Объекты добавить в ArrayList cars
        ArrayList <Car> cars = new ArrayList<>(Arrays.asList(blackOpel,redOpel,yellowMazda,greenMazda));
        // отсортировать объекты в cars сначала по brand, price, color
        Comparator<Car> carsBrandComparator = new CarsBrandComparator();
        Comparator<Car> carsPriceComparator = new CarsPriceComparator();
        Comparator<Car> carsColorComparator = new CarsColorComparator();

        // cars.sort(компаратор);
        System.out.println("Сортировка по \"brand\"");
        cars.sort(carsBrandComparator);
        System.out.println(cars);
        System.out.println("\nСортировка по \"price\"");
        cars.sort(carsPriceComparator);
        System.out.println(cars);
        System.out.println("\nСортировка по \"color\"");
        cars.sort(carsColorComparator);
        System.out.println(cars);

//todo: сортировка по всем свойствам сразу
        Comparator<Car> carsBrandPriceColorComparator = new CarsBrandComparator()
                .thenComparing(new CarsPriceComparator()) .thenComparing(new CarsColorComparator());
        TreeSet<Car> carTreeSet = new TreeSet<>(carsBrandPriceColorComparator);
        carTreeSet.addAll(cars);
        System.out.println("\nСортировка по всем свойствам (brand, price, color)");
        System.out.println(carTreeSet);

    }
}

class CarsBrandComparator implements Comparator<Car>
{
    @Override
    public int compare(Car o1, Car o2)
    {
        return o1.getBrand().compareTo(o2.getBrand());
    }
}

class CarsPriceComparator implements Comparator<Car>
{
    @Override
    public int compare(Car o1, Car o2)
    {
        return Integer.compare(o1.getPrice(),o2.getPrice());
    }
}

class CarsColorComparator implements Comparator<Car>
{
    @Override
    public int compare(Car o1, Car o2)
    {
        return o1.getColor().compareTo(o2.getColor());
    }
}
