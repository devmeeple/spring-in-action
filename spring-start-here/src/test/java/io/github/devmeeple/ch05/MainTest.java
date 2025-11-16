package io.github.devmeeple.ch05;

import io.github.devmeeple.ch05.config.ProjectConfig;
import io.github.devmeeple.ch05.services.CommentService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { ProjectConfig.class})
class MainTest {

    @Autowired
    private ApplicationContext context;

    @DisplayName("CommentService 빈 조회 시 항상 같은 인스턴스를 반환한다.")
    @Test
    void testCommentServiceIsSingleton() {
        CommentService result1 = context.getBean("commentService", CommentService.class);
        CommentService result2 = context.getBean("commentService", CommentService.class);

        assertThat(result1).isSameAs(result2);
    }
}
