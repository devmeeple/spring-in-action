# 스프링 교과서

## 2. 스프링 컨텍스트: 빈 정의

- 스프링 컨텍스트(ApplicationContext)는 애플리케이션에 사용할 객체(Bean)를 생성하고 관리하는 핵심 컨테이너다.
- 스프링 컨텍스트에 등록하지 않은 객체는 스프링이 관리하지 않는다.
    - 의존성 주입(Dependency Injection), 생명주기 관리 등 스프링의 지원을 받으려면 반드시 등록해야 한다.
- 스프링 컨텍스트에 등록한 객체를 스프링 빈(Spring Bean)이라고 한다.

### 스프링 빈 등록 방법

- 스프링 빈을 추가하는 방법은 크게 3가지다.

**@Bean을 사용한 수동 등록**

- 구성(`@Configuration`)클래스를 정의하고 개발자가 직접 Bean을 정의한다.
    - XML 기반 설정도 가능하지만, 최근 구성 클래스는 Java 기반(Java-based configuration)을 주로 사용한다.
- 외부 라이브러리, 제 3자 코드 등 개발자가 직접 어노테이션을 추가할 수 없는 클래스를 Bean으로 등록할 때 유용하다.

**스트레오타입 어노테이션(Stereotype Annotation)을 사용한 자동 등록**

- 클래스에 `@Component`계열 어노테이션을 추가하고 `@ComponentScan`으로 탐색 경로를 지정한다.
    - `@Component`
    - `@Controller`, `@Service`, `@Repository`등이 해당된다.
- Spring Boot 환경에서는 별도 설정 없이도 `@SpringBootApplication` 내부에 포함된 `@ComponentScan` 덕분에 자동으로 빈 탐색이 이뤄진다.

**프로그래밍 방식(Programmatic Registration)**

- `registerBean()` 메서드를 사용하면 컨텍스트에 Bean을 추가하는 로직을 재정의하여 구현할 수 있다.
- 실무에서 자주 사용하지 않는다.

## 3. 스프링 컨텍스트: 빈 작성

- 애플리케이션의 대부분 기능은 단일 객체가 모든 작업을 처리하지 않고, 여러 객체의 협력으로 이뤄진다.
- 객체가 자신의 책임을 수행하려면 종종 다른 객체에게 작업을 위임하고, 자연스럽게 의존 관계(Dependency)가 발생한다.
- 스프링은 의존 관계를 직접 생성(`new`)하지 않고 스프링 컨테이너(ApplicationContext)가 대신 관리하도록 지원한다.

### 스프링 빈 의존 관계 설정하기

- 빈을 생성하는 메서드에서 다른 빈을 생성하는 `@Bean` 메서드를 직접 참조
- 매개변수 타입의 빈을 컨텍스트에서 검색하고 해당 빈을 매개변수 값으로 전달
- `@Autowired`

**@Autowired**

- 필드 주입: 테스트 코드, 개념 증명(PoC)에 주로 사용
- 생성자 주입: 실제 코드에 가장 많이 사용
- setter: 프로덕션 수준의 코드에서는 자주 사용하지 않음

### 순환 의존성

> A가 B를 필요로 하고, B는 다시 A를 필요로 한다.

- 순환 의존성이 있는 빈은 생성할 수 없고, 예외를 발생시키며 실행은 실패한다.
- 빈을 구성할 때 순환 의존은 반드시 피해야 한다.

### 동일한 타입의 빈을 주입할 때

- `@Primary`를 사용하면 동일한 타입의 빈 중 기본으로 사용할 빈을 지정한다.
- `@Qualifier`를 사용하면 빈 이름을 지정하고 이름으로 주입한다.

## 4. 스프링 컨텍스트: 추상화

### 인터페이스 도입

**직접 결합의 문제**

```java
public class UserService {
    private MySqlRepository repository = new MySqlRepository();
    
    public void addUser(User user) {
        repository.save(user);
    }
}
```

- 직접 결합은 객체가 다른 객체의 구체적인 클래스를 직접 참조하고 생성하여 사용하는 행동을 의미한다.
- 클래스의 내부 구현이 변경되면 직접 사용하는 모든 클래스도 수정해야 한다.
    - 변경에 취약하며 유지보수를 어렵게 만든다.
- 실제 구현 객체에 강하게 의존하기 때문에 테스트가 어렵다.

**인터페이스 도입**

```java
public interface UserRepository {
    void save(User user);
}

public class MySqlRepository implements UserRepository {
    
}

public class UserService {
    private UserRepository repository;
    
    public UserService(UserRepository repository) {
        this.repository = repository;
    }
    
    public void addUser(User user) {
        repository.save(user);
    }
}
```

- 두 객체 사이에 인터페이스를 두어 다른 객체의 구체적인 구현이 아닌 역할에만 의존하도록 한다.
- 의존 역전 원칙(DIP, Dependency Inversion Principle)은 결합도를 낮추고 유연성을 극대화하는 핵심 원칙이다.

### POJO(Plain Old Java Object)

- POJO는 특정 기술이나 프레임워크에 종속되지 않는 단순 객체를 의미한다.
- 외부에 종속적인 라이브러리나 기술을 상속하거나 직접적으로 구현하지 않는다.
- 일반적으로 속성(fields)과 메서드(methods)(Getter/Setter, 비즈니스 로직)만 포함한다.

### 스트레오타입 어노테이션 사용 위치

- 스프링이 관리하는 Bean은 실제로 동작하는 객체(인스턴스)여야 한다.
- 인터페이스에 어노테이션을 사용해도 스프링 컨테이너가 관리할 수 있는 구체적인 인스턴스가 생성되지 않는다. 따라서 구체적인 구현 클래스에 어노테이션을 사용한다.

### DI(Dependency Injection)

- DI는 객체 간 의존 관계를 객체 자신이 아닌 외부(스프링 컨테이너)에서 주입하는 디자인 패턴이다.
- DI는 객체 지향 설계의 핵심 목표인 느슨한 결합(Loose Coupling)과 높은 응집도(High Cohesion)를 달성하도록 지원한다.
- 스프링은 개발의 복잡성을 낮추기 위해 DI를 핵심 원리로 채택했다.

### 구체적인 스트레오타입 어노테이션 사용

- `@Component`는 가장 기본적인 Bean 등록 어노테이션이다. 스프링은 세분화한 구체적인 어노테이션을 제공한다.
- **구체적인 어노테이션**
    - 명확한 역할 부여 및 가독성
    - 특화된 기능 제공
    - 선택적 스캔 및 AOP 적용의 용이함
