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

    @DisplayName("스프링 컨텍스트에 Bean Parrot을 추가한다.")
    @Test
    void testKokoIsInTheSpringContext() {
        Parrot result = context.getBean(Parrot.class);

        assertThat(result.getName()).isEqualTo("KoKo");
    }

    @DisplayName("스프링 컨텍스트에 Bean 문자열 Hello를 추가한다.")
    @Test
    void testHelloIsInTheSpringContext() {
        String result = context.getBean(String.class);

        assertThat(result).isEqualTo("Hello");
    }

    @DisplayName("스프링 컨텍스트에 정수 값 10을 추가한다.")
    @Test
    void test10IsInTheSpringContext() {
        Integer result = context.getBean(Integer.class);

        assertThat(result).isEqualTo(10);
    }
}
