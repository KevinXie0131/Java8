package org.example.optional;

import java.util.Optional;

import org.example.lambda.Employee;
import org.junit.Test;

public class TestOptional {
    /*
     * Optional
     * 	Optional.of(T t)
     * 	Optional.empty()
     * 	Optional.ofNullable(T t)
     * 	isPresent()
     * 	orElse(T t)
     * 	orElseGet(Supplier s)
     * 	map(Function f)
     * 	flatMap(Function mapper)
     */

    @Test
    public void test1(){
        Optional<Employee> op = Optional.of(new Employee());
        Employee emp = op.get();
        System.out.println(emp);
    }

    @Test
    public void test2(){
    	Optional<Employee> op = Optional.ofNullable(null);
		//System.out.println(op.get());
        //System.out.println(op);

        Optional<Employee> op1 = Optional.empty();
        //System.out.println(op1.get());
        System.out.println(op1.isPresent());
    }

    @Test
    public void test3(){
        Optional<Employee> op = Optional.ofNullable(new Employee());

        if(op.isPresent()){
            System.out.println(op.get());
        }

        op = Optional.ofNullable(null);
        Employee emp = op.orElse(new Employee("peter"));
        System.out.println(emp);

        Employee emp2 = op.orElseGet(() -> new Employee("john"));
        System.out.println(emp2);
    }

    @Test
    public void test4(){
        Optional<Employee> op = Optional.of(new Employee(101, "joe", 18, 9999.99));

        Optional<String> op2 = op.map(Employee::getName);
        System.out.println(op2.get());

        Optional<String> op3 = op.flatMap((e) -> Optional.of(e.getName()));
        System.out.println(op3.get());
    }
}
