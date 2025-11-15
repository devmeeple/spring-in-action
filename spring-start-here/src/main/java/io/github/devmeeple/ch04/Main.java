package io.github.devmeeple.ch04;

import io.github.devmeeple.ch04.model.Comment;
import io.github.devmeeple.ch04.proxies.EmailNotificationProxy;
import io.github.devmeeple.ch04.repositories.DBCommentRepository;
import io.github.devmeeple.ch04.services.CommentService;

public class Main {

    public static void main(String[] args) {
        DBCommentRepository commentRepository = new DBCommentRepository();
        EmailNotificationProxy commentNotificationProxy = new EmailNotificationProxy();
        CommentService commentService = new CommentService(commentRepository, commentNotificationProxy);

        Comment comment = new Comment();
        comment.setAuthor("Laurentiu");
        comment.setText("Demo comment");

        commentService.publishComment(comment);
    }
}
