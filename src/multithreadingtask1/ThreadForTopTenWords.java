package multithreadingtask1;

import java.io.*;
import java.util.*;

public class ThreadForTopTenWords extends Thread
{
    private Map <String, Integer> mainHashMap; //ссылка на основную мапу
    private File file;
    private int quarter; //четверть текста
    private int threadsCount; //количество потоков

    public Map<String, Integer> getMainHashMap() {
        return mainHashMap;
    }

    public void setMainHashMap(Map<String, Integer> mainHashMap) {
        this.mainHashMap = mainHashMap;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public int getQuarter() {
        return quarter;
    }

    public void setQuarter(int quarter) {
        this.quarter = quarter;
    }

    public int getThreadsCount() {
        return threadsCount;
    }

    public void setThreadsCount(int threadsCount) {
        this.threadsCount = threadsCount;
    }


    public ThreadForTopTenWords(String name, Map<String, Integer> mainHashMap, File file, int quarter, int threadsCount)
    {
        super(name);
        this.mainHashMap = mainHashMap;
        this.file = file;
        this.quarter = quarter;
        this.threadsCount = threadsCount;
    }

    @Override
    public void run()
    {
        try
        {
            List<String> stringList = readTextFromFile(getFile(), getThreadsCount(), getQuarter());

            Map<String, Integer> stringIntegerMap = new HashMap<>();

            for (String s : stringList)
            {
                String [] arrayList;
                arrayList = s.split(" ");
                for (int i = 0; i < arrayList.length; i++)
                {
                    if(!stringIntegerMap.containsKey(arrayList[i]))
                    {
                        stringIntegerMap.put(arrayList[i], 1);
                    }
                    else
                    {
                        stringIntegerMap.put(arrayList[i], stringIntegerMap.get(arrayList[i])+1);
                    }
                }
            }

            System.out.println("ssfsfsdfds");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }


    private List<String> readTextFromFile(File file, int threadsCount, int part) throws IOException
    {
        List<String> stringList = new ArrayList<>();

        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        String line = bufferedReader.readLine();

        while (line != null)
        {
            if(line.trim().length() > 6)
            {
                stringList.add(line.toLowerCase().replaceAll("[^а-я]"," ").trim());
            }
            line = bufferedReader.readLine();
        }

        List<List<String>> newList = new ArrayList<>();
        int partitionSize = stringList.size()/threadsCount;
        for (int i = 0; i <= stringList.size(); i+=partitionSize)
        {
            newList.add(stringList.subList(i,Math.min(i + partitionSize, stringList.size())));
        }

        return newList.get(part);
    }
}
