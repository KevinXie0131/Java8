package org.example.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.example.lambda.Employee;
import org.junit.Test;

public class TestStreamAPI1 {
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
    public void test1(){
        Stream<String> str = emps.stream().map((e) -> e.getName());
        System.out.println("-------------------------------------------");

        List<String> strList = Arrays.asList("aaa", "bbb", "ccc", "ddd", "eee");
        Stream<String> stream = strList.stream().map(String::toUpperCase);
        stream.forEach(System.out::println);

        Stream<Stream<Character>> stream2 = strList.stream().map(TestStreamAPI1::filterCharacter);
        stream2.forEach((sm) -> {
            sm.forEach(System.out::println);
        });
        System.out.println("---------------------------------------------");

        Stream<Character> stream3 = strList.stream()
                .flatMap(TestStreamAPI1::filterCharacter);
        stream3.forEach(System.out::println);
    }

    public static Stream<Character> filterCharacter(String str){
        List<Character> list = new ArrayList<>();

        for (Character ch : str.toCharArray()) {
            list.add(ch);
        }

        return list.stream();
    }

    /*
		sorted()
		sorted(Comparator com)
	 */
    @Test
    public void test2(){
        emps.stream()
                .map(Employee::getName)
                .sorted()
                .forEach(System.out::println);

        System.out.println("------------------------------------");

        emps.stream()
                .sorted((x, y) -> {
                    if(x.getAge() == y.getAge()){
                        return x.getName().compareTo(y.getName());
                    }else{
                        return Integer.compare(x.getAge(), y.getAge());
                    }
                }).forEach(System.out::println);
    }
}
