package collectionstasks;

// Создать список объектов List<Employee> (использовать метод employeeGenerator)
// и сортировать по:
// имени
// имени и зарплате
// имени, зарплате, возрасту и компании

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class Employee
{
    private String name;
    private String company;
    private int salary;
    private int age;

    // TODO: конструктор, геттеры и сеттеры

    public Employee(String name, String company, int salary, int age)
    {
        this.name = name;
        this.company = company;
        this.salary = salary;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return getSalary() == employee.getSalary() &&
                getAge() == employee.getAge() &&
                Objects.equals(getName(), employee.getName()) &&
                Objects.equals(getCompany(), employee.getCompany());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getCompany(), getSalary(), getAge());
    }

    public static List<Employee> employeeGenerator(int num)
    {
        // метод для создания списка объектов класса Employee
        String[] names = {"Mike", "Tom", "Alex", "John", "Peter", "Jack", "Charlie", "Max", "Jenifer", "Linda", "Elizabeth"}; // массив с именами
        String[] companies = {"Microsoft", "IBM", "Google", "General Electric", "Siemens", "Samsung", "Apple"}; // массив с названиями компаний
        Integer [] salaries = {1000,2000,3000,4000,5000,6000,7000};

        List<Employee> employees = new ArrayList<>(num);

        // добавление num объектов Employee в список (employees)
        for (int i = 0; i < num; i++)
        {
            // TODO: объекты создавать с рандомными значениями. Возраст от 21 до 60 и не забудьте про зп
            employees.add(new Employee(names[((int) (Math.random() * names.length))], companies[((int) (Math.random() * companies.length))],salaries[((int) (Math.random() * salaries.length))],(21 + (int)(Math.random() * 40))));
        }
        return employees;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", company='" + company + '\'' +
                ", salary=" + salary +
                ", age=" + age +
                '}';
    }
}

//todo: компараторы
class EmployeeNameComparator implements Comparator<Employee>
{
    @Override
    public int compare(Employee o1, Employee o2)
    {
        return o1.getName().compareTo(o2.getName());
    }
}

class EmployeeAgeComparator implements Comparator<Employee>
{
    @Override
    public int compare(Employee o1, Employee o2)
    {
        return Integer.compare(o1.getAge(),o2.getAge());
    }
}

class EmployeeSalaryComparator implements Comparator<Employee>
{
    @Override
    public int compare(Employee o1, Employee o2)
    {
        return Integer.compare(o1.getSalary(),o2.getSalary());
    }
}

class EmployeeCompanyComparator implements Comparator<Employee>
{
    @Override
    public int compare(Employee o1, Employee o2)
    {
        return o1.getCompany().compareTo(o2.getCompany());
    }
}