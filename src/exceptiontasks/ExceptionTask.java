package exceptiontasks;

import exceptiontasks.myexceptions.AccessDeniedException;
import exceptiontasks.myexceptions.FileNotFoundException;
import exceptiontasks.myexceptions.JarException;

import java.util.function.DoubleToIntFunction;

enum Status
{
    FILE_NOT_FOUND,JAR_ERROR,ACCESS_DENIED
}

public class ExceptionTask
{
    public static void main(String[] args) throws AccessDeniedException
    {

        try
        {
            for (int i = 0; i < 3; i++)
            {
                try
                {
                    throwException(Status.values()[i]);
                }
                catch (FileNotFoundException e)
                {
                    System.out.println(e.getMessage() + "[исключение обработано]");
                }
                catch (JarException e)
                {
                    e.printStackTrace();
                }
                catch (AccessDeniedException e)
                {
                    System.out.println(e.getMessage());
                    throw new AccessDeniedException();
                }
            }
        }
        catch (AccessDeniedException e)
        {
            System.out.println(e.getMessage() + "[поймали повторное исключение - " + e.getClass().getSimpleName() + "]");
        }
    }

    public static void throwException(Status status) throws JarException, FileNotFoundException, AccessDeniedException
    {
        switch (status)
        {
            case JAR_ERROR:
                throw new JarException("Исключение типа JarException");
            case ACCESS_DENIED:
                throw new AccessDeniedException();
            case FILE_NOT_FOUND:
                throw new FileNotFoundException();
        }
    }
}

//todo:
//    Метод static void throwException(Status status) throws JarException, FileNotFoundException, AccessDeniedException принимает на вход enum и выбрасывает следующие исключения в зависимости от значения status:
//            если status FILE_NOT_FOUND, выбрасывает FileNotFoundException
//            если status ACCESS_DENIED, выбрасывает AccessDeniedException
//            если status JAR_ERROR, выбрасывает JarException.
//            При вызове метода throwException обработать исключения следующим образом:
//            FileNotFoundException - выводить в консоль сообщение исключения(метод getMessage()) + любое дополнительное сообщение,
//            AccessDeniedException - выводить в консоль сообщение исключения (метод getMessage()) и снова выбрасывать exception,
//            JarException - выводить в консоль стек трейс.
//todo:
//    enum Status с необходимыми константами нужно создать.
//
//        Создать список исключений и заполнить его 9 различными runtime исключениями. Например,
//
//        try {
//        int[] arr = new int[5];
//        arr[7] = 12;
//        } catch (Exception e) {
//        exceptionsList.add(e);
//        }
