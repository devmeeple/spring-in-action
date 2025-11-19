package io.github.devmeeple.ch06;

import io.github.devmeeple.ch06.config.ProjectConfig;
import io.github.devmeeple.ch06.model.Comment;
import io.github.devmeeple.ch06.services.CommentService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ProjectConfig.class);

        CommentService service = context.getBean(CommentService.class);

        Comment comment = new Comment();
        comment.setText("Demo comment");
        comment.setAuthor("Natasha");

        service.publishComment(comment);
    }
}
