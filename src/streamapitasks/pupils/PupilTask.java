package streamapitasks.pupils;

import java.time.LocalDate;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class PupilTask
{
    public static void main(String[] args)
    {
        Pupil pupil1 = new Pupil(1,"Vasya", Pupil.Gender.MALE, LocalDate.of(1990,2,1));
        Pupil pupil2 = new Pupil(3,"Igor", Pupil.Gender.MALE, LocalDate.of(1993,12,12));
        Pupil pupil3 = new Pupil(4, "Alyona",Pupil.Gender.FEMALE,LocalDate.of(1993,4,9));
        Pupil pupil4 = new Pupil(5, "Anya",Pupil.Gender.FEMALE,LocalDate.of(1997,12,19));
        Pupil pupil5 = new Pupil(5, "Anya",Pupil.Gender.FEMALE,LocalDate.of(1995,8,16));
        Pupil pupil6 = new Pupil(5, "Anya",Pupil.Gender.FEMALE,LocalDate.of(1990,1,9));

        List<Pupil> pupils = Arrays.asList(pupil1,pupil2,pupil3,pupil4,pupil5,pupil6);

        // обращение к enum Gender через имя класса - Pupil.Gender

        // Используя Stream API:

//todo:
// 1. Разделить учеников на две группы: мальчиков и девочек. Результат: Map<Pupil.Gender, ArrayList<Pupil>>
        Map<Pupil.Gender, ArrayList<Pupil>> boysAndGirls = pupils.stream()
                .collect(Collectors.groupingBy(Pupil::getGender, Collectors.toCollection(ArrayList::new)));
        boysAndGirls.entrySet().forEach(System.out::println);
        System.out.println();
//todo:
// 2. Найти средний возраст учеников
        int pupilsMiddleAge = pupils.stream()
                .sorted(Comparator.comparing(Pupil::getBirth))
                .skip(pupils.size()/2)
                .findFirst()
                .get().getBirth().getYear() *(-1) + LocalDate.now().getYear();
        System.out.println("Средний возраст учеников: " + pupilsMiddleAge);
        System.out.println();
//todo:
// 3. Найти самого младшего ученика
        Pupil smallerPupil = pupils.stream()
                .max(Comparator.comparing(Pupil::getBirth))
                .get();
        System.out.println("Самый младший ученик: " + smallerPupil);
        System.out.println();
//todo:
// 4. Найти самого старшего ученика
        Pupil olderPupil = pupils.stream()
                .min(Comparator.comparing(Pupil::getBirth))
                .get();
        System.out.println("Самый старший ученик: " + olderPupil);
        System.out.println();
//todo:
// 5. Собрать учеников в группы по году рождения
        Map<Integer,ArrayList<Pupil>> groupByBirth = pupils.stream()
                .collect(Collectors.groupingBy(pupil -> pupil.getBirth().getYear(), Collectors.toCollection(ArrayList::new)));
        groupByBirth.entrySet().forEach(System.out::println);
        System.out.println();
//todo:
// 6. Убрать учеников с одинаковыми именами, имена и дату рождения оставшихся вывести в консоль
        pupils.stream()
                .collect(Collectors.toMap(Pupil::getName, Function.identity(), (name1,name2) -> name1))
        .entrySet().forEach(pup -> System.out.println(pup.getValue().getName() + " (дата рождения " + pup.getValue().getBirth() + ")"));
        System.out.println();
//todo:
// 7. Отсортировать по полу, потом по дате рождения, потом по имени (в обратном порядке), собрать в список (List)
        List<Pupil> sortList = pupils.stream()
                .sorted(Comparator.comparing(Pupil::getGender).thenComparing(Pupil::getBirth).thenComparing(Pupil::getName).reversed())
                .collect(Collectors.toList());
        sortList.forEach(System.out::println);
        System.out.println();
//todo:
// 8. Вывести в консоль всех учеников в возрасте от N до M лет
        pupils.stream()
                .filter(pupil -> (pupil.getBirth().getYear()*(-1) + LocalDate.now().getYear() < 30)
                        && (pupil.getBirth().getYear()*(-1) + LocalDate.now().getYear())  > 22)
        .forEach(pupil -> System.out.println(pupil.getName() + ":" + (pupil.getBirth().getYear()*(-1) + LocalDate.now().getYear())));
        System.out.println();
//todo:
// 9. Собрать в список всех учеников с именем=someName
        List<Pupil> pupWithSomeName = pupils.stream()
                .filter(pupil -> pupil.getName().equals("Anya"))
                .collect(Collectors.toList());
        pupWithSomeName.forEach(System.out::println);
        System.out.println();
//todo:
// 10. Собрать Map<Pupil.Gender, Integer>, где Pupil.Gender - пол, Integer - суммарный возраст учеников данного пола
        Map<Pupil.Gender, Integer> genderSumAgeMap = pupils.stream()
                .collect(Collectors.groupingBy(Pupil::getGender, Collectors.summingInt(pup -> pup.getBirth().getYear()*(-1)
                        + LocalDate.now().getYear())));
        genderSumAgeMap.entrySet().forEach(System.out::println);
    }
}