package org.example.optional;

import java.util.Optional;

public class Box {

    private Optional<Cup> cup = Optional.empty();

    private Cup cup0;

    public Optional<Cup> getCup0(){
        return Optional.of(cup0);
    }

    public Box() {
    }

    public Box(Optional<Cup> cup) {
        this.cup = cup;
    }

    public Optional<Cup> getCup() {
        return cup;
    }

    public void setCup(Optional<Cup> cup) {
        this.cup = cup;
    }

    @Override
    public String toString() {
        return "Box{" +
                "cup=" + cup +
                '}';
    }
}
