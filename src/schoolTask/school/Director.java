package schoolTask.school;

public class Director extends Human
{
    private static Director director;
    public static String Name;
    public static int Age;

    private Director(String name, int age)
    {
        super(name, age);
    }

    public static Director getInstance(String name, int age )
    {
        if(director == null)
        {
            director = new Director(name, age);
            Name = name;
            Age = age;
        }
        return director;
    }

    public void declareStartStydingOfDay()
    {
        System.out.println("Занятия начались!");
    }

    public void declareEndStydingOfDay()
    {
        System.out.println("Занятия закончились!");
    }

    public void directorInfo()
    {
        System.out.printf("Статус: директор\nИмя: %s\nВозраст: %d\n", Director.Name,Director.Age);
    }

}
