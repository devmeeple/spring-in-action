package io.github.devmeeple.ch04.proxies;

import io.github.devmeeple.ch04.model.Comment;

public interface CommentNotificationProxy {

    void sendComment(Comment comment);
}
