package io.github.devmeeple.ch05;

import io.github.devmeeple.ch05.config.ProjectConfig;
import io.github.devmeeple.ch05.services.CommentService;
import io.github.devmeeple.ch05.services.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ProjectConfig.class);

        CommentService commentService = context.getBean(CommentService.class);
        UserService userService = context.getBean(UserService.class);

        boolean result = commentService.getCommentRepository() == userService.getCommentRepository();

        System.out.println(result);
    }
}
