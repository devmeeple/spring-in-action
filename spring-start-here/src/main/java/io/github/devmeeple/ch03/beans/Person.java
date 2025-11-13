package io.github.devmeeple.ch03.beans;

import org.springframework.stereotype.Component;

@Component
public class Person {

    private String name = "Ella";

    private final Parrot parrot;

//    @Autowired
    public Person(Parrot parrot) {
        this.parrot = parrot;
    }

    public String getName() {
        return name;
    }

    public Parrot getParrot() {
        return parrot;
    }
}
