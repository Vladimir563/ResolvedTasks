package iopart2tasks;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ClientPropertiesLoader
{
    private static ClientPropertiesLoader propertiesLoader;
    private Properties properties;
    private String sourceFileName;

    public Properties getProperties() {
        return properties;
    }

    public String getSourceFileName() {
        return sourceFileName;
    }

    private ClientPropertiesLoader(String sourceFileName)
    {
        this.properties = new Properties();
        this.sourceFileName = sourceFileName;
        loadProperties();
    }

    public static ClientPropertiesLoader getInstance(String fileName)
    {
        if(propertiesLoader == null)
        {
            propertiesLoader = new ClientPropertiesLoader(fileName);
        }
        return propertiesLoader;
    }

    private void loadProperties()
    {
        try(InputStream input = ClientPropertiesLoader.class.getClassLoader().getResourceAsStream(getSourceFileName()))
        {
            getProperties().load(input);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
