package ditask.tstclasses;


import ditask.container.marks.Required;
import ditask.container.marks.RequiredClass;
import ditask.tstclasses.config.AnimalsConfig;

@RequiredClass
public class Owner
{
    @Required
    private AnimalsConfig ownerConfig;

    public String getName(){
        return ownerConfig.getOwnerName();
    }
}
