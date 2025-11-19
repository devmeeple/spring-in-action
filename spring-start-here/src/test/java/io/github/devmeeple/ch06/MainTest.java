package io.github.devmeeple.ch06;

import io.github.devmeeple.ch06.aspects.LoggingAspect;
import io.github.devmeeple.ch06.config.ProjectConfig;
import io.github.devmeeple.ch06.model.Comment;
import io.github.devmeeple.ch06.services.CommentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.logging.Logger;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { ProjectConfig.class})
class MainTest {

    private Logger serviceLogger;
    private Logger aspectLogger;

    @Autowired
    private LoggingAspect loggingAspect;

    @Autowired
    private CommentService commentService;

    @BeforeEach
    void setUp() {
        this.aspectLogger = mock(Logger.class);
        loggingAspect.setLogger(aspectLogger);

        this.serviceLogger = mock(Logger.class);
        commentService.setLogger(serviceLogger);
    }

    @DisplayName("Aspect가 publishComment() 메서드 실행을 가로채고 변경한다.")
    @Test
    void testAspectInterceptsPublishCommentMethod() {
        Comment comment = new Comment();
        comment.setText("Test comment text");
        comment.setAuthor("Test comment author");

        commentService.publishComment(comment);

        verify(serviceLogger).info("Publishing comment:" + comment.getText());
        verify(aspectLogger).info("Method executed and returned SUCCESS");
    }
}
