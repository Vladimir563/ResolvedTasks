package schoolTask.school;

import java.util.Arrays;

public class School
{
    private String schoolName;
    private Director director;
    private Pupil [] pupils = new Pupil[1];
    private String [] stydingObjects = {"mathematics","physics","history"};
    private Teacher [] teachers = new Teacher[1];

    public String[] getStydingObjects()
    {
        return stydingObjects;
    }

    private int pupilCounter;

    private int teacherCounter;

    private int stydingObjectsCounter = stydingObjects.length;

    public School(String schoolName, Director director) //конструктор
    {
        this.schoolName = schoolName;
        this.director = director;
    }

    public Pupil[] getPupils() {
        return pupils;
    }

    public Teacher[] getTeachers() {
        return teachers;
    }

    public void addPupil(Pupil newPupil) //добавить ученика
    {
        if(pupils[pupils.length - 1] != null)
        {
            Pupil [] newPupils = Arrays.copyOfRange(pupils,0,pupils.length + 1);
            newPupils [pupilCounter] = newPupil;
            pupils = newPupils;
            pupilCounter++;
        }
        else
        {
            pupils[pupilCounter] = newPupil;
            pupilCounter++;
        }
    }

    public void addTeacher(Teacher newTeacher) //добавить учителя
    {
        if(teachers[teachers.length - 1] != null)
        {
            Teacher [] newTeachers = Arrays.copyOfRange(teachers,0,teachers.length + 1);
            newTeachers [teacherCounter] = newTeacher;
            teachers = newTeachers;
            teacherCounter++;
        }
        else
        {
            teachers[teacherCounter] = newTeacher;
            teacherCounter++;
        }
    }

    public void assignDirector(Human newDir) //назначить директора
    {
        director.Name = newDir.getName();
        director.Age = newDir.getAge();
    }

    public void schoolDay() //школьный день
    {
        String [] learnedPupils = new String [0];
        director.declareStartStydingOfDay();
        int counter = 0;

        for (int i = 0; i < teachers.length; i++)
        {
            for (int j = 0; j < pupils.length; j++)
            {
                if(!Arrays.asList(learnedPupils).contains(pupils[j].getName()) && teachers[i].getTeachingObject() == pupils[j].getStydingObject())
                {
                    try
                    {
                        Thread.sleep(400);
                        System.out.printf("Учитель %s учит %s...\n",teachers[i].getName(),pupils[j].getName());
                        learnedPupils = Arrays.copyOfRange(learnedPupils,0,learnedPupils.length + 1);
                        learnedPupils [counter] = pupils[j].getName();
                        teachers[i].teach(pupils[j]);
                        counter++;
                        Thread.sleep(400);
                    }
                    catch (InterruptedException e)
                    {
                        System.out.println(e);
                    }
                }
            }
        }
        director.declareEndStydingOfDay();
    }

    public void addStydingObject(String objectName) //добавить школьный предмет
    {
        if(stydingObjects[stydingObjects.length-1] != null)
        {
            stydingObjects = Arrays.copyOfRange(stydingObjects,0,stydingObjects.length + 1);
            stydingObjects[stydingObjectsCounter] = objectName;
            stydingObjectsCounter++;
        }
        else
        {
            stydingObjects[stydingObjectsCounter] = objectName;
            stydingObjectsCounter++;
        }
    }

    @Override
    public String toString() {
        return "schoolName='" + schoolName + '\'' + ", director=" + director.Name + ", pupils=" + Arrays.toString(returnHumansList(pupils)) + ", teachers=" + Arrays.toString(returnHumansList(teachers));
    }

    public String [] returnHumansList(Human [] humans) //возвращает строку всех учителей/учеников
    {
        String [] humList = new String[humans.length];
        if(humans[0] == null)
        {
            return humList;
        }

        for (int i = 0; i < humans.length; i++)
        {
            humList[i] = humans[i].toString();
        }

        return humList;
    }

}
