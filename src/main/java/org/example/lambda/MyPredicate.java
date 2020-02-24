package org.example.lambda;

@FunctionalInterface
public interface MyPredicate<T> {

    public boolean test(T t);

}
