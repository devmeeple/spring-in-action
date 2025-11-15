package io.github.devmeeple.ch04.repositories;

import io.github.devmeeple.ch04.model.Comment;

public interface CommentRepository {

    void storeComment(Comment comment);
}
