package org.example.stream;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.example.lambda.Employee;
import org.junit.Test;

public class TestStreamAPI2 {

    List<Employee> emps = Arrays.asList(
            new Employee(102, "tom", 59, 6666.66),
            new Employee(101, "jack", 18, 9999.99),
            new Employee(103, "tim", 28, 3333.33),
            new Employee(104, "june", 8, 7777.77),
            new Employee(104, "laura", 8, 7777.77),
            new Employee(104, "lily", 8, 7777.77),
            new Employee(105, "lucy", 38, 5555.55)
    );

    @Test
    public void test(){
        Integer[] nums = new Integer[]{1,2,3,4,5};

        Arrays.stream(nums)
                .map((x) -> x * x)
                .forEach(System.out::println);
    }

    /*
		allMatch
		anyMatch
		noneMatch
		findFirst
		findAn
		count
		max
		min
	 */
    @Test
    public void test1(){
        boolean bl = emps.stream()
                .allMatch((e) -> e.getName().equals("tom"));
        System.out.println(bl);

        boolean bl1 = emps.stream()
                .anyMatch((e) -> String.valueOf(e.getAge()).equals("8"));
        System.out.println(bl1);

        boolean bl2 = emps.stream()
                .noneMatch((e) -> e.getName().equals("kitty"));
        System.out.println(bl2);
    }

    @Test
    public void test2(){
        Optional<Employee> op = emps.stream()
                .sorted((e1, e2) -> Double.compare(e1.getSalary(), e2.getSalary()))
                .findFirst();
        System.out.println(op.get());
        System.out.println("--------------------------------");

        Optional<Employee> op2 = emps.parallelStream()
                .filter((e) -> String.valueOf(e.getAge()).equals("8"))
                .findAny();
        System.out.println(op2.get());
    }

    @Test
    public void test3(){
        long count = emps.stream()
                .filter((e) -> String.valueOf(e.getAge()).equals("8"))
                .count();
        System.out.println(count);

        Optional<Double> op = emps.stream()
                .map(Employee::getSalary)
                .max(Double::compare);
        System.out.println(op.get());

        Optional<Employee> op2 = emps.stream()
                .min((e1, e2) -> Double.compare(e1.getSalary(), e2.getSalary()));
        System.out.println(op2.get());
    }

    @Test
    public void test4(){
        Stream<Employee> stream = emps.stream()
                .filter((e) -> String.valueOf(e.getAge()).equals("8"));

        long count = stream.count();

        stream.map(Employee::getSalary).max(Double::compare);
    }
}
