package ditask.tstclasses;

import ditask.container.marks.Required;
import ditask.container.marks.RequiredClass;
import ditask.tstclasses.config.AnimalsConfig;

import java.util.ArrayList;


@RequiredClass
public class Cat
{
    @Required
    private AnimalsConfig catConfig;

    @Required
    private Owner owner;

    private ArrayList<Mouse> mice = new ArrayList<>(20);

    public void catchMouse(Mouse mouse) {
        if (catConfig.getCatSpeed() < mouse.getSpeed()) {
            System.out.println(mouse.getName() + " убежал от " + catConfig.getCatName());
            return;
        }
        mice.add(mouse);
        System.out.println(catConfig.getCatName() + " поймал " + mouse.getName());
    }
}
