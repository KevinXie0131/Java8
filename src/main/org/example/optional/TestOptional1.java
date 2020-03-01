package org.example.optional;

import java.util.Optional;

import org.junit.Test;

public class TestOptional1 {

    @Test
    public void test(){
        Optional<Cup> cup1 = Optional.ofNullable(new Cup("cup1"));

        Optional<Box> op = Optional.ofNullable(new Box(cup1));
        String name = getCup(op);
        System.out.println(name);
    }

    public String getCup(Optional<Box> box){
        return box.orElse(new Box())
                .getCup()
                .orElse(new Cup("cup2"))
                .getName();
    }
}
