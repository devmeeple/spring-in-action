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

## 5. 스프링 컨텍스트: 빈의 스코프 및 수명 주기

- 스프링 컨텍스트는 빈(Bean) 스코프를 통해 객체 인스턴스를 관리하는 방법을 정의한다. 
    - 주요 스코프는 싱글톤(Singleton)과 프로토타입(Prototype)이다.

### 빈 스코프(Bean Scopes)

**싱글톤(Singleton)**

- 스프링의 기본 스코프, 스프링 컨텍스트 내에서 하나의 유일한 객체 인스턴스만 생성하고 관리한다.
- 컨텍스트 내에서 빈 이름을 사용하여 참조하는 모든 위치에서 동일한 인스턴스를 얻는다.
- 여러 스레드가 동일한 인스턴스에 접근하기 때문에, 불변(immutable)하게 만들기를 권장한다.
    - 주로 생성자 주입을 통해 만든다.
- 변경 가능한 속성이 필요할 때, 개발자가 직접 스레드 동기화를 처리해야 경쟁 상태(Race Condition)를 방지할 수 있다.
- 스프링 싱글톤은 스프링 컨테이너 내에서만 싱글톤임을 보장한다.

**프로토타입(Prototype)**

- 빈을 참조할 때마다 해당 타입의 새로운 객체 인스턴스를 생성하는 스코프다.
- 스프링은 객체 타입에만 집중하고, 요청이 들어올 때마다 새로운 인스턴스를 만든다.
- 주로 변경 가능하고, 각 사용 시점마다 고유한 인스턴스가 필요한 경우에만 사용한다.
- 프로토타입 빈을 싱글톤 빈에 주입할 경우, 싱글톤 빈이 생성되는 시점에 단 한 번만 프로토타입 인스턴스가 생성되어 주입된다. 이후 싱글톤 빈을 사용할 때마다 새로운 프로토타입 인스턴스를 얻지 못한다.

### 빈 인스턴스 생성 시점

**즉시 인스턴스 생성(Eager Initialization)**

- 스프링의 기본 동작 방식, 스프링 컨텍스트가 만들어질 때 즉시 빈 인스턴스를 생성한다.

**지연 인스턴스 생성(Lazy Initialization)**

- 빈이 실제로 참조되는 시점에 인스턴스를 생성한다.
- 성능 최적화를 위해 사용할 수 있지만, 애플리케이션 설계의 잠재적 문제가 있다는 신호일 수 있다.
    - 근본적인 원인을 해결되지 않고 `@Lazy`를 사용하여 문제가 덮어지는 상황을 의미

## 6. 스프링 AOP로 애스펙트 사용

### 애스펙트(관점) 지향 프로그래밍(AOP, Aspect-Oriented Programming)

- 애스펙트(Aspect)는 프레임워크가 메서드의 호출을 가로채고 실행을 변경할 수 있는 방법이다.
- 애스펙트는 비즈니스로직과 횡단 관심사(Cross-cutting Concerns)를 분리하여 개발자가 기능 구현에 집중할 수 있도록 돕는다.
    - 반복적으로 사용하는 공통 기능(횡단 관심사)을 처리한다. (트랜잭션, 로깅)
    - 로깅 방식, 보안 정책을 변경할 때 처리가 쉽다.
- AOP는 프록시(Proxy) 패턴을 사용한다.
    - 빈이 애스펙트의 PointCut 대상이라면 실제 객체 대신 프록시 객체를 동적으로 생성한다.
    - 클라이언트는 실제 객체가 아닌 프록시 객체의 참조를 받는다.
    - 클라이언트가 서비스 메서드를 호출하면 프록시 객체가 먼저 가로챈다.
    - 프록시는 가로챈 호출 전후로 애스펙트에 정의된 Advice 코드(로깅, 트랜잭션)를 실행한다.
    - Advice 실행을 완료 후, 프록시는 실제 대상 객체로 위임하여 비즈니스로직을 실행한다.

**핵심 용어**

- Aspect: `@Aspect`, 관심사를 정의한 클래스, 횡단 관심사를 모듈화 한 단위
- Join Point: Advice를 적용할 수 있는 지점(메서드 실행, 필드 접근, 예외 처리 등), Advice가 실행되는 실제 위치
- Advice: 특정 조인 포인트에서 실행되어야 할 코드(`@Around`, `@Before`, `@After`), 무엇을 할지 정의
- Pointcut: Advice를 적용할 Join Point의 규칙/집합, 어디에 적용할지(특정 패키지의 모든 메서드)

**사용법**

```kotlin
// AOP(Aspect-Oriented Programming)
implementation("org.springframework.boot:spring-boot-starter-aop")
```

- 스프링에서 AOP를 사용하려면 의존성을 추가해야 한다.

**위험성**

- 애스펙트는 강력하지만, 오용될 경우 코드의 복잡성을 증가시키고 디버깅을 어렵게 만든다.
- AOP는 코드가 명시적으로 호출되지 않아도 실행된다. 애플리케이션의 실제 동작 흐름을 추적하기 어렵게 만든다.
- 잘못된 범위 설정은 의도치 않은 메서드를 가로채거나 성능 저하가 발생할 수 있다.

**실제 사례**

- `@Transactional`: 메서드 호출 전후에 트랜잭션 시작, 커밋, 롤백 로직을 자동으로 주입한다.
- `@PreAuthorize`,` @PostAuthorize`: 스프링 시큐리티에 사용한다. 메서드 실행 전후에 권한 검사 로직을 삽입하여 접근을 제어한다.

**참고 자료**

- [@AspectJ support](https://docs.spring.io/spring-framework/reference/core/aop/ataspectj.html)

### 로깅을 사용하는 이유

- 애플리케이션 운영 환경에서 발생할 수 있는 복잡한 문제를 체계적으로 관리하고 분석할 수 있게 지원한다.
- `System.out`은 메시지를 단순히 출력할 뿐, 메시지의 중요도를 구분할 수 없다. 반면, 로깅 프레임워크는 메시지에 레벨(Level)을 부여하여 정보의 중요도를 분류하고 관리할 수 있다.
- 로깅 메시지에 표준화된 형식을 적용하여 가독성을 높이고 분석을 용이하게 한다.
- 로깅 프레임워크는 성능 최적화가 되어 있다.

### 사용자 정의 어노테이션

```java
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ToLog {
}
```

- `@Retention`: 자바가 실행 중 어노테이션 정보를 읽고 특정 로직을 수행하거나 AOP처럼 기능을 가로채려면, 리플렉션을 사용할 수 있도록 `RUNTIME`으로 설정해야 한다.
- `@Target`: 어노테이션을 클래스, 메서드, 필드 등 어떤 요소(Element)에 붙일 수 있는지 대상을 지정한다.

## 7. 스프링 부트와 스프링 MVC 이해

### 웹 애플리케이션의 특징과 구현 방식

- 웹 애플리케이션은 여러 사용자가 동시에 접속하여 요청을 보낼 수 있다. 서블릿 컨테이너는 각 요청마다 별도의 스레드를 할당하여 동시성을 처리하고 안정성을 확보한다.
- **구현 방식**
    - **서버사이드 렌더링(SSR)**: 서버가 클라이언트 요청에 응답하는 완성된 HTML 뷰를 제공(JSP, Thymeleaf, Freemaker)
    - **프론트엔드-백엔드 분리**(API 방식): 서버는 JSON, XML 등의 데이터 형식을 전송하고 클라이언트가 데이터를 받아 화면을 렌더링(RESTful API 구현)

### 서블릿 컨테이너(Servlet Container)

- 서블릿 컨테이너는 클라이언트의 HTTP 요청과 응답을 자바 객체(`HttpServletRequest`, `HttpServletResponse`)로 변환하고 관리하는 표준 규약이다.
- 톰캣(Tomcat)은 서블릿 컨테이너 규약을 실제로 구현한 WAS(Web Application Server)중 하나다.
- 서블릿은 웹 애플리케이션 로직의 최초 진입점이다. 컨테이너에 의해 생명주기(생성, 서비스, 소멸)를 관리한다.
- 스프링은 개발자가 직접 서블릿을 정의하고 등록하는 대신 빈 관리를 활용하여 서블릿 객체를 서블릿 컨테이너에 등록한다.

### 디스패처 서블릿(Dispatcher Servlet)과 Spring MVC

- 디스패처 서블릿은 프론트 컨트롤러 패턴을 구현한 Spring MVC 핵심 구성요소다.
- **프론트 컨트롤러(Front Controller)**
    - 단일 진입점: 모든 클라이언트 요청을 받는 유일한 서블릿이다. 기존 서블릿 방식처럼 요청마다 서블릿을 만들 필요 없이, 모든 요청을 디스패처 서블릿이 먼저 받아 처리한다.
    - 디스패처 서블릿은 요청을 받고, 처리 로직을 담당하는 여러 핸들러(Controller)에게 작업을 위임한다.

**디스패처 서블릿의 요청 처리 과정**

1. 요청 수신: 서블릿 컨테이너(톰캣)에게 `HttpServletRequest`를 받는다.
2. 핸들러 매핑(Handler Mapping): 요청 URI를 분석하고 요청을 실제로 처리할 컨트롤러를 찾는다.
3. 핸들러 실행: 컨트롤러를 실행한다. 비즈니스 로직을 수행하고 Model과 View 이름을 반환한다.
4. 뷰 리졸버(View Resolver): 컨트롤러가 반환한 View 이름을 기반으로 실제 뷰 템플릿을 View 객체로 변환한다.
5. 뷰 렌더링: Model 데이터를 사용하여 최종 HTML 응답 본문을 생성한다.
6. 응답 전송: 디스패처 서블릿이 최종 응답을 `HttpServletResponse`에 담아 서블릿 컨테이너를 통해 클라이언트로 전송한다.

### 스프링 부트(Spring Boot)

- 스프링 부트는 스프링을 기반으로 실무 환경에 사용 가능한 독립 실행형 애플리케이션을 복잡한 고민 없이 작성할 수 있도록 돕는 스프링 프로젝트다.
- **핵심 기능**
  - 의존성 관리(Dependency Management): spring-boot-starter 사용
  - 배포(Deployment) 간소화: 실행 가능한 `jar` 안에 WAS가 포함되어 있어 `jar` 실행으로 간편하게 배포 가능
  - 자동 설정(Auto Configuration): 구성보다 관례(COC, Convention Over Configuration) 원칙을 적용하여 필요한 기능에 대한 기본 구성을 제공

## 8. 스프링 부트와 스프링 MVC를 이용한 웹 앱 구현

### 동적 페이지

- 동적 페이지는 클라이언트 요청에 따라 다른 콘텐츠를 표시한다. 최근에는 정적 페이지보다 동적 페이지를 주로 사용한다.
- 동적 페이지는 표시할 정보를 알기 위해 컨트롤러에서 변수 데이터를 가져온다.
- 스프링은 템플릿 엔진(Template Engine)을 사용하여 쉽게 동적 페이지를 표현한다.
- **템플릿 엔진**
    - 타임리프(Thymeleaf)
    - 머스태치(Mustache)
    - 프리마커(Freemarker)
    - 자바 서버 페이지(JSP)

### HTTP 요청 데이터 처리 방식

**요청 매개변수(Request Parameter)**

```text
/path?key1=value&key2=value2
```

- URL의 쿼리 문자열(Query String)또는 폼 데이터(Form Data)를 통해 `키=값` 형태로 데이터를 전송한다.
- 검색어, 필터 조건, 페이지 번호 등 소량 데이터 처리에 적합하다.
- 기본값은 필수(`required=true`)다. 필수가 아니라면 `@RequestParam(required=false)`로 설정한다.
- 스프링은 `@RequestParam`을 사용하여 처리한다.

**경로 변수(Path Variable)**

```text
/path/{variable}
```

- 데이터를 URL 경로의 일부처럼 구조화하여 전송한다. 특정 리소스(자원)를 식별하는데 주로 사용한다.
- 필수로 식별하는 값에 적합하다.
- 검색 엔진은 구조화된 경로를 안정적인 리소스로 판단한다. 따라서 색인화에 유리하다.
- 스프링은 `@PathVariable`를 사용하여 처리한다.

**요청 헤더(Request Header)**

- 요청에 대한 부가 정보(인증 토큰, 사용자 환경 정보)를 전송, URI에 표시하지 않는다. 주로 시스템 정보 전송에 사용한다.

**요청 본문(Request Body)**

- JSON이나 XML 형태의 대량 데이터 전송에 사용한다. `@RequestBody`를 사용하여 처리한다.

### Model

- `Model` 객체는 Controller가 처리한 결과 데이터나 화면에 표시할 데이터를 담아 전송하는 통로 역할이다.

### 데이터 바인딩: @ModelAttribute

- 복잡한 요청 데이터를 객체로 자동 변환해 주는 기능이다.
- 클라이언트가 전송한 HTTP 요청 파라미터의 값을 개발자가 정의한 객체(모델 클래스)의 필드에 자동으로 매칭하고 채워준다.
- 스프링은 요청 파라미터 이름과 객체의 필드 이름이 일치하면 자동으로 값을 주입하여 객체의 인스턴스를 생성하고 메서드에 전달한다.
- 스프링이 인스턴스를 만들 수 있도록 모델 클래스는 기본 생성자가 반드시 필요하다.
- [@ModelAttribute](https://docs.spring.io/spring-framework/reference/web/webmvc/mvc-controller/ann-methods/modelattrib-method-args.html)

### HTTP 메서드의 목적과 활용

- HTTP 메서드는 클라이언트가 서버에게 의도를 전달하는 동사다.

## 9. 스프링 웹 스코프

- 요청 스코프, 세션 스코프, 애플리케이션 스코프는 웹 애플리케이션에서 사용할 수 있는 웹 스코프다.

### 요청 스코프(Request Scope)

- HTTP 요청 별로 인스턴스를 생성한다. 요청이 종료되면 즉시 사라진다.
- 요청별로 인스턴스가 독립적으로 생성되어 동시성 문제에 자유롭다.
- 인스턴스 속성에 데이터를 저장해도 해당 요청 내에서만 유지되어 안전한다.
- `@PostConstruct` 또는 생성자에 복잡하거나 시간이 오래 걸리는 로직을 사용하면 안된다. 성능 저하가 발생한다.

### 세션 스코프(Session Scope)

- HTTP 세션이 시작될 때 생성되어 세션이 만료될 때까지 유지한다. 동일한 클라이언트(세션)의 모든 요청이 해당 인스턴스를 공유한다.

**동시성 문제**

- 동일한 클라이언트에서 발생하는 모든 요청에 인스턴스를 공유한다.
- 동일 세션에서 여러 요청이 동시에 발생하며 빈의 상태를 변경하려 할 때 경쟁 상태(Race Condition)가 발생할 수 있다. 스프링이 자동으로 해결해주지 않기 때문에, 직접 동기화 처리가 필요하다.
- 로그인 정보, 쇼핑처럼 세션 단위로 유지되어야 하는 상태 데이터를 저장할 때 사용한다.

### 애플리케이션 스코프(Application Scope)

- 웹 애플리케이션이 시작될 때 생성되어 애플리케이션이 종료될 때까지 단 하나의 인스턴스를 사용한다.
- 사용을 권장하지 않는다. 
 - 인스턴스에 상태를 저장하면 모든 사용자가 데이터를 공유하게 되어 요청의 독립성을 해친다.
 - 서버 전체에서 공유하는 데이터라면 데이터베이스나 캐시(Redis)등의 영속성 계층을 사용하면 된다.

## 10. REST 서비스 구현

### REST 서비스

- REST(Representational State Transfer)는 현대 웹 서비스 개발에 가장 널리 사용되는 아키텍처 스타일이다.
- REST 서비스는 웹 기본 프로토콜 HTTP의 장점을 최대 활용하는 아키텍처 스타일을 따른다. 
- 자원을 URI(Uniform Resource Identifier)로 식별하고 조작을 HTTP Method(GET, POST, PUT, DELETE 등)를 통해 수행한다.
- HTTP 표준을 따르므로 별도의 복잡한 프로토콜 없이 웹 브라우저, 모바일 웹, 다양한 서버 기술 등 어디서든 쉽게 접근, 사용할 수 있다.
- Stateless(무상태) 원칙을 따른다. 서버는 클라이언트의 상태를 저장하지 않아, 요청을 처리하는 서버를 쉽게 늘릴 수 있어 서비스 확장에 유리하다.

### HTTP 응답 처리

- REST 서비스는 요청 처리 후 클라이언트에게 HTTP 응답을 반환한다. 응답은 응답 헤더, 응답 본문, HTTP 상태 코드(Status Code)로 구성한다.
- `ResposeEntity`는 HTTP 응답 전체를 제어하는 클래스다. 특정 상태 코드를 명시해야 할 때, 또는 응답 헤더에 추가 정보를 담아야 할 때 사용한다.

### 예외 처리: @ControllerAdvice

- `@ControllerAdvice`는 애플리케이션 전역에서 발생하는 예외를 한 곳에서 처리할 수 있도록 스프링에서 지원하는 기능이다.
- 전역 예외 처리(Global Exception Handling) 로직을 구현한다.
- 각 컨트롤러 메서드마다 `try-catch`를 작성하는 대신, 모든 예외 처리를 모아 코드의 중복을 줄이고 유지보수를 용이하게 한다.
- 예외가 발생했을 때 일관된 형태의 HTTP 상태 코드와 오류 메시지를 클라이언트에게 반환한다.

### @MockitoBean

```java
// 기존
//@MockBean
//private UserService userService;

// 대체
@MockitoBean
private UserService userService;
```

- `@MockBean`은 스프링 부트(Spring Boot) 3.4.0 버전부터 Deprecated 되었다.
- 스프링 부트 프로젝트가 순수 스프링 프로젝트에서도 동일하게 Mockito Mock 객체를 컨텍스트에 등록하는 기능을 사용할 수 있도록 범용성을 확대했다.
- `@MockitoBean`은 필드에 선언하면 해당 필드의 타입에 맞는 Mockito Mock 객체를 생성하고 테스트가 실행되는 스프링 컨텍스트 내의 기존 빈을 Mock 객체로 교체한다.

## 11. REST 엔드포인트 사용

```shell
http POST :8080/ch11/payment requestId:ABC123 amount:=1000
```

- HTTPie 사용 예시

### REST API 호출 방법

- OpenFeign
- RestTemplate
- WebClient

### RestTemplate

- 초기 스프링에서 가장 널리 사용한 REST 클라이언트다.
- 기본 통신방법은 동기(Synchronous) 방식이다.
- 현재는 유지보수 중이며 OpenFeign을 권장한다.
    - 공식문서에서는 WebClient 사용을 권장한다.

### WebClient

- 스프링 5부터 도입한 비동기(Non-blocking) 통신을 지원하는 REST 클라이언트다.

### OpenFeign

- 선언적(Declarative) REST 클라이언트다.
- 사용자가 호출할 API를 인터페이스(Interface) 형태로 정의하면 런타임에 인터페이스를 구현, API 호출을 처리한다.
- 주로 마이크로서비스 아키텍처(MSA) 환경에서 서비스 간 통신을 간단하게 구현할 때 유용하다.

### WireMock

```groovy
implementation 'org.wiremock.integrations:wiremock-spring-boot:3.10.0'
```

- WireMock이란 외부 HTTP 서버에 의존하는 테스트 사례에 적합한 라이브러리다.
- 테스트에 사용하는 목(Mock) HTTP 서버와 Mock API를 만들어준다.
- REST 클라이언트가 외부 서버와 통신하는 로직 자체를 신뢰할 수 있고 예측 가능한 환경에서 접근할 수 있도록 돕는 테스트 도구다.

## 12. 스프링 앱에서 데이터 소스 사용

### 스키마(Schema)

```sql
DROP TABLE IF EXISTS purchase CASCADE;

CREATE TABLE IF NOT EXISTS purchase (
    id INT AUTO_INCREMENT PRIMARY KEY,
    product VARCHAR(50) NOT NULL,
    price DOUBLE NOT NULL
)
```

- purchase 테이블은 구매 정보를 저장한다.
- id: 기본 키(Primary Key)이며 자동으로 증가하는 정수형(AUTO_INCREMENT)다.
- product: 구매한 상품명(VARCHAR)
- price: 상품 가격(DOUBLE)

### API 테스트 방법

```shell
# 구매
http POST :8080/ch12/purchase product="Spring Security in Action" price:=25.2

# 등록
http :8080/ch12/purchase
```

### 데이터 소스와 커넥션 관리

- 데이터 소스(Data Source)는 스프링이 데이터베이스에 접근하기 위한 필수 요소다. 
- 데이터베이스 커넥션(Connection)을 관리하는 객체의 추상화다.

**JDBC**

- 자바는 JDBC(Java Database Connectivity)를 통해 특정 데이터베이스 기술에 종속되지 않고 일관된 방식으로 데이터베이스에 접근하는 추상화를 제공한다.

**HikariCP**

- 스프링 부트는 HikariCP는 기본적으로 고성능 커넥션 풀(Connection Pool) 구현체를 사용한다.
- 커넥션 풀은 데이터베이스 커넥션을 미리 생성해 두고 필요할 때 재사용하여 애플리케이션 성능을 크게 향상한다.

**설정 및 보안**

- 데이터베이스 접속 정보는 애플리케이션 프로퍼티 파일에 작성하지 않는다. 보안을 위해 시크릿 볼트(Secret Vault)를 사용한다.

- Docker Compose Support

### 데이터베이스 의존성 관리

- Flyway, Liquibase는 데이터베이스 마이그레이션(Migration) 도구다. 스크립트의 버전 관리를 지원한다.
- 스키마 변경 사항을 추적하고, 환경 간 일관성을 유지하며, 배포 시 자동으로 스키마를 업데이트하는 데 사용한다.

**데이터 소스를 직접 구성할 때**

- 스프링 부트는 대부분 자동 구성을 지원한다.
- 다중 데이터 소스(데이터베이스를 여러 개 사용), 세부 커넥션 풀 설정, 외부 환경 통합이 필요할 때는 데이터 소스를 직접 구성할 수 있다.

### 스프링 부트와 Docker Compose

- 개발자마다 로컬 환경의 데이터베이스를 구성하는 방식이 다르면 문제가 발생할 수 있다.
- Docker Compose 파일을 공유하면 동일한 버전과 설정의 데이터 베이스 환경을 구성하기 쉽다.
- [Docker Compose](https://docs.spring.io/spring-boot/how-to/docker-compose.html)
- [Docker Compose Support in Spring Boot 3.1](https://spring.io/blog/2023/06/21/docker-compose-support-in-spring-boot-3-1)

## 13. 스프링 앱에서 트랜잭션 사용

- 트랜잭션이란? 왜 사용하는가? 스프링의 트랜잭션 사용 원리, 예시, 클래스, 메서드 사용, 주의

### 스키마(Schema)

```sql
DROP TABLE IF EXISTS account CASCADE;

CREATE TABLE account (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    amount DOUBLE NOT NULL
);
```

- id: 기본 키(Primary Key)이며 자동으로 증가하는 정수형(AUTO_INCREMENT)다.
- name: 계좌 소유자의 이름
- amount: 소유자가 보유한 금액

### API 테스트 방법

```shell
# 이체 전
http :8080/ch13/accounts

# 이체
http POST :8080/ch13/transfer senderAccountId:=1 receiverAccountId:=2 amount:=100

# 이체 후
http :8080/ch13/accounts
```

### 트랜잭션(Transaction)

- 트랜잭션이란 데이터베이스의 상태를 변화시키기 위해 수행하는 논리적인 작업의 최소 단위다.
- 여러 데이터베이스 연산(SQL)을 하나의 묶음으로 처리하여 데이터의 무결성과 일관성을 보장하기 위해 사용한다.
- 트랜잭션은 작업을 묶어 모두 성공(커밋)하거나, 모두 실패(롤백)하게 만든다.

**스프링의 트랜잭션**

- 스프링의 `@Transactional`은 AOP 애스팩트를 사용한다. 메서드 호출을 가로채고 호출에 트랜잭션 로직을 적용한다.
- `@Transactional`은 클래스 레벨 또는 메서드 레벨에 적용할 수 있다.
    - 클래스 레벨: 클래스 내의 모든 `public` 메서드에 적용
    - 메서드 레벨: 클래스 레벨의 설정을 재정의 또는 특정 메서드에만 적용
- 커밋(Commit): 메서드가 예외 없이 성공적으로 실행 완료하면 모든 변경사항을 데이터베이스에 저장한다.
- 롤백(Rollback): 메서드 실행 중 언체크 예외(`RuntimeException` 및 하위 예외)가 발생하면 작업 시작 시점으로 상태를 복원한다.
- 트랜잭션을 적용한 메서드 내에서 발생한 예외를 `try-catch`로 처리하고 다시 던지지 않으면, 스프링 AOP는 예외 발생을 추적할 수 없다.
- 메서드가 정상 종료로 간주되어 롤백되지 않고 커밋된다. 롤백을 처리하려면 반드시 예외를 메서드 밖으로 던져야(`throw`) 한다.

## 14. 스프링 데이터로 데이터 영속성 구현

### API 테스트 방법

```shell
# 이체 전
http :8080/ch14/accounts

# 이체
http POST :8080/ch14/transfer senderAccountId:=1 receiverAccountId:=2 amount:=100

# 매개변수를 사용하여 조회
http :8080/ch14/accounts name=='John Read'
```

## 영속성(Persistence)

- 프로그램의 실행이 종료된 이후에도 데이터를 보존하여 언제든지 다시 사용할 수 있도록 하는 특성이다.
- 애플리케이션에서 영속성을 다루는 부분을 영속성 계층(Persistence Layer)이라고 한다.
    - CRUD(Create, Read, Update, Delete)를 통해 데이터베이스와 상호작용
    - 비즈니스 로직과 데이터베이스 간의 데이터 변환

### 스프링 데이터(Spring Data)

- 데이터 접근 기술을 일관성 있고 편리하게 사용할 수 있도록 지원하는 추상화 계층
- 반복적인 데이터 접근 코드(CRUD)를 줄이고 비즈니스 로직에 집중할 수 있도록 지원
- 스프링 데이터는 여러 계층의 인터페이스를 제공하여 기능을 명확하게 분리하고 유연성을 확보한다.
    - Repository: 최상위 마커 인터페이스(Maker Interface), 기능 없이 인터페이스를 상속받는 클래스가 저장소임을 표시
    - CrudRepository: 기본적인 CRUD 기능을 제공(`save()`, `findById()`, `findAll`, `delete` 등)
    - PagingAndSortingRepository: `CrudRepository`를 상속, 페이징(Pagination) 및 정렬(Sorting) 기능을 추가로 제공

**인터페이스 분리**

- 인터페이스를 기능별로 분리하여 인터페이스 분리 원칙(ISP: Interface Segregation Principle)을 준수한다.
- 정의한 역할만 담당하여 불필요한 메서드 노출을 방지한다. 모듈 간의 의존성이 줄어 다른 기술로 변경하거나 기능 확장할 때 코드를 덜 수정한다.

**쿼리를 명시적으로 사용하는 이유**

- 스프링 데이터는 메서드 이름만으로 쿼리를 생성하는 쿼리 메서드(Query Method) 기능을 제공한다. 하지만 `@Query`를 사용하여 명시적으로 작성할 수 있다.
- 복잡한 연산 작업을 수행할 때 메서드 이름이 길어지면 가독성이 떨어진다.
- 메서드 이름을 쿼리로 변환하기 때문에 초기화 속도가 느려진다.
- 메서드 이름을 잘못 리팩터링 하면 동작에 영향을 미칠 위험이 있다.
