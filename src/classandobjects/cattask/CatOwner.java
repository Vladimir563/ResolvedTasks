package classandobjects.cattask;

public class CatOwner
{
    private String name;
    private String surname;
    private String adress;


    public CatOwner(String name, String surname, String adress)
    {
        this.name = name;
        this.surname = surname;
        this.adress = adress;
    }

    public CatOwner()
    {
    }

    @Override
    public String toString() {
        return "CatOwner{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", adress='" + adress + '\'' +
                '}';
    }
}
