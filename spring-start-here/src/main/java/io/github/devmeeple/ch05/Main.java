package io.github.devmeeple.ch05;

import io.github.devmeeple.ch05.config.ProjectConfig;
import io.github.devmeeple.ch05.model.Comment;
import io.github.devmeeple.ch05.services.CommentService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ProjectConfig.class);

        CommentService commentService = context.getBean(CommentService.class);

        commentService.sendComment(new Comment());
        commentService.sendComment(new Comment());
    }
}
