# 실전! 스프링 부트와 JPA 활용1 - 웹 애플리케이션 개발

## 1. 프로젝트 환경 설정

### H2 데이터베이스 설치

> [!IMPORTANT]
> Spring Boot `4.x`는 최소 H2 `2.4` 버전 이상을 사용해야 한다.[^1]

![H2 Database Engine Download - All Platforms](docs/img/h2-database-engine-download.jpg)

1. `jdbc:h2:~/jpashop`에 최초 접속, 데이터베이스 파일을 생성한다.
2. 정상적으로 접속되면 `~/jpashop.mv.db` 파일이 생성된다.
3. `jdbc:h2:tcp://localhost/~/jpashop`로 접속한다.

### 참고 자료

- [H2 Database Engine](https://www.h2database.com/html/main.html)

[^1]: [Spring Boot 4.0 Release Notes](https://github.com/spring-projects/spring-boot/wiki/Spring-Boot-4.0-Release-Notes)
