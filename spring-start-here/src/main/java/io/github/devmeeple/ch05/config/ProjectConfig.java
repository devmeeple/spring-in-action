package io.github.devmeeple.ch05.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@ComponentScan(basePackages = {
        "io.github.devmeeple.ch05.services",
})
@Configuration
public class ProjectConfig {
}
