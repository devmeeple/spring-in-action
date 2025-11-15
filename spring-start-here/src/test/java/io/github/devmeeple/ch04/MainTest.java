package io.github.devmeeple.ch04;

import io.github.devmeeple.ch04.model.Comment;
import io.github.devmeeple.ch04.proxies.CommentNotificationProxy;
import io.github.devmeeple.ch04.repositories.CommentRepository;
import io.github.devmeeple.ch04.services.CommentService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class MainTest {

    @Mock
    private CommentRepository commentRepository;

    @Mock
    private CommentNotificationProxy commentNotificationProxy;

    @InjectMocks
    private CommentService commentService;

    @DisplayName("댓글을 작성하면 저장 및 알림 기능을 호출한다.")
    @Test
    void testCommentService() {
        Comment comment = new Comment();

        commentService.publishComment(comment);

        verify(commentRepository).storeComment(comment);
        verify(commentNotificationProxy).sendComment(comment);
    }
}
