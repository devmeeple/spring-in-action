package io.github.devmeeple.ch05;

import io.github.devmeeple.ch05.config.ProjectConfig;
import io.github.devmeeple.ch05.repositories.CommentRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { ProjectConfig.class})
class MainTest {

    @Autowired
    private ApplicationContext context;

    @DisplayName("프로토타입 스코프 CommentRepository 빈 조회 시 새로운 인스턴스를 반환한다.")
    @Test
    void testCommentRepositoryIsPrototype() {
        CommentRepository result1 = context.getBean("commentRepository", CommentRepository.class);
        CommentRepository result2 = context.getBean("commentRepository", CommentRepository.class);

        assertThat(result1).isNotSameAs(result2);
    }
}
