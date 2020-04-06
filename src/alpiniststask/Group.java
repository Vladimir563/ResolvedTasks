package alpiniststask;

import java.util.Arrays;

public class Group
{
    private boolean isDialingInAGroup;
    private Alpinist [] alpinists;
    private Mountain mountain;

    public Group(boolean isDialingInAGroup, Alpinist[] alpinists, Mountain mountain)
    {
        this.isDialingInAGroup = isDialingInAGroup;
        setAlpinists(alpinists);
        this.mountain = mountain;
    }

    public boolean isDialingInAGroup() {
        return isDialingInAGroup;
    }

    public void setDialingInAGroup(boolean dialingInAGroup) {
        isDialingInAGroup = dialingInAGroup;
    }

    public Alpinist[] getAlpinists() {
        return alpinists;
    }

    public void setAlpinists(Alpinist[] alpinists)
    {
        if(!isDialingInAGroup)
        {
            System.out.println("Набор в группу не ведется. Группа не может быть создана\n");
        }
        else
        {
            this.alpinists = alpinists;
        }
    }

    public Mountain getMountain() {
        return mountain;
    }

    public void setMountain(Mountain mountain) {
        this.mountain = mountain;
    }

    public void addAlpinistInAGroup(Alpinist alpinist)
    {
        if(isDialingInAGroup && !Arrays.asList(alpinists).contains(alpinist) && alpinists.length <= 3)
        {
            Alpinist [] newAlpinists = Arrays.copyOfRange(alpinists,0,alpinists.length + 1);
            newAlpinists[newAlpinists.length - 1] = alpinist;
            setAlpinists(newAlpinists);
            return;
        }

        if(!isDialingInAGroup)
        {
            System.out.println("В данный момент набор в группу не ведется\n");
            return;
        }

        if(alpinists.length > 3)
        {
            System.out.println("Участники восхождения уже набраны");
            return;
        }

        if(Arrays.asList(alpinists).contains(alpinist))
        {
            System.out.printf("В группе уже есть %s\n", alpinist.getName());
            return;
        }
    }
}

/*
    Группа для восстановления на гору создаётся со следующими характеристиками:
        идёт набор в группу или нет;
        массив альпинистов;
        гора;
        должна быть возможность добавить альпиниста в группу, если набор ещё идёт*/
