# 호돌맨의 요절복통 개발쇼 (SpringBoot, Vue.JS, AWS)

## 컨트롤러 생성

```java
@WebMvcTest
class PostControllerTest {

    @Autowired
    private MockMvcTester mockMvcTester;

    @DisplayName("/post 요청 시 Hello World를 출력한다.")
    @Test
    void test() {
        mockMvcTester.get().uri("/posts")
                .exchange()
                .assertThat()
                .hasStatusOk()
                .hasBodyTextEqualTo("Hello World2")
                .apply(print());
    }
}
```

- `MockMvcTester`를 사용하여 테스트 코드 개선

### 참고 자료

- [Testing Spring Controllers With MockMvcTester](https://blog.jetbrains.com/idea/2025/04/a-practical-guide-to-testing-spring-controllers-with-mockmvctester/)

## POST 데이터 콘텐츠 타입

- **HTTP 메서드**
  - 페이로드(Payload): 전송에 사용되는 순수 데이터
  - `POST`와 `PUT`의 차이는 멱등성이다. `PUT`은 몇 번을 요청해도 같은 결과가 일어난다. 즉, 부수 효과가 없다.
  - `PUT`은 리소스를 완전히 교체한다. 반면, `PATCH`는 멱등성을 갖지 않는다.

| 메서드     | 개념                                                       |
|---------|----------------------------------------------------------|
| GET     | 리소스를 가져오도록 요청                                            |
| POST    | 서버에 데이터를 전송, 요청 본문의 유형은 `Content-Type` 헤더로 나타냄           |
| PUT     | 요청 페이로드를 사용하여 새로운 리소스 생성 또는 대상 리소스 대체                    |
| PATCH   | 리소스 부분 수정                                                |
| DELETE  | 리소스 삭제                                                   |
| OPTIONS | 리소스가 지원하는 통신 옵션을 요청, 주로 서버가 지원하는 HTTP 메서드나 헤더 등을 확인할 때 사용 |
| HEAD    | `GET` 요청과 동일하지만 헤더만을 반환                                  |
| TRACE   | 요청 메시지가 프록시 서버 등을 거쳐 서버에 도착하는 과정에 어떻게 변형되었는지 확인          |
| CONNECT | 프록시 서버를 통해 목적 리소스로 TCP 터널을 설정하도록 요청                      |

- `@RequestParam`, `@ModelAttribute`, `@RequestBody` 개념과 사례
  - `@RequestParam`: 쿼리 매개변수 또는 폼 데이터를 `Controller` 메서드 매개변수와 연결
  - `@ModelAttribute`: 클라이언트 요청을 객체로 연결
  - `@RequestBody`: HttpMessageRender를 통해 **요청 본문(body)**을 읽고 역직렬화

### 참고 자료

- [MDN Web Docs 'HTTP request methods'](https://developer.mozilla.org/en-US/docs/Web/HTTP/Reference/Methods)

## 데이터 검증-1

**Validation**

- 데이터 검증은 실수, 누락, 악의적인 입력을 방지하고 데이터베이스에 의도치 않은 값 저장을 막기 위해 필요하다.

```groovy 
implementation("org.springframework.boot:spring-boot-starter-validation") 
``` 

- Spring에서 데이터 검증을 사용할 때는 의존성을 추가해야 한다.
- 검증에는 Java Bean Validation(Jakarta Validation) API를 사용한다. API는 표준 어노테이션(`@NotNull` 등)과 인터페이스가 정의되어 있다.
- Hibernate Validator는 표준을 참고하여 실제 검증 로직을 구현했다.
- Spring Boot Starter는 Jakarta Validation API와 Hibernate Validator를 한 번에 포함해 주는 편의 패키지다. 별도 설정 없이 즉시 검증기능을 사용할 수 있게 지원한다.

**JsonPath**

- JsonPath는 JSON을 검증하도록 구현된 표현식이다.
- Spring의 `jsonPath()` 메서드는 내부적으로 [Jayway JsonPath](https://github.com/json-path/JsonPath) 라이브러리를 사용한다.
- MockMvc는 가짜 HTTP 요청을 보내는 도구이며 JsonPath는 응답 JSON을 검증하는 도구다.

### 참고 자료

- [Java Bean Validation](https://docs.spring.io/spring-framework/reference/core/validation/beanvalidation.html)

## 데이터 검증-2

### @ControllerAdvice

- `@ExceptionHandler`와 `@ControllerAdvice`는 Spring MVC에서 예외를 공통적으로 처리하는 핵심 도구다.
- `@ExceptionHandler`를 사용하면 컨트롤러에서 예외를 처리할 수 있다. 하지만 정상 코드와 예외 처리 코드가 분리되지 않는다.
- `@ControllerAdivce`를 사용하면 위 문제를 해결할 수 있다.
- `@ControllerAdivce`는 대상을 지정하지 않으면 모든 컨트롤러에 적용된다. 따라서 전역 예외 처리(Global Exception Handler)에 적합하다.

### 참고 자료

- [Controller Advice](https://docs.spring.io/spring-framework/reference/web/webmvc/mvc-controller/ann-advice.html)

## 작성글 저장 1 - 게시글 저장 구현

### @Lob

- 게시글의 본문의 길이는 가변적이다. 따라서 JPA에서 가변 길이 데이터 유형을 나타내는 `@Lob`를 사용한다.

## 작성글 저장 2 - 클래스 분리

### Jackson

- Jackson은 JVM 진영에서 가장 많이 사용하는 JSON 직렬화/역질렬화 라이브러리다.
- 객체를 JSON(String)으로 바꾸거나, 반대로 JSON을 객체로 바꿔주는 역할이다.
- Spring은 Jackson 라이브러리를 기본으로 사용하여 별도의 설정 없이 JSON으로 자동 처리한다.

### ObjectMapper

- ObjectMapper는 Jackson의 핵심 클래스다. 실질적인 변환을 수행한다.
- Spring은 애플리케이션 실행 시 자동으로 하나의 ObjectMapper를 빈(bean)으로 등록하고 빈을 기반으로 HTTP 요청과 응답을 변환한다.
  - 컨트롤러에서 `Post` 객체를 반환하면, Spring은 내부적으로 `ObjectMapper.writeValueAsString()`을 호출하여 JSON 문자열로 응답한다.

### 참고 자료

- [JSON](https://docs.spring.io/spring-boot/reference/features/json.html)
- [Jackson](https://docs.spring.io/spring-framework/reference/web/webmvc-view/mvc-jackson.html)
- [Introducing Jackson 3 support in Spring](https://spring.io/blog/2025/10/07/introducing-jackson-3-support-in-spring)


### 객체를 생성하는 방법

**생성자(Constructor)**

```java
public class User {
    private String name;
    private int age;
    
    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
```

- 객체를 만들기 위한 가장 기본적이며 직관적인 방법
- 동일 타입 파라미터가 여러 개 일 때 혼란을 야기함
- 선택적 인자 처리 어려움
- 의미 전달이 어려움

**정적 팩토리 메서드(Static Factory Method)**

```java
public class User {
    private String name;
    private int age;

    private User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public static User of(String name, int age) {
        return new User(name, age);
    }

    public static User guest() {
        return new User("Guest", 0);
    }
}
```

- 객체 생성 의도를 명확히 표현하고 싶을 때 사용

**빌더 패턴(@Builder)**

- 필드가 많거나 선택적 파라미터가 많은 객체 생성에 적합
- 인자의 순서, `null` 처리, 기본값 문제를 해결하고 싶을 때 사용

- 생성자, 정적 메서드, @Builder(디자인 패턴) 배경과 사용법
  - @Builder의 장점: 가독성
  - 유연한 값 생성
  - 필요한 값만 받을 수 있음 -> 오버로딩 가능한 조건
  - *객체의 불변성*

### 참고 자료

- [빌더 패턴(Builder Pattern)](https://johngrib.github.io/wiki/pattern/builder/)
- [정적 팩토리 메서드(static factory method)](https://johngrib.github.io/wiki/pattern/static-factory-method/)
- [Constructor vs Static Factory vs Builder Pattern: When and What to Use in Java](https://medium.com/@kariapratham/constructor-vs-static-factory-vs-builder-pattern-when-and-what-to-use-in-java-5f1ec79d3cc5)
- [Constructor vs. Builder: Making the Right Choice for Your Project](https://www.dhiwise.com/blog/design-converter/constructor-vs-builder-making-the-right-choice-for-your-project)

## 게시글 조회 1 - 단건조회

- @PathVariable
- Optional
