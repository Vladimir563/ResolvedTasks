package iotasks;

import org.omg.CORBA.portable.InputStream;

import java.io.*;
import java.util.Arrays;
import java.util.Vector;

public class FilesHandler
{
    public static File createFileWithRandomData(String path) throws IOException
    {
        String [] strArr = new String[]{"hi","hello","how are you", "I'm","fine","thank you","bye","\n","!!!","?"};
        File file = new File(path);
        String newStr = "";
        try(FileOutputStream outputStream = new FileOutputStream(file,false))
        {
            for (int i = 0; i < ((50 + (int) (Math.random() * 100))); i++)
            {
                newStr += strArr[((int) (Math.random() * strArr.length))] + " ";
            }
            System.out.println("Файл создан!");
            System.out.printf("Количество символов исходного текста: %s",newStr.length());
            outputStream.write(newStr.getBytes());
        }
        return file;
    }

    public static File[] divideFile(File file) throws IOException
    {
        String fileData = null;
        try(FileInputStream inputStream = new FileInputStream(file); //читаем данные из файла и записываем в строку
        ByteArrayOutputStream byteArrayOut = new ByteArrayOutputStream())
        {
            byte [] bytes = new byte[512];
            int data;
            while((data = inputStream.read(bytes)) > 0)
            {
                byteArrayOut.write(bytes,0,data);
            }
            fileData = byteArrayOut.toString();
        }

//todo: создали 2 файла в которые будут разделены и записаны данные из основного файла
        File file1 = new File(file.getPath() + "1");
        File file2 = new File(file.getPath() + "2");
        String strToFile1 = fileData.substring(0,fileData.length()/2);
        String strToFile2 = fileData.substring(fileData.length()/2);

        try(FileOutputStream outputStream = new FileOutputStream(file1,false);
        FileOutputStream outputStream1 = new FileOutputStream(file2,false))
        {
            outputStream.write(strToFile1.getBytes());
            outputStream1.write(strToFile2.getBytes());
        }
        System.out.printf("\n\nФайл %s разделен!", file.getName());
        return new File[]{file1,file2};
    }

    public static File combineSeveralFiles(File ... files) throws IOException
    {
        File combineFile = new File(files[0].getPath().substring(0,files[0].getPath().length()-1) + "Combine");
        byte [] bytes;
        for (int i = 0; i < files.length; i++)
        {
            try(FileInputStream fileInputStream = new FileInputStream(files[i]);
            ByteArrayOutputStream byteArrayOut = new ByteArrayOutputStream())
            {
                byte [] bytes1 = new byte[512];
                int data;
                while((data = fileInputStream.read(bytes1)) > 0)
                {
                    byteArrayOut.write(bytes1,0,data);
                }
                bytes = byteArrayOut.toByteArray();
            }

            try(FileOutputStream fileOutputStream = new FileOutputStream(combineFile,true);
            BufferedOutputStream bufferedOut = new BufferedOutputStream(fileOutputStream))
            {
                bufferedOut.write(bytes);
            }
        }

        return combineFile;
    }

    public static void createFileWithEncodeString(File file) throws IOException
    {
        File file1 = new File(file.getPath() + "Encode");
        byte [] bytes1;
        try(FileInputStream inputStream = new FileInputStream(file);
        ByteArrayOutputStream byteArrayOut = new ByteArrayOutputStream())
        {
            byte [] bytes = new byte[512];
            int data;
            while((data = inputStream.read(bytes)) > 0)
            {
                byteArrayOut.write(bytes,0,data);
            }
            bytes1 = byteArrayOut.toByteArray();
        }

        try(FileOutputStream fileOutputStream = new FileOutputStream(file1);
            MyOutputStream myOutput = new MyOutputStream(fileOutputStream))
        {
            myOutput.write(bytes1);
        }
    }

    public static void createFileWithDecodeString(File file) throws IOException
    {
        File file1 = new File(file.getPath().substring(0,file.getPath().length()-6) + "Decode");
        byte [] bytes1;
        try(FileInputStream inputStream = new FileInputStream(file);
        MyInputStream myInputStream = new MyInputStream(inputStream);
        ByteArrayOutputStream byteArrayOut = new ByteArrayOutputStream())
        {
            byte [] bytes = new byte[512];
            int data;
            while((data = myInputStream.read(bytes)) > 0)
            {
                byteArrayOut.write(bytes,0,data);
            }
            bytes1 = byteArrayOut.toByteArray();
        }

        try(FileOutputStream fileOutputStream = new FileOutputStream(file1);
        BufferedOutputStream buf = new BufferedOutputStream(fileOutputStream))
        {
            buf.write(bytes1);
        }
    }
}
