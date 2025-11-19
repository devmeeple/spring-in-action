package io.github.devmeeple.ch06;

import io.github.devmeeple.ch06.config.ProjectConfig;
import io.github.devmeeple.ch06.model.Comment;
import io.github.devmeeple.ch06.services.CommentService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { ProjectConfig.class})
class MainTest {

    @Autowired
    private CommentService commentService;

    @DisplayName("Aspect를 통해 publishComment() 메서드 실행을 가로채고 반환값을 변경한다.")
    @Test
    void testAspectInterceptsPublishCommentMethod() {
        Comment comment = new Comment();
        comment.setText("Test comment text");
        comment.setAuthor("Test comment author");

        String result = commentService.publishComment(comment);

        assertThat(result).isEqualTo("FAILED");
    }
}
