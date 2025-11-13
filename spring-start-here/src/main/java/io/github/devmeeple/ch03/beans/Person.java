package io.github.devmeeple.ch03.beans;

import org.springframework.stereotype.Component;

@Component
public class Person {

    private String name = "Ella";
    private final Parrot parrot;

    public Person(Parrot parrot2) {
        this.parrot = parrot2;
    }
    public String getName() {
        return name;
    }

    public Parrot getParrot() {
        return parrot;
    }

    public void setName(String name) {
        this.name = name;
    }
}
