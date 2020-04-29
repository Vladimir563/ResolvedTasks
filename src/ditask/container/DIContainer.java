package ditask.container;


import ditask.PropertiesLoader;
import ditask.container.marks.ConfigClass;
import ditask.container.marks.Required;
import ditask.container.marks.RequiredClass;
import sun.plugin.javascript.navig.Array;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class DIContainer
{
    private Set<Class> classesHashSet;
    private PropertiesLoader propertiesLoader;

    public DIContainer(HashSet <Class> classes)
    {
        this.classesHashSet = classes;
    }

    public HashMap<String,Object> createObjects(Set<Class> classesHashSet) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchFieldException
    {
        HashMap<String,Object> objects = new HashMap<>();
        String sourceFileName;
        for(Class c : classesHashSet) //перебираем все классы из сета (поиск классов с аннотацией ConfigClass т.к они должны создаваться первыми)
        {
            Annotation [] annotations = c.getDeclaredAnnotations();
            if(c.isAnnotationPresent(ConfigClass.class)) //если класс помечен аннотацией ConfigClass
            {
                ConfigClass configClass = (ConfigClass) c.getDeclaredAnnotation(ConfigClass.class); // получим ссылку на аннотацию
                sourceFileName = configClass.file(); // получили название конфиг файла из конфиг класса (из свойства аннотации)
                this.propertiesLoader = propertiesLoader.getPropertiesLoader(sourceFileName); //создали (получили) обьект загрузчика свойств
                Constructor constructor = c.getDeclaredConstructor(); //получили конструктор данного класса
                Object obj = constructor.newInstance(); //создали обьект данного класса
                Field [] fields = c.getDeclaredFields(); // получили все поля данного класса
                for(Field field : fields)
                {
                    field.setAccessible(true); // открываем доступ к полю
//todo: инициализируем поля в соответствии с их типом из конфиг файла;
                    if(field.getType().equals(String.class))
                    {
                        field.set(obj,propertiesLoader.getProperties().getProperty(configClass.prefix() + "." + field.getName()));
                    }
                    else if(field.getType().equals(int.class))
                    {
                        field.set(obj,Integer.parseInt(propertiesLoader.getProperties().getProperty(configClass.prefix() + "." + field.getName())));
                    }
                }
                objects.put(obj.getClass().getSimpleName(),obj);
            }
        }

        for(Class c1 : classesHashSet)
        {
            Annotation [] annotations = c1.getDeclaredAnnotations();
            if(c1.isAnnotationPresent(RequiredClass.class))
            {
                Constructor constructor1 = c1.getDeclaredConstructor();
                Object obj1 = constructor1.newInstance(); //создали обьект данного класса
                Field [] fields = c1.getDeclaredFields(); //получили поля
                for(Field field : fields)
                {
                    if(field.isAnnotationPresent(Required.class))
                    {
//todo: инициализируем поля в соответствии с их типом
                        String fieldName = field.getName();
                        Method method;
                        String methodName = "set" + fieldName.toUpperCase().charAt(0) + fieldName.substring(1, fieldName.length()); //формируем имя сеттера
                        if(field.getType().equals(int.class)) //если тип поля int
                        {
                            method = c1.getDeclaredMethod(methodName, int.class); //пытаемся получить данный сеттер из класса
                            method.setAccessible(true); //открываем доступ к полю
//todo: вызываем сеттер определенного поля (созданного обьекта) и передаем в параметр значение
                            method.invoke(obj1,666);
                        }
                        else if (field.getType().equals(String.class)) //аналогично для String
                        {
                            method = c1.getDeclaredMethod(methodName, String.class);
                            method.setAccessible(true);
                            method.invoke(obj1,"ThisValueWasSetWithReflection");
                        }
                        else if(!field.getType().equals(Array.class))
                        {
                            //todo: если поле оказалось не примитивом массивом или строкой (пользовательским классом) то
                            Class animal = field.getType(); //получаем ссылку на класс данного поля
                            Constructor constructor = animal.getDeclaredConstructor();
                            Object animalObject = constructor.newInstance();
                            field.setAccessible(true);
                            Field [] fields1 = field.getType().getDeclaredFields(); //получил все поля
                            for (Field field1 : fields1)
                            {
                                if(field1.getType().equals(String.class))
                                {
                                    field1.setAccessible(true);
                                    String objName = field1.getName().substring(0,field1.getName().length()-4);
                                    field1.set(animalObject, objName);
                                }
                                else if(field1.getType().equals(int.class))
                                {
                                    field1.setAccessible(true);
                                    field1.set(animalObject,((int) Math.random() * 30));
                                }
                            }
                            field.set(obj1,animalObject);
                        }
                    }
                }
                objects.put(obj1.getClass().getSimpleName(),obj1);
            }
        }
        return objects;
    }
}
