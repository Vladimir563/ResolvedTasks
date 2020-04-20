package collectionstasks;

import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;

import java.util.Comparator;
import java.util.TreeSet;

public class Main
{
    public static void main(String[] args)
    {
        Comparator<Employee> employeeNameComparator = new EmployeeNameComparator();

        Comparator<Employee> employeeNameSalaryComparator = new EmployeeNameComparator()
                .thenComparing(new EmployeeSalaryComparator());

        Comparator<Employee> employeeNameSalaryAgeCompanyComparator = employeeNameSalaryComparator
                .thenComparing(new EmployeeAgeComparator()) .thenComparing(new EmployeeCompanyComparator());

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