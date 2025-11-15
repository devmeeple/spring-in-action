package io.github.devmeeple.ch04;

import io.github.devmeeple.ch04.model.Comment;
import io.github.devmeeple.ch04.proxies.CommentNotificationProxy;
import io.github.devmeeple.ch04.repositories.CommentRepository;
import io.github.devmeeple.ch04.services.CommentService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class MainTest {

    @DisplayName("댓글을 작성하면 저장 및 알림 기능을 호출한다.")
    @Test
    void testCommentService() {
        Comment comment = mock(Comment.class);
        CommentRepository commentRepository = mock(CommentRepository.class);
        CommentNotificationProxy commentNotificationProxy = mock(CommentNotificationProxy.class);
        CommentService commentService = new CommentService(commentRepository, commentNotificationProxy);

        commentService.publishComment(comment);

        verify(commentRepository).storeComment(comment);
        verify(commentNotificationProxy).sendComment(comment);
    }
}
