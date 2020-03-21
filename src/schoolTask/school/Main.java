package schoolTask.school;

import java.util.Arrays;

public class Main
{
    public static void main(String[] args) throws InterruptedException
    {
        School school13 = new School("SPBSchool 13", Director.getInstance("Ivan Petrovich",43));
        System.out.println(school13.toString());

        school13.addPupil(new Pupil("Vasya",14,school13.getStydingObjects()[0]));
        school13.addPupil(new Pupil("Gena",13,school13.getStydingObjects()[0]));
        school13.addPupil(new Pupil("Vovochka",13,school13.getStydingObjects()[1]));
        school13.addPupil(new Pupil("Pavel",14,school13.getStydingObjects()[2]));

        System.out.println(school13.toString());
        school13.addTeacher(new Teacher("Marina Petrovna",50,school13.getStydingObjects()[0]));
        school13.addTeacher(new Teacher("Petr Semenov",40,school13.getStydingObjects()[1]));
        school13.addTeacher(new Teacher("Victor Anatolievich", 29,school13.getStydingObjects()[2]));

        System.out.println(school13.toString());
        System.out.println();
        school13.schoolDay();
        System.out.println();
        System.out.println(school13.toString());
        System.out.println();
        school13.schoolDay();
        school13.assignDirector(school13.getTeachers()[0]);

        System.out.println(school13.toString());

        school13.addStydingObject("physical training");

        System.out.println(Arrays.toString(school13.getStydingObjects()));

    }
}
