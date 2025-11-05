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
