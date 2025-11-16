package io.github.devmeeple.ch05.config;

import io.github.devmeeple.ch05.services.CommentService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProjectConfig {

    @Bean
    public CommentService commentService() {
        return new CommentService();
    }
}
