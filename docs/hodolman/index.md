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
