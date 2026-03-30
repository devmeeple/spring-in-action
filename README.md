# Spring in Action

## 들어가며

> [!NOTE] 
> Spring을 학습 할 때 사용한 자료를 여러 독립 프로젝트로 모아 관리한다.

- 각 프로젝트는 서로 의존하지 않는다. **완전히 독립 `Gradle` 프로젝트다.**
- 강의, 책, 실습 단위로 프로젝트를 분리한다.
- '멀티 모듈 프로젝트'가 아니다.

### 유의사항

- `setting.gradle.kts`에서 `include(...)`하지 않는다.
- 루트에서 하위 프로젝트를 관리하지 않는다.
- 각 프로젝트는 다음 파일을 반드시 포함한다.
  - `settings.gradle.kts`
  - `build.gradle.kts`
  - `gradlew`
  - `gradle/wrapper`

```shell
cd bootcamp
./gradlew bootRun

cd spring-start-here
./gradlew bootRun

cd hodolman
./gradlew bootRun
```

- 실행은 각 프로젝트 디렉터리에서 한다.
- Git은 `spring-in-action`(Root)에서 이뤄진다.

### 참고 자료

- [호돌맨의 요절복통 개발쇼 (SpringBoot, Vue.JS, AWS)](https://inf.run/ySr4N)
- [『스프링 교과서』(로렌티우 스필카, 길벗, 2024)](https://product.kyobobook.co.kr/detail/S000213355775)
- [『부트캠프 백엔드 개발자 편 with 스프링 부트』(김송아, 한빛미디어, 2026)](https://product.kyobobook.co.kr/detail/S000219025613)
