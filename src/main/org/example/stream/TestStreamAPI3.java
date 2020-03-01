package org.example.stream;

import java.util.Arrays;
import java.util.DoubleSummaryStatistics;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.example.lambda.Employee;
import org.junit.Test;

public class TestStreamAPI3 {
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
        Optional<Integer> count = emps.stream()
                .map((e) -> 1)
                .reduce(Integer::sum);

        System.out.println(count.get());
    }

    /*
        reduce(T identity, BinaryOperator) / reduce(BinaryOperator)
     */
    @Test
    public void test1(){
        List<Integer> list = Arrays.asList(1,2,3,4,5,6,7,8,9,10);

        Integer sum = list.stream()
                .reduce(0, (x, y) -> x + y);

        System.out.println(sum);
        System.out.println("----------------------------------------");

        Optional<Double> op = emps.stream()
                .map(Employee::getSalary)
                .reduce(Double::sum);

        System.out.println(op.get());
    }

    @Test
    public void test2(){
        Optional<Integer> sum = emps.stream()
                .map(Employee::getName)
                .flatMap(TestStreamAPI1::filterCharacter)
                .map((ch) -> {
                    if(ch.equals('t'))
                        return 1;
                    else
                        return 0;
                }).reduce(Integer::sum);

        System.out.println(sum.get());
    }

    @Test
    public void test3(){
        List<String> list = emps.stream()
                .map(Employee::getName)
                .collect(Collectors.toList());

        list.forEach(System.out::println);
        System.out.println("----------------------------------");

        Set<String> set = emps.stream()
                .map(Employee::getName)
                .collect(Collectors.toSet());

        set.forEach(System.out::println);
        System.out.println("----------------------------------");

        HashSet<String> hs = emps.stream()
                .map(Employee::getName)
                .collect(Collectors.toCollection(HashSet::new));

        hs.forEach(System.out::println);
    }

    @Test
    public void test4(){
        Optional<Double> max = emps.stream()
                .map(Employee::getSalary)
                .collect(Collectors.maxBy(Double::compare));

        System.out.println(max.get());

        Optional<Employee> op = emps.stream()
                .collect(Collectors.minBy((e1, e2) -> Double.compare(e1.getSalary(), e2.getSalary())));

        System.out.println(op.get());

        Double sum = emps.stream()
                .collect(Collectors.summingDouble(Employee::getSalary));

        System.out.println(sum);

        Double avg = emps.stream()
                .collect(Collectors.averagingDouble(Employee::getSalary));

        System.out.println(avg);

        Long count = emps.stream()
                .collect(Collectors.counting());

        System.out.println(count);
        System.out.println("--------------------------------------------");

        DoubleSummaryStatistics dss = emps.stream()
                .collect(Collectors.summarizingDouble(Employee::getSalary));

        System.out.println(dss.getMax());
        System.out.println(dss.getMin());
        System.out.println(dss.getAverage());
        System.out.println(dss.getCount());
        System.out.println(dss.getSum());
    }

    @Test
    public void test5(){
        Map<String, List<Employee>> map = emps.stream()
                .collect(Collectors.groupingBy(Employee::getName));

        System.out.println(map);
    }

    @Test
    public void test6(){
        Map<String, Map<String, List<Employee>>> map = emps.stream()
                .collect(Collectors.groupingBy(Employee::getName, Collectors.groupingBy((e) -> {
                    if(e.getAge() >= 60)
                        return "old";
                    else if(e.getAge() >= 35)
                        return "middle";
                    else
                        return "young";
                })));

        System.out.println(map);
    }

    @Test
    public void test7(){
        Map<Boolean, List<Employee>> map = emps.stream()
                .collect(Collectors.partitioningBy((e) -> e.getSalary() >= 5000));

        System.out.println(map);
    }

    @Test
    public void test8(){
        String str = emps.stream()
                .map(Employee::getName)
                .collect(Collectors.joining("," , "----", "----"));

        System.out.println(str);
    }

    @Test
    public void test9(){
        Optional<Double> sum = emps.stream()
                .map(Employee::getSalary)
                .collect(Collectors.reducing(Double::sum));

        System.out.println(sum.get());
    }
}
