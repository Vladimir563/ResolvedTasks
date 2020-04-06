package convertertask;

import convertertask.converters.ConverterToKelvin;
import convertertask.converters.FahrenheitConverter;

public abstract class ConverterTemplate implements IConvertable
{
    private double baseVolumeInCelsius;
    private double convertedValue;

    public double getBaseVolumeInCelsius() {
        return baseVolumeInCelsius;
    }

    public void setBaseVolumeInCelsius(double baseVolumeInCelsius) {
        this.baseVolumeInCelsius = baseVolumeInCelsius;
    }

    public void setConvertedValue(double convertedValue) {
        this.convertedValue = convertedValue;
    }

    public double getConvertedValue()
    {
        return convertedValue;
    }

    public static ConverterTemplate returnConverter(String type)
    {
        ConverterTemplate converter = null;
        switch (type)
        {
            case "FR":
                converter = new FahrenheitConverter();
                break;
            case "KL":
                converter = new ConverterToKelvin();
                break;
            default:
                System.out.println("Данный тип конвертера не поддерживается\n");
        }
        return converter;
    }
}

