package io.github.devmeeple.ch04.proxies;

import io.github.devmeeple.ch04.model.Comment;

public class EmailNotificationProxy implements CommentNotificationProxy {

    @Override
    public void sendComment(Comment comment) {
        System.out.println("Sending notification for comment: " + comment.getText());
    }
}
