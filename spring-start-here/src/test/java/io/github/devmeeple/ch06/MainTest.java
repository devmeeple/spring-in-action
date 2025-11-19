package io.github.devmeeple.ch06;

import io.github.devmeeple.ch06.aspects.LoggingAspect;
import io.github.devmeeple.ch06.aspects.SecurityAspect;
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
    private Logger loggingAspectLogger;
    private Logger securityAspectLogger;

    @Autowired
    private LoggingAspect loggingAspect;

    @Autowired
    private SecurityAspect securityAspect;

    @Autowired
    private CommentService commentService;

    @BeforeEach
    void setUp() {
        this.loggingAspectLogger = mock(Logger.class);
        loggingAspect.setLogger(loggingAspectLogger);

        this.securityAspectLogger = mock(Logger.class);
        securityAspect.setLogger(securityAspectLogger);

        this.serviceLogger = mock(Logger.class);
        commentService.setLogger(serviceLogger);
    }

    @DisplayName("두 개의 Aspect(보안, 로깅)가 publishComment() 메서드 실행을 모두 가로챈다.")
    @Test
    void testAspectInterceptsPublishCommentMethod() {
        Comment comment = new Comment();
        comment.setText("Test comment text");
        comment.setAuthor("Test comment author");

        commentService.publishComment(comment);

        verify(serviceLogger).info("Publishing comment:" + comment.getText());
        verify(securityAspectLogger).info("Security Aspect: Calling the intercepted method");
        verify(loggingAspectLogger).info("Logging Aspect: Calling the intercepted method");
    }
}
