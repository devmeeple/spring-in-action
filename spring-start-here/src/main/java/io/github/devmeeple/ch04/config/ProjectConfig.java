package io.github.devmeeple.ch04.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@ComponentScan(basePackages = {
        "io.github.devmeeple.ch04.proxies",
        "io.github.devmeeple.ch04.services",
        "io.github.devmeeple.ch04.repositories"
})
@Configuration
public class ProjectConfig {
}
