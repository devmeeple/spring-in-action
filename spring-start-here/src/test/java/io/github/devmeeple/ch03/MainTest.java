package io.github.devmeeple.ch03;

import io.github.devmeeple.ch03.beans.Parrot;
import io.github.devmeeple.ch03.beans.Person;
import io.github.devmeeple.ch03.config.ProjectConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { ProjectConfig.class })
class MainTest {

    @Autowired
    private ApplicationContext context;

    @DisplayName("스프링 컨텍스트에 등록한 Parrot 인스턴스를 확인한다.")
    @Test
    void testKokoIsInTheSpringContext() {
        Parrot parrot = context.getBean(Parrot.class);

        assertThat(parrot.getName()).isEqualTo("Koko");
    }

    @DisplayName("스프링 컨텍스트에 등록한 Person 인스턴스를 확인한다.")
    @Test
    void testEllaIsInTheSpringContext() {
        Person person = context.getBean(Person.class);

        assertThat(person.getName()).isEqualTo("Ella");
    }

    @DisplayName("Person 인스턴스와 Parrot는 연결되어 있다.")
    @Test
    void testEllaOwnsKoko() {
        Person person = context.getBean(Person.class);

        assertThat(person.getParrot()).isNotNull();
        assertThat(person.getParrot().getName()).isEqualTo("Koko");
    }
}
