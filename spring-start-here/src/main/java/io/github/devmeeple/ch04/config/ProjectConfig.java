package io.github.devmeeple.ch04.config;

import io.github.devmeeple.ch04.proxies.CommentNotificationProxy;
import io.github.devmeeple.ch04.proxies.EmailNotificationProxy;
import io.github.devmeeple.ch04.repositories.CommentRepository;
import io.github.devmeeple.ch04.repositories.DBCommentRepository;
import io.github.devmeeple.ch04.services.CommentService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProjectConfig {

    @Bean
    public CommentRepository commentRepository() {
        return new DBCommentRepository();
    }

    @Bean
    public CommentNotificationProxy commentNotificationProxy() {
        return new EmailNotificationProxy();
    }

    @Bean
    public CommentService commentService(
            CommentRepository commentRepository,
            CommentNotificationProxy commentNotificationProxy) {
        return new CommentService(
                commentRepository,
                commentNotificationProxy
        );
    }
}
