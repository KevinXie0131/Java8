package org.example.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

import org.example.lambda.Employee;
import org.junit.Test;

public class TestStreamAPI {
    //1.
    @Test
    public void test1(){
        //1.
        List<String> list = new ArrayList<>();
        Stream<String> stream = list.stream();
        Stream<String> parallelStream = list.parallelStream();

        //2.
        Integer[] nums = new Integer[10];
        Stream<Integer> stream1 = Arrays.stream(nums);

        //3.
        Stream<Integer> stream2 = Stream.of(1,2,3,4,5,6);

        //4.
        Stream<Integer> stream3 = Stream.iterate(0, (x) -> x + 2).limit(10);
        stream3.forEach(System.out::println);

        Stream<Double> stream4 = Stream.generate(Math::random).limit(2);
        stream4.forEach(System.out::println);
    }

    //2.
    List<Employee> emps = Arrays.asList(
            new Employee(102, "tom", 59, 6666.66),
            new Employee(101, "jack", 18, 9999.99),
            new Employee(103, "tim", 28, 3333.33),
            new Employee(104, "june", 8, 7777.77),
            new Employee(104, "laura", 8, 7777.77),
            new Employee(104, "lily", 8, 7777.77),
            new Employee(105, "lucy", 38, 5555.55)
    );

    /*
		filter
		limit
		skip(n)
		distinct
	 */
    @Test
    public void test2(){
        Stream<Employee> stream = emps.stream()
                .filter((e) -> {
                    System.out.println("test");
                    return e.getAge() <= 35;
                });
        stream.forEach(System.out::println);
    }

    @Test
    public void test3(){
        Iterator<Employee> it = emps.iterator();

        while(it.hasNext()){
            System.out.println(it.next());
        }
    }

    @Test
    public void test4(){
        emps.stream()
                .filter((e) -> {
                    System.out.println("skip！"); // &&  ||
                    return e.getSalary() >= 5000;
                }).limit(3)
                .forEach(System.out::println);
    }

    @Test
    public void test5(){
        emps.parallelStream()
                .filter((e) -> e.getSalary() >= 5000)
                .skip(2)
                .forEach(System.out::println);
    }

    @Test
    public void test6(){
        emps.stream()
                .distinct()
                .forEach(System.out::println);
    }

}
