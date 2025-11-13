package io.github.devmeeple.ch02;

import io.github.devmeeple.ch02.config.ProjectConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ProjectConfig.class);

        Parrot parrot = context.getBean(Parrot.class);

        System.out.println(parrot);
        System.out.println(parrot.getName());
    }
}
