package io.github.devmeeple.ch05;

import io.github.devmeeple.ch05.config.ProjectConfig;
import io.github.devmeeple.ch05.services.CommentService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ProjectConfig.class);

        CommentService commentService1 = context.getBean("commentService", CommentService.class);
        CommentService commentService2 = context.getBean("commentService", CommentService.class);

        boolean result = commentService1 == commentService2;

        System.out.println(result);
    }
}
