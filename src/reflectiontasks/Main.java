package reflectiontasks;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

public class Main
{
    public static void main(String[] args) throws IllegalAccessException, NoSuchMethodException, InstantiationException, InvocationTargetException {
/*        toString(new Message());
        System.out.println();
        toString(new User("Ivan", new Message()));*/
        Message obj = (Message) createObjAndCallToString(new Message());
        System.out.println(obj.getTitle());

        try
        {
            User obj2 = (User) createObjAndCallToString(new User("Vasya", new Message()));
            System.out.println(obj2.getName());
        }
        catch (NullPointerException e)
        {
            System.out.println(e.fillInStackTrace());
        }


    }

    static void toString(Object o) throws IllegalAccessException {
        Class exClass = o.getClass(); //получили ссылку на экземпляр обьекта
        Field [] fields = exClass.getDeclaredFields(); //получили все методы класса у которого вызываем этот метод
        for(Field field : fields)
        {
            field.setAccessible(true); //делаем это поле доступным
            if(field.getType().getTypeName().equals("java.lang.String") || field.getType().isPrimitive() || field.getType().isArray())
            {
                System.out.println();
                System.out.println(field.getName() + " - " + field.get(o));
            }
            else
            {
                toString(field.get(o));
            }
        }
    }

    static Object createObjAndCallToString(Object o) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class someClass = o.getClass();
        if(someClass.isAnnotationPresent(ConfigClass.class))
        {
            Constructor constructorSomeClass = someClass.getDeclaredConstructor();
            o = constructorSomeClass.newInstance();
            Field [] fields = someClass.getDeclaredFields();
            for(Field field : fields)
            {
                if(field.isAnnotationPresent(Required.class))
                {
                    String nameMethod = field.getName();
                    Method method = someClass.getDeclaredMethod(("set" + nameMethod.toUpperCase().charAt(0) + nameMethod.substring(1,nameMethod.length())), String.class);
                    method.setAccessible(true);
                    method.invoke(o, "This field was changed by Reflection!!!");
                }
            }
            return o;
        }
        return null;
    }
}

//todo:
//    Tasks:
//    1. написать рефлективный статический static toString(Object o)
//    вывести информацию по полям обьекта
//    название поля : значение поля
//    проблема может случиться если поле обьекта не примитивный тип (вызвать у этого поля опять тот же метод (рекурсия)

//2. если класс аннотирован аннотацией ConfigClass, создать обьект данного класса
//// если в классе есть поле отмеченное аннотацией @Required - установить значение этого поля
//
////значение поля устанавливать через сеттер, исходя из того что
////stringData() / setStringData()

//field.getName()
//field.getField()
//field.getType()
//у созданного обьекта вызвать метод public String toString(), используя рефлексию


//выписать интересные методы для
//Class
//Field
//Method
//Modifier