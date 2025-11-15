package io.github.devmeeple.ch04.services;

import io.github.devmeeple.ch04.model.Comment;
import io.github.devmeeple.ch04.proxies.CommentNotificationProxy;
import io.github.devmeeple.ch04.repositories.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private CommentNotificationProxy commentNotificationProxy;

    public void publishComment(Comment comment) {
        commentRepository.storeComment(comment);
        commentNotificationProxy.sendComment(comment);
    }
}
