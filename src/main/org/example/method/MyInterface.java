package org.example.method;

public interface MyInterface {

    default String getName(){
        return "hello";
    }

    public static void show(){
        System.out.println("interface");
    }

}