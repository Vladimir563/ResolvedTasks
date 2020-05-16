package streamapitasks;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TopTenWords
{
    public static void main(String[] args) throws IOException
    {
        // создать Map<String, Long> map
        // String - слово
        // Long - частота встречаемости
        // в мапу должны войти только первые 10 частоте встречаемости слов
        // текст в файле

        File file = new File("sources/task21.txt");

        Map <String, Long> map =
                Files.lines(file.toPath())
                .parallel()
                .flatMap(line -> Stream.of(line.split(" ")))
                .map(word -> word.toLowerCase().trim())
                .collect(Collectors.toCollection(ArrayList::new))
                .stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet()
                .parallelStream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .limit(10)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        //сортировка в мапе не сохранится, т.к это HashMap (порядок хранения отличается от порядка добавления)
        map.entrySet().stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .forEach(System.out::println);

//todo:
// = читаем из файла в stream
// сделать stream параллельным
// обработать каждый элемент: убрать пробелы, привести к нижнему регистру
// создать новый stream: массив слов - flatMap
// собрать в map: слово - количество
// получить entrySet() терминальная операция!!!
// снова создать параллельный stream
// сортировать по значениям
// получить первые 10 элементов
// собирать мапу; терминальная операция!!!
// вывести в консоль
    }
}
