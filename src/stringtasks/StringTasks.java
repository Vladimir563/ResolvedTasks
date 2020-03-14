package stringtasks;

import java.util.Arrays;

public class StringTasks
{
    public static void main(String[] args)
    {
/*      1)  Даны 2 слова, состоящие из четного числа букв.
        Получить слово состоящее из первой половины первого слова и второй половины второго слова.*/
        System.out.println("Task1:");
        String str1 = "children";
        String str2 = "pregnant";
        StringBuilder sb = new StringBuilder();
        String partOfStr1 = str1.substring(0,str1.length()/2);
        String partOfStr2 = str2.substring(str2.length()/2,str2.length());
        String mergeStr = sb.append(partOfStr1).append(partOfStr2).toString();
//      так короче но, менее читабельно
//      String mergeStr = sb.append(str1.substring(0,str1.length()/2)).append(str2.substring(str2.length()/2,str2.length())).toString();
        System.out.printf("Первая половина %s + вторая половина %s = %s\n\n",str1,str2,mergeStr);

//      2)  Найдите самое длинное слово в предложении, при условии, что в предложении все слова разной длины.
        System.out.println("Task2:");
        String sentence = "Найдите самое длинное слово в предложении, при условии, что в предложении все слова разной длины.";
        String [] strArr = sentence.split(" |\\,|\\.");
        String biggestWord = "";
        for (int i = 0; i < strArr.length; i++)
        {
            if(strArr[i].length() > biggestWord.length())
            {
                biggestWord = strArr[i];
            }
        }
        System.out.printf("The biggest word in sentence: \"%s\"\n\n",biggestWord);

//      3)  Имеются две строки. Найти количество вхождений одной строки в другую.
        System.out.println("Task3:");
        String sentence1 = "кот кот Котик котяра кошка";
        String word = "кот";
        System.out.printf("Количество вхождений слова \"%s\" в строку \"%s\" (без учета регистра) = %d\n\n",word,sentence1,EntranceWordsCount(word,sentence1));

/*      4)  Написать функцию, которая проверяет, является ли строка палиндромом.
            Палиндром — это слово или фраза, которые одинаково читаются по буквам
            или по словам как слева направо, так и справа налево.*/
        System.out.println("Task4:");
        String palindromWord = "доход";
        String noPalindormWord = "пример";
        System.out.printf("\"%s\" %s палиндромом\n",palindromWord,IsPalindromWord(palindromWord) ?"является":"не является");
        System.out.printf("\"%s\" %s палиндромом\n\n",noPalindormWord,IsPalindromWord(noPalindormWord) ?"является":"не является");

/*      5)  Даны два слова и словарь (массив слов). Необходимо найти алгоритм превращения в другое, меняя за один шаг одну букву,
            причем каждое новое слово должно быть в словаре (массиве слов).
            Например,даны слова "hit" и "cog" и словарь(массив слов) ["hot", "dot", "dog", "log", "lot"].
            Один из вариантов цепочки: "hit"->"hot"->"dot"->"dog"->"cog".*/
        System.out.println("Task5:");
        String s1 = "hit";
        String s2 = "cog";
        String [] arrS = {"hot", "dot", "dog","dos", "got", "log", "lot"};

        char [] s1Arr = s1.toCharArray();
        char [] s2Arr = s2.toCharArray();
        String [] wordsChain = new String[arrS.length];
        String interimString;
        String helpString = s1;
        int counter = 0;
        for (int i = 0; i < s1Arr.length; i++)
        {
            for (int j = 1; j < (Math.abs(s1Arr[i] - s2Arr[i])+1); j++)
            {
                int index = (s1Arr[i] - s2Arr[i]) > 0 ? 1: -1; // получаем знак индекса, чтобы понимать в какую сторону двигаться по таблице юникода
                s1Arr[i] -= j * index;
                interimString = new String(s1Arr);
                if(ReturnExistWord(arrS, interimString) != null)
                {
                    s1Arr = ReturnExistWord(arrS, interimString).toCharArray();
                    helpString = new String(s1Arr);
                    wordsChain[counter] = helpString; //обновляем исходное слово
                    counter++;
                    i = -1; //если слово есть в словаре сбрасываем счетчик и снова идем от первой буквы
                    break;
                }
                else
                {
                    s1Arr = helpString.toCharArray(); //если слова нет в "словаре" сбрасываем до последнего слова которое есть
                }
            }
        }

        System.out.printf("%s --> ",s1);
        for(String s : wordsChain)
        {
            if(s != null)
            {
                System.out.printf("%s -- > ",s);
            }
        }
        System.out.printf("%s\n\n",s2);

/*      6)  Пользователь вводит названия городов через пробел. Вы их присваиваете переменной.
        Переставьте названия так, чтобы они были упорядочены по алфавиту.*/
        System.out.println("Task6:");
        String usersEnter = "Москва Архангельск Санкт-Петербург Лондон";

        String [] cityArr = usersEnter.split(" ");
        String [] citySort = new String[cityArr.length];
        System.out.println("Исходный массив:");
        System.out.println(Arrays.toString(cityArr));
        for (int i = 0; i < citySort.length; i++)
        {
            citySort[i] = ReturnFirstWordByLetter(cityArr);
            cityArr = DeleteElementFromArr(cityArr, ReturnFirstWordByLetter(cityArr));
        }
        System.out.println("Отсортированный массив:");
        System.out.println(Arrays.toString(citySort));
    }

    public static int EntranceWordsCount (String word, String sentence)
    {
        sentence = sentence.toLowerCase(); // без учета регистра
        String [] strArr = sentence.split(" ");
        int entranceCount = 0;
        for (int i = 0; i < strArr.length; i++)
        {
            if(strArr[i].startsWith(word))
            {
                entranceCount++;
            }
        }
        return entranceCount;
    }

    public static boolean IsPalindromWord(String word)
    {
        StringBuilder sb = new StringBuilder(word);
        if(word.equals(sb.reverse().toString()))
        {
            return true;
        }
        return false;
    }

    public static String ReturnExistWord(String [] strArr, String str)
    {
        String existWord = null;
        for(String s : strArr)
        {
            if(s.equals(str))
            {
                existWord = str;
            }
        }
        return existWord;
    }

    public static String ReturnFirstWordByLetter(String [] strArr) //метод возвращающий слово первая буква которого идет раньше первых букв остальных слов из массива
    {
        String firstWordInArr = "Я";
        for (int i = 0; i < strArr.length; i++)
        {
            if(strArr[i].toCharArray()[0] < firstWordInArr.toCharArray()[0])
            {
                firstWordInArr = strArr[i];
            }
        }
        return firstWordInArr;
    }

    public static String [] DeleteElementFromArr(String [] strArr, String str) //метод удаляющий слово из массива
    {
        String [] newArr = new String[strArr.length-1];
        int counter = 0;
        for (int i = 0; i < strArr.length; i++)
        {
            if(strArr[i] != str)
            {
                newArr[counter] = strArr[i];
                counter++;
            }
        }
        return newArr;
    }
}


