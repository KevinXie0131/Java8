package org.example.optional;

public class Cup {

    private String name;

    public Cup() {
    }

    public Cup(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Cup{" +
                "name='" + name + '\'' +
                '}';
    }

}
