package iotasks;

import java.io.*;


public class Main
{
    public static void main(String[] args)
    {
        try
        {
            File file = FilesHandler.createFileWithRandomData("sources/file");
            File [] dividedFiles = FilesHandler.divideFile(file);
            FilesHandler.combineSeveralFiles(dividedFiles[0], dividedFiles[1], new File("sources/example"));
            FilesHandler.createFileWithEncodeString(new File("sources/example"));
            FilesHandler.createFileWithDecodeString(new File("sources/exampleEncode"));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}

//todo:
//        1. Разбить файл (информацию из одного файла записать в 2 разных файла)
//        Склеить файл (информацию из нескольких файлов записать в один файл)
//
//todo:
//        2. Написать свои реализации InputStream и OutputStream,
//        которые будут расширять FilterInputStream и FilterOutputStream (классы, позволяющие
//        создавать декораторы (обертки)).
//        В переопределяемых методах (read и write)
//        необходимо дешифровать и шифровать данные (использовать xor ^).
//        Данные реализации - обертки (декораторы) над любыми InputStream и OutputStream,
//        например, если речь идет о записи в файл,
//        то Ваш метод write шифрует данные и потом вызывает метод write fileoutputStream,
//        метод read вызывает метод read fileinputStream и потом дешифрует полученниые данные.
//        Объекты fileoutputStream и fileinputStream будут передаваться в конструкторы
//        Ваших реализаций InputStream и OutputStream.
