package alpiniststask;


public class Alpinist
{
    private String name;
    private String adress;

    public Alpinist(String name, String adress) throws ShortNameException
    {
        setName(name);
        setAdress(adress);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws ShortNameException
    {
        if(name.length() < 3)
        {
            throw new ShortNameException("Слишком короткое имя");
        }
        else
        {
            this.name = name;
        }
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) throws ShortNameException
    {
        if(adress.length() < 5)
        {
            throw new ShortNameException("Слишком короткий адрес проживания");
        }
        else
        {
            this.adress = adress;
        }
    }

    @Override
    public String toString() {
        return "Alpinist{" +
                "name='" + name + '\'' +
                ", adress='" + adress + '\'' +
                '}';
    }
}

//todo: Альпинист создаётся с именем (не менее 3 символов) и адресом проживания (не менее 5 символов).