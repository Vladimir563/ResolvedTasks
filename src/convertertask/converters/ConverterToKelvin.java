package convertertask.converters;

import convertertask.ConverterTemplate;

public class ConverterToKelvin extends ConverterTemplate
{
    final private double k = 273.15;

    public double getK() {
        return k;
    }

    @Override
    public void convertValue()
    {
        setConvertedValue(getBaseVolumeInCelsius() + getK());
    }

    @Override
    public void getConvertValue()
    {
        convertValue();
        System.out.printf("%f градусов цельсия в градусах по кельвину = %f\n", getBaseVolumeInCelsius(), getConvertedValue());
    }
}
