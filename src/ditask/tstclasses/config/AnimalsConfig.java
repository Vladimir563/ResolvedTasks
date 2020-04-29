package ditask.tstclasses.config;
import ditask.container.marks.ConfigClass;

@ConfigClass(file = "animals.properties", prefix = "animals")
public class AnimalsConfig
{
    private String catName;
    private int catSpeed;

    private String mouseName;
    private int mouseSpeed;

    private String ownerName;

    public String getCatName() {
        return catName;
    }

    public int getCatSpeed() {
        return catSpeed;
    }

    public String getMouseName() {
        return mouseName;
    }

    public int getMouseSpeed() {
        return mouseSpeed;
    }

    public String getOwnerName() { return ownerName; }
}
