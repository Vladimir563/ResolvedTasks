package classandobjects.cattask;

public class Cat
{
    private String name;
    private double weight;
    private int age;
    private String colour;
    private int health;
    private int hitPower;
    private String ownersAdress;

    //constructors
    public Cat(String name) //1
    {
        this.name = name;
    }

    public Cat(String name, double weight, int age) //2
    {
        this.name = name;
        this.weight = weight;
        this.age = age;
    }

    public Cat(double weight, String colour) //3
    {
        this.weight = weight;
        this.colour = colour;
    }

    public Cat(double weight, String colour, CatOwner owner) //4
    {
        this.weight = weight;
        this.colour = colour;
        this.ownersAdress = owner.toString();
    }

    public Cat(String name, double weight, int age, String colour, int health, int hitPower, CatOwner owner) //5
    {
        this.name = name;
        this.weight = weight;
        this.age = age;
        this.colour = colour;
        this.health = health;
        this.hitPower = hitPower;
        this.ownersAdress = owner.toString();
    }

    //getters and setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getHitPower() {
        return hitPower;
    }

    public void setHitPower(int hitPower) {
        this.hitPower = hitPower;
    }

    public String getOwnersAdress() {
        return ownersAdress;
    }

    public void setOwnersAdress(String ownersAdress)
    {
        this.ownersAdress = ownersAdress;
    }

    @Override
    public String toString() {
        return "Cat{" +
                "name='" + name + '\'' +
                ", weight=" + weight +
                ", age=" + age +
                ", colour='" + colour + '\'' +
                ", health=" + health +
                ", hitPower=" + hitPower +
                ", ownersAdress='" + this.ownersAdress + '\'' +
                '}';
    }

    public void Fight(Cat anotherCat)
    {
        if(this.health < anotherCat.hitPower)
        {
            System.out.printf("%s пока не может драться\n",this.name);
            return;
        }
        else
        {
            System.out.printf("Здоровье кота %s уменьшилось -%d%% (%s)\n",this.name,anotherCat.hitPower,anotherCat.name);
            this.health -= anotherCat.hitPower;
            System.out.printf("Текущий уровень здоровья %s (%d%%)\n",this.name,this.health);
        }

    }
}
