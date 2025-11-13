package io.github.devmeeple.ch02;

import io.github.devmeeple.ch02.config.ProjectConfig;
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

    @DisplayName("기본으로 설정한 빈, parrot2의 이름은 Miki다.")
    @Test
    void testParrot2IsPrimary() {
        Parrot result = context.getBean(Parrot.class);

        assertThat(result.getName()).isEqualTo("Miki");
    }
}
