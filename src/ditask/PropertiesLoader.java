package ditask;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesLoader
{
    private Properties properties;
    private String configFileName;
    private static PropertiesLoader propertiesLoader;

    private PropertiesLoader(String configFileName)
    {
        this.configFileName = configFileName;
        properties = new Properties();
        loadProperties(getProperties(),getConfigFileName());
    }

    public static PropertiesLoader getPropertiesLoader(String fileConfName)
    {
        if(propertiesLoader == null)
        {
            propertiesLoader = new PropertiesLoader(fileConfName);
        }
        return propertiesLoader;
    }

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    public String getConfigFileName() {
        return configFileName;
    }

    public void setConfigFileName(String configFileName) {
        this.configFileName = configFileName;
    }

    private void loadProperties(Properties properties, String configFileName)
    {
        try(InputStream input = PropertiesLoader.class.getClassLoader().getResourceAsStream(configFileName))
        {
            properties.load(input);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
