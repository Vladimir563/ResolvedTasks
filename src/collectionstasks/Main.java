package collectionstasks;

import java.util.Comparator;
import java.util.TreeSet;

public class Main
{
    public static void main(String[] args)
    {
//        Comparator<Employee> employeeNameComparator = new EmployeeNameComparator();
//        Comparator<Employee> employeeNameComparator = (obj1, obj2) -> obj1.getName().compareTo(obj2.getName());
        Comparator<Employee> employeeNameComparator = Comparator.comparing(Employee::getName);

/*        Comparator<Employee> employeeNameSalaryComparator = new EmployeeNameComparator()
                .thenComparing(new EmployeeSalaryComparator());*/
        Comparator<Employee> employeeNameSalaryComparator = Comparator.comparing(Employee::getName).thenComparing(Employee::getSalary);

/*        Comparator<Employee> employeeNameSalaryAgeCompanyComparator = employeeNameSalaryComparator
                .thenComparing(new EmployeeAgeComparator()) .thenComparing(new EmployeeCompanyComparator());*/
        Comparator<Employee> employeeNameSalaryAgeCompanyComparator = Comparator.comparing(Employee::getName).thenComparing(Employee::getSalary).thenComparing(Employee::getAge).thenComparing(Employee::getCompany);

        TreeSet<Employee> employeeTreeSet = new TreeSet<>(employeeNameComparator);
        employeeTreeSet.addAll(Employee.employeeGenerator(5));
        System.out.println("Сортировка только по имени");
        employeeTreeSet.forEach(System.out::println);
        System.out.println();

        employeeTreeSet = new TreeSet<>(employeeNameSalaryComparator);
        employeeTreeSet.addAll(Employee.employeeGenerator(5));
        System.out.println("Сортировка по имени и зарплате");
        employeeTreeSet.forEach(System.out::println);
        System.out.println();

        employeeTreeSet = new TreeSet<>(employeeNameSalaryAgeCompanyComparator);
        employeeTreeSet.addAll(Employee.employeeGenerator(5));
        System.out.println("Сортировка по имени, зарплате, возрасту и компании");
        employeeTreeSet.forEach(System.out::println);
    }
}


//todo:
// Создать список объектов List<Employee> (использовать метод employeeGenerator)
// и сортировать по:
// имени
// имени и зарплате
// имени, зарплате, возрасту и компании