package io.github.devmeeple.minilog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class EogSpringbootApplication {

    public static void main(String[] args) {
        SpringApplication.run(EogSpringbootApplication.class, args);
    }
}
