package alpiniststask;

public class Mountain
{
    private String mountainName;
    private String country;
    private int height;

    public Mountain(String mountainName, String country, int height) throws ShortNameException, SmallHeightException
    {
        setMountainName(mountainName);
        setCountry(country);
        setHeight(height);
    }

    public String getMountainName() {
        return mountainName;
    }

    public void setMountainName(String mountainName) throws ShortNameException
    {
        if(mountainName.length() < 4)
        {
            throw new ShortNameException("Слишком короткое название горы");
        }
        else
        {
            this.mountainName = mountainName;
        }
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) throws ShortNameException
    {
        if(country.length() < 4)
        {
            throw new ShortNameException("Слишком короткое название страны");
        }
        else
        {
            this.country = country;
        }
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) throws SmallHeightException
    {
        if(height < 100)
        {
            throw new SmallHeightException("Слишком маленькая высота горы");
        }
        else
        {
            this.height = height;
        }
    }
}

//todo: Гора создаётся с названием (не менее 4 символов), страной (не менее 4 символов) и высотой (не менее 100 метров)
