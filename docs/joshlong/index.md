# Josh Long 씨에게 배우다

- [IntelliJ IDEA Conf 2025 'Bootiful Spring Boot'](https://inf.run/FvmXv)

# IntelliJ IDEA Conf 2025 'Bootiful Spring Boot'

## 주요 의존성

- Spring Debugger
    - Spring 개발에 특화된 디버깅 도구(IDE 플러그인)
    - Spring Application Context(스프링 컨테이너)를 인식하여 Bean 생성 과정, 의존성 주입, AOP 프락시 등을 시각적으로 확인할 수 있다.
    - 일반적인 디버거는 단순 코드 흐름을 추적한다. 반면 Spring Debugger는 Spring 콘텍스트 내부 구조를 보여주기 때문에 문제를 빠르게 파악할 수 있다.
- Spring Boot Dev Tools
    - 개발 편의성을 높이기 위한 도구 모음
    - 코드가 변경될 때 자동 재시작을 제공, 빠른 피드백을 얻는다.
    - 운영 환경에서는 사용하지 않고 개발 환경에서만 사용
- GraalVM Native Support
    - GraalVM은 Oracle에서 개발한 고성능 JDK 기반 런타임이다.
    - Spring Boot는 GraalVM을 활용, 애플리케이션을 네이티브 이미지(Native Image)로 빌드할 수 있다.
    - 리플렉션, 동적 프락시를 많이 사용하는 경우 추가 설정이 필요하다.
- Docker Compose Support
    - Spring Boot가 Docker Compose를 직접 실행 및 관리하도록 지원하는 기능, 지원 전에는 각 환경을 수동으로 실행했다.
    - 개발 환경 인프라를 코드로 관리(Infrastructure as Code) 할 수 있다.
- Spring Web
    - Spring Boot로 웹 애플리케이션을 만들기 위한 핵심 의존성
    - Spring MVC, REST API 개발에 필요한 라이브러리를 포함한다.
- PostgresSQL
    - [PostgreSQL vs MySQL, 어떤 데이터베이스를 선택해야 할까?](https://polarishare.com/d/4ldqtef)
- Spring Data JPA
    - JPA(Java Persistence API) 기반으로 반복적인 CRUD(Create, Read, Update, Delete) 코드를 자동화하고 인터페이스 기반으로 데이터베이스 접근을 쉽게 만드는 의존성
- Spring Boot Actuator
    - 애플리케이션의 운영 상태를 모니터링하고 관리할 수 있는 기능을 제공한다.
    - REST 엔드포인트로 제공한다.
- Spring Cloud Config
    - 분산 시스템 환경(MSA)에서 설정 정보를 중앙 집중식으로 관리하는 설루션
    - Externalized Configuration를 지원한다.

## 질문과 답변

### Spring Bean의 접근 제어자

> Spring Bean의 접근제어자를 package-private(default)로 사용하는 이유가 있을까?

- Bean은 반드시 `public`일 필요는 없다. 같은 패키지 내에서만 접근 가능한 `package-private`(default) 접근 제어자도 사용할 수 있다.
- 모든 클래스를 `public`으로 열어두면 어디서든 참조할 수 있다. 이는 의존성 확산을 유도한다. 접근 범위를 최소화하는 것은 설계 안정성을 높이는 전략이다. 

### ApplicationRunner

- `ApplicationRunner`란 Spring Boot 애플리케이션이 완전 초기화 된 후, 한 번 실행되는 콜백 인터페이스다.
- Spring 콘텍스트가 모두 생성된 후 실행한다.
- 비슷한 인터페이스는 `CommandLineRunner`가 있다.
- 초기 데이터 세팅, 외부 시스템 연결 검증, 시작 시점 로직 실행 등에 사용한다.

### Spring Modulith

- 모놀리식 애플리케이션을 논리적 모듈로 구조화 할 수 있도록 지원하는 프로젝트다.
- 모놀리식 구조의 단순함은 유지하면서 모듈 간 경계를 명확히 하여 이벤트 기반 상호작용을 지원한다.
- 완전한 MSA로 가기 전 모놀리식 내부를 건강하게 유지하는 전략이다.

### @RestController를 사용하지 않는 이유

- `@RestController`는 `@Controller`와 `@ResponseBody`를 합친 어노테이션이다.
- Josh Long 씨는 REST의 본질인 'HTTP를 제대로 사용하기'에 집중하기 위해 `@Controller`, `@ResponseBody`를 직접 사용한다.

### Spring Data AOT

- Spring Data AOT는 GraalVM Native Image 환경을 지원하기 위해 Spring Data가 런타임 동작을 빌드 시점으로 옮기는 최적화 기술이다.
- AOT(Ahead-Of-Time)란, 런타임에 처리하던 작업을 컴파일 시점에 미리 수행하는 방식을 의미한다.

