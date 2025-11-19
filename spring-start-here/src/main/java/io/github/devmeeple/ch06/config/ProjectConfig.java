package io.github.devmeeple.ch06.config;

import io.github.devmeeple.ch06.aspects.LoggingAspect;
import io.github.devmeeple.ch06.aspects.SecurityAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@EnableAspectJAutoProxy
@ComponentScan(basePackages = "io.github.devmeeple.ch06.services")
@Configuration
public class ProjectConfig {

    @Bean
    public LoggingAspect loggingAspect() {
        return new LoggingAspect();
    }

    @Bean
    public SecurityAspect securityAspect() {
        return new SecurityAspect();
    }
}
