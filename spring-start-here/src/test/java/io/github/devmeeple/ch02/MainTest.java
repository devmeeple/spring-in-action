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

    @DisplayName("parrot1 인스턴스의 이름은 Koko다.")
    @Test
    void testParrot1HasTheNameKoko() {
        Parrot result = context.getBean("parrot1", Parrot.class);

        assertThat(result.getName()).isEqualTo("KoKo");
    }

    @DisplayName("miki 인스턴스의 이름은 Miki다.")
    @Test
    void testParrotMikiHasTheNameMiki() {
        Parrot result = context.getBean("miki", Parrot.class);

        assertThat(result.getName()).isEqualTo("Miki");
    }

    @DisplayName("parrot3 인스턴스의 이름은 Riki다.")
    @Test
    void testParrot3HasTheNameRiki() {
        Parrot result = context.getBean("parrot3", Parrot.class);

        assertThat(result.getName()).isEqualTo("Riki");
    }
}
