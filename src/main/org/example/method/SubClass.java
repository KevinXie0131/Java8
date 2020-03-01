package org.example.method;

public class SubClass implements MyInterface{

    @Override
    public String getName() {
        return MyInterface.super.getName();
    }

    public static void main(String[] args) {
        SubClass sc = new SubClass();
        System.out.println(sc.getName());

        MyInterface.show();
    }
}
