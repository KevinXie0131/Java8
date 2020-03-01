package org.example.lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;

import org.junit.Test;

public class TestLambda1 {

    @Test
    public void test1(){
        Comparator<String> com = new Comparator<String>(){
            @Override
            public int compare(String o1, String o2) {
                return Integer.compare(o1.length(), o2.length());
            }
        };

        TreeSet<String> ts = new TreeSet<>(com);

        TreeSet<String> ts2 = new TreeSet<>(new Comparator<String>(){
            @Override
            public int compare(String o1, String o2) {
                return Integer.compare(o1.length(), o2.length());
            }

        });
    }

    // Lambda
    @Test
    public void test2(){
        Comparator<String> com = (x, y) -> Integer.compare(x.length(), y.length());
        TreeSet<String> ts = new TreeSet<>(com);
    }

    List<Employee> emps = Arrays.asList(
            new Employee(101, "tom", 18, 9999.99),
            new Employee(102, "john", 59, 6666.66),
            new Employee(103, "jane", 28, 3333.33),
            new Employee(104, "july", 8, 7777.77),
            new Employee(105, "jack", 38, 5555.55)
    );

    //age < 35
    public List<Employee> filterEmployeeAge(List<Employee> emps){
        List<Employee> list = new ArrayList<>();

        for (Employee emp : emps) {
            if(emp.getAge() <= 35){
                list.add(emp);
            }
        }

        return list;
    }

    //salary > 5000
    public List<Employee> filterEmployeeSalary(List<Employee> emps){
        List<Employee> list = new ArrayList<>();

        for (Employee emp : emps) {
            if(emp.getSalary() >= 5000){
                list.add(emp);
            }
        }

        return list;
    }

    @Test
    public void test3(){
        List<Employee> list = filterEmployeeAge(emps);

        for (Employee employee : list) {
            System.out.println(employee);
        }
    }

    //method 1
    public List<Employee> filterEmployee(List<Employee> emps, MyPredicate<Employee> mp){
        List<Employee> list = new ArrayList<>();

        for (Employee employee : emps) {
            if(mp.test(employee)){
                list.add(employee);
            }
        }

        return list;
    }

    @Test
    public void test4(){
        List<Employee> list = filterEmployee(emps, new FilterEmployeeForAge());
        for (Employee employee : list) {
            System.out.println(employee);
        }

        System.out.println("------------------------------------------");

        List<Employee> list2 = filterEmployee(emps, new FilterEmployeeForSalary());
        for (Employee employee : list2) {
            System.out.println(employee);
        }
    }

    //method 2
    @Test
    public void test5(){
        List<Employee> list = filterEmployee(emps, new MyPredicate<Employee>() {
            @Override
            public boolean test(Employee t) {
                return t.getId() <= 103;
            }
        });

        for (Employee employee : list) {
            System.out.println(employee);
        }
    }

    //method 3
    @Test
    public void test6(){
        List<Employee> list = filterEmployee(emps, (e) -> e.getAge() <= 35);
        list.forEach(System.out::println);

        System.out.println("------------------------------------------");

        List<Employee> list2 = filterEmployee(emps, (e) -> e.getSalary() >= 5000);
        list2.forEach(System.out::println);
    }

    //method 4
    @Test
    public void test7(){
        emps.stream().filter((e) -> e.getAge() <= 35)
                     .forEach(System.out::println);

        System.out.println("----------------------------------------------");

        emps.stream()
            .map(Employee::getName)
            .limit(3)
            .sorted()
            .forEach(System.out::println);
    }
}
