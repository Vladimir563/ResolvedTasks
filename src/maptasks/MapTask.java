package maptasks;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapTask {
    public static void main(String[] args)
    {
        // TODO:: написать статический метод, который принимает на вход мапу (firstTaskMap) и город (city)
        //  и формирует список (List) логинов, которые соответствуют переданному городу

        HashMap<String, String> firstTaskMap = new HashMap<>();
        firstTaskMap.put("qwe", "Тверь");
        firstTaskMap.put("asd", "Тверь");
        firstTaskMap.put("zxc", "Москва");
        firstTaskMap.put("rty", "Тверь");
        firstTaskMap.put("fgh", "Магадан");

        String city = "Тверь";

        System.out.println("Task1:");
        System.out.println(returnLoginList(firstTaskMap,city));

        // TODO:: дан список слов (words). Написать метод, который будет возвращать количество одинаковых слов в списке
        //  в виде Map<String, Integer>, где String - слово и Integer - количество повторений

        List<String> words = new ArrayList<>();
        words.add("may");
        words.add("august");
        words.add("june");
        words.add("may");
        words.add("may");
        words.add("july");
        words.add("may");
        words.add("august");
        words.add("august");

        System.out.println("\nTask2:");
        System.out.println(returnCountOfSameWords(words));


        // TODO:: дана мапа (customerMap).
        //  Написать метод, который принимает на вход агрументы int from и int to и возвращает новую мапу,
        //  в которую войдут все покупатели, возраст которых находится в диапазоне [from, to)

        HashMap<String, Customer> customerMap = new HashMap<>();
        customerMap.put("1", new Customer("Павел", "1", 23));
        customerMap.put("2", new Customer("Олег", "2", 17));
        customerMap.put("3", new Customer("Максим", "3", 48));
        customerMap.put("4", new Customer("Евгения", "4", 67));

        System.out.println("\nTask3:");
        System.out.println(returnCostumersInRange(customerMap,17,50));

        // TODO:: Задания по тексту (text). На каждый пункт - минимум один метод:
        //  1. написать метод, принимающий на вход слово и возвращающий частоту встречаемости данного слова в тексте
        //  2. написать метод, который собирает все слова в группы по количеству букв:
        //  например, в одну группу попадут слова: 3 - [the, war, jar, get, met...], в другую 2 - [on, up, no, of...]
        //  3. написать метод, который выводит в консоль топ 10 самых частых слов
        //  4. Вывести частоту встречаемости букв анг алфавита в данном тексте. Вывести в процентах для каждой буквы

        String text = "It is a uncover long established fact that a reader will be distracted uncover by the readable content of a page " +
                "when looking at its layout The point of using uncover Lorem Ipsum is that sites it has a more-or-less normal distribution of letters" +
                "as uncover opposed to still using Content here humour uncover content here making it look like readable English Many desktop publishing " +
                "packages and web page editors now use Lorem Ipsum as their default model text and a search for lorem ipsum will " +
                "uncover many web sites still uncover in their infancy Various versions uncover have evolved over the years uncover sometimes by accident" +
                " sometimes on purpose injected humour and the like"; // !!! в тексте содержатся только буквы и пробельные символы !!!
    }

    static List <String> returnLoginList(HashMap <String,String> hashMap, String city)
    {
        List <String> login = new ArrayList<>();
        for(Map.Entry <String,String> entry : hashMap.entrySet())
        {
            if(entry.getValue().equals(city)) login.add(entry.getKey());
        }
        return login;
    }

    static Map <String,Integer> returnCountOfSameWords(List <String> wordsList)
    {
        HashMap <String,Integer> hashMap = new HashMap<>();
        for(String word : wordsList)
        {
            if(hashMap.containsKey(word))
            {
                hashMap.replace(word,hashMap.get(word)+1);
            }
            else
            {
                hashMap.put(word,1);
            }
        }
        return hashMap;
    }

    static Map<String, Customer> returnCostumersInRange(HashMap<String, Customer> hashMap ,int from, int to)
    {
        HashMap <String, Customer> newHashMap = new HashMap<>();
        int number = 1;
        for(Map.Entry<String, Customer> entry : hashMap.entrySet())
        {
            if(entry.getValue().getAge() >= from && entry.getValue().getAge() < to)
            {
                newHashMap.put(Integer.toString(number),entry.getValue());
            }
            number++;
        }
        return newHashMap;
    }

}
