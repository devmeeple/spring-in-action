package io.github.devmeeple.ch02.config;

import io.github.devmeeple.ch02.Parrot;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProjectConfig {

    @Bean
    Parrot parrot() {
        Parrot parrot = new Parrot();
        parrot.setName("KoKo");
        return parrot;
    }

    @Bean
    String hello() {
        return "Hello";
    }

    @Bean
    Integer ten() {
        return 10;
    }
}
