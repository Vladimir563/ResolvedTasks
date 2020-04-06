package alpiniststask;

import java.util.Arrays;

enum mountains
{
    ELBRUS("Эльбрус"),VEZUVII("Везувий"),AFON("Афон");
    private String mountainName;

    mountains(String mountainName)
    {
        this.mountainName = mountainName;
    }

    public String getMountainName()
    {
        return mountainName;
    }
}

enum countries
{
    RUSSIA("Россия"),ITALIA("Италия"), GREECE("Италия");
    private String countryName;

    countries(String countryName) {
        this.countryName = countryName;
    }

    public String getCountryName() {
        return countryName;
    }
}

public class Main
{
    public static void main(String[] args) throws ShortNameException, SmallHeightException
    {
        Alpinist a1 = new Alpinist("Andrey", "Saint-Petersburg, Stroiteley street, 44-12");
        Alpinist a2 = new Alpinist("Victor", "Ryazan, Lesnaya street, 1a-2");
        Alpinist a3 = new Alpinist("Petr", "Kalinigrad, Smolnaya street, 90-932");
        Alpinist a4 = new Alpinist("Semen", "Novgorod, Lenina street, 10-902");


        Mountain m1 = new Mountain(mountains.ELBRUS.getMountainName(), countries.RUSSIA.getCountryName(), 10000);
        Mountain m2 = new Mountain(mountains.AFON.getMountainName(),countries.GREECE.getCountryName(),9020);
        Mountain m3 = new Mountain(mountains.VEZUVII.getMountainName(), countries.ITALIA.getCountryName(),5004);

        Group g1 = new Group(true, new Alpinist[]{a1,a2}, m1);
        g1.addAlpinistInAGroup(a3);

        Group g2 = new Group(false, new Alpinist[]{a3,a1}, m2);
        Group g3 = new Group(false, new Alpinist[]{a2,a3}, m3);


//todo: демонстрация обработки исключений
        try
        {
            Mountain m5 = new Mountain("F","Russia",99);
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }

        try
        {
            Alpinist a5 = new Alpinist("Iv", "Russia");
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
}


/*
//todo:
    Дополнительная задача на классы и объекты
        (в задаче нет ни наследования, ни интерфейсов)
//todo:
    Описать объектную модель Альпинист, Гора, Группа для восхождения на гору.
        Альпинист создаётся с именем (не менее 3 символов) и адресом проживания (не менее 5 символов).
//todo:
        Гора создаётся с названием (не менее 4 символов), страной (не менее 4 символов) и высотой (не менее 100 метров)
//todo:
        Группа для восстановления на гору создаётся со следующими характеристиками:
        идёт набор в группу или нет;
        массив альпинистов;
        гора;
        должна быть возможность добавить альпиниста в группу, если набор ещё идёт

        В методе main создать:
        3 группы для восхождений на 3 различных горы.
        В первой группе 3 альпиниста (набор открыт)
        Во второй и третьей группах по 2 альпиниста (набор в эти группы закрыт)

        При выполнении задания необходимо обращать внимание на модификаторы доступа и выполнять все необходимые проверки.*/
