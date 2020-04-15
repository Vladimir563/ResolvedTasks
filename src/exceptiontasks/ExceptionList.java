package exceptiontasks;

import java.util.ArrayList;

public class ExceptionList
{
    public static void main(String[] args)
    {
        ArrayList<RuntimeException> exceptions = new ArrayList <RuntimeException>();
//todo: IndexOutOfBoundsException
        try
        {
           int [] arr = new int[3];
           arr[6] = 1;
        }
        catch (IndexOutOfBoundsException e)
        {
            exceptions.add(e);
        }
//todo: ArithmeticException
        try
        {
            int a = 2;
            int b = 0;
            int c = 2/b;
        }
        catch (ArithmeticException e)
        {
            exceptions.add(e);
        }
//todo: NullPointerException
        try
        {
            Object o = null;
            o.equals(new Object());
        }
        catch (NullPointerException e)
        {
            exceptions.add(e);
        }
//todo: ClassCastException
        try
        {
            Object obj = "123";
            Integer integer = (Integer) obj;
        }
        catch (ClassCastException e)
        {
            exceptions.add(e);
        }
//todo: IllegalArgumentException
        try
        {
            someMethod(-4);
        }
        catch (IllegalArgumentException e)
        {
            exceptions.add(e);
        }
//todo: UnsupportedOperationException
        try
        {
            ExceptionExample example = new ExceptionExample();
            example.setName("newName");
        }
        catch (UnsupportedOperationException e)
        {
            exceptions.add(e);
        }
//todo: SecurityException
        try
        {
            ExceptionExample example = new ExceptionExample();
            example.getName();
        }
        catch (SecurityException e)
        {
            exceptions.add(e);
        }
//todo: NegativeArraySizeException
        try
        {
            int [] arr = new int[-1];
        }
        catch (NegativeArraySizeException e)
        {
            exceptions.add(e);
        }
//todo: NumberFormatException
        try
        {
            String str = "hello";
            int i = Integer.parseInt(str);
        }
        catch (NumberFormatException e)
        {
            exceptions.add(e);
        }


//todo: вывод в консоль всех ислючений
        for (int i = 0; i < exceptions.size(); i++)
        {
            System.out.println((i+1) + ")" + " " + exceptions.get(i));
        }
    }

    public static void someMethod(int a)
    {
        if(a < 0)
        {
            throw new IllegalArgumentException();
        }
    }
}

class ExceptionExample
{
    private String name = "ExceptionExample";

    public String getName()
    {
        throw new SecurityException();
    }

    public void setName(String name)
    {
        throw new UnsupportedOperationException();
    }
}

//    Создать список исключений и заполнить его 9 различными runtime исключениями. Например,
//
//        try {
//        int[] arr = new int[5];
//        arr[7] = 12;
//        } catch (Exception e) {
//        exceptionsList.add(e);
//        }
