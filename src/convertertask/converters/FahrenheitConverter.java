package convertertask.converters;

import convertertask.ConverterTemplate;

public class FahrenheitConverter extends ConverterTemplate
{
    final private double koef1 = 9/5d;
    final private int koef2 = 32;

    @Override
    public void convertValue()
    {
        setConvertedValue((getBaseVolumeInCelsius() * koef1) + koef2);
    }

    @Override
    public void getConvertValue()
    {
        convertValue();
        System.out.printf("%f градусов цельсия в градусах по фаренгейту = %f\n", getBaseVolumeInCelsius(),getConvertedValue());
    }
}

