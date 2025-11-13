package io.github.devmeeple.ch03.config;

import io.github.devmeeple.ch03.beans.Parrot;
import io.github.devmeeple.ch03.beans.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProjectConfig {

    @Bean
    public Parrot parrot1() {
        Parrot parrot = new Parrot();
        parrot.setName("Koko");
        return parrot;
    }

    @Bean
    public Parrot parrot2() {
        Parrot parrot = new Parrot();
        parrot.setName("Miki");
        return parrot;
    }

    @Bean
    public Person person(Parrot parrot2) {
        Person person = new Person();
        person.setName("Ella");
        person.setParrot(parrot2);
        return person;
    }

    /*
    public Person person(@Qualifier("parrot2") Parrot parrot) {
        Person p = new Person();
        p.setName("Ella");
        p.setParrot(parrot);
        return p;
    }
    */
}
