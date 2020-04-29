package ditask.tstclasses;


import ditask.container.marks.Required;
import ditask.container.marks.RequiredClass;
import ditask.tstclasses.config.AnimalsConfig;

@RequiredClass
public class Mouse
{
    @Required
    private AnimalsConfig mouseConfig;

    public String getName() {
        return mouseConfig.getMouseName();
    }

    public int getSpeed() {
        return mouseConfig.getMouseSpeed();
    }
}
