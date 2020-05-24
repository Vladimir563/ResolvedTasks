package multithreadingtask1;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class Main
{
    public static void main(String[] args)
    {
        ArrayList <ThreadForTopTenWords> threads = new ArrayList<>();
        Map<String, Integer> topTenWordsMap = new HashMap<>();
        File file = new File("sources/voina_i_mir.txt");
        int threadsCount = Runtime.getRuntime().availableProcessors(); //количество ядер/потоков которые могут работать одновременно

//todo: создание и добавление потоков в список
        for (int i = 0; i < threadsCount; i++)
        {
            threads.add(new ThreadForTopTenWords("thread " + i, topTenWordsMap, file, i, threadsCount));
        }

        threads.get(0).start();
    }

}

/*топ 100 слов параллельно
        Количество потоков = Runtime.getRuntime().availableProcessors()

        ып ряывр ря вро еочн еоч нк отрнкв о оакон ночк
        общий map
        [общий map / ып ряывр ря]
        [общий map / вро еочн еоч]
        [общий map / нк отрнкв о оа]
        [общий map / кон ночк]

        Каждый поток собирает результат в свой map,
        затем добавляет в общий map.

        Поток, который создавал другие потоки должен ожидать их завершения и
        выводить результат в консоль*/
