package ditask;

import ditask.container.DIContainer;
import ditask.tstclasses.*;
import ditask.tstclasses.config.AnimalsConfig;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class Main
{
    public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        HashSet <Class> classes = new HashSet<>(new ArrayList<>(Arrays.asList(Cat.class,Mouse.class,Owner.class, AnimalsConfig.class)));
        DIContainer container = new DIContainer(classes);
//todo: для теста
        HashMap<String,Object> objects = container.createObjects(classes);
        Cat cat = (Cat) objects.get("Cat");
        Mouse mouse = (Mouse) objects.get("Mouse");
        cat.catchMouse(mouse);
    }
}

