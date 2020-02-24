package org.example.lambda;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import org.junit.Test;

public class TestLambda2 {

    @Test
    public void test1(){
        int num = 0;//jdk 1.7  final

        Runnable r = new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello World!" + num);
            }
        };
        r.run();

        System.out.println("-------------------------------");

        Runnable r1 = () -> System.out.println("Hello Lambda!" );
        r1.run();
    }

    @Test
    public void test2(){
        Consumer<String> con = x -> System.out.println(x);
        con.accept("hello lambda！");
    }

    @Test
    public void test3(){
        Comparator<Integer> com = (x, y) -> {
            System.out.println("functional interface");
            return Integer.compare(x, y);
        };
    }

    @Test
    public void test4(){
        Comparator<Integer> com = (x, y) -> Integer.compare(x, y);
    }


    //需求：对一个数进行运算
    @Test
    public void test6(){
        Integer num = operation(100, (x) -> x * x);
        System.out.println(num);

        System.out.println(operation(200, (y) -> y + 200));
    }

    public Integer operation(Integer num, MyFunction mf){
        return mf.getValue(num);
    }
}
