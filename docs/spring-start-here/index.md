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
