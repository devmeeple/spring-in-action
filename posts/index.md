# 2. Domain

## 목차

- 프로젝트 환경 변수 설정

## 2.3 프로젝트 환경 변수 설정

- **프로젝트 환경설정, 환경 변수를 가장 먼저 작업할까?**
- **환경 변수 설정은 선호도에 사용법이 다르다. 어떤 변화를 겪었을까?**
- 환경 변수는 환경을 분리하기 위해 사용한다. 현업에서 이뤄지는 개발은 운영서버와 개발서버가 분리되어 있는 상황이 비일비재하다.
- 환경 변수 설정은 다양한 방법이 있다.
    - `code`, `XML(Extensible Markup Language)`, `properties`, `yml`
- [Spring Framework Documentation 'Profiles'](https://docs.spring.io/spring-boot/reference/features/profiles.html)

> 환경 변수 지정하지 않은 상황

- 환경 변수를 지정하지 않으면 `default`로 설정한다.

## 2.4 클래스 생성

**BaseEntity**

- `@MappedSuperClass`
- `abstract`

**Entity**

- 상속
- 기본키(Primary Key) 생성 전략
- PK naming
- `id: Type? = null`
- Repository, Spring Data JPA
- Enum

## 2.5 엔티티 개발 - 연관관계 없음

> 테이블 설계 참고

- 생성자를 추가했음

- Class should have [public, protected] no-arg constructor.
    - [Stack Overflow 'JPA error'](https://stackoverflow.com/questions/64410753/jpa-error-in-kotlin-class-student-should-have-public-protected-no-arg-con)
    - [Kotlin Docs 'No-arg compiler plugin'](https://kotlinlang.org/docs/no-arg-plugin.html): reflection?
    - [Spring 'Building web applications with Spring Boot and Kotlin'](https://spring.io/guides/tutorials/spring-boot-kotlin)
    - [코틀린에서 JPA를 이슈없이 사용하는 방법](https://wslog.dev/kotlin-jpa)
- `@Column`은 사용 유무와 옵션
- `camelCase`, 데이터베이스 필드명 naming

**SkillType**

- `SkillType.valueOf(type)`: enum.valueOf
- DBMS 예약어 `type`
- `EnumType.ORDINAL` vs. `EnumType.String`

**HttpInterface**

- Servlet
- HTTP: cookies, referer, uri, user agent
- Null Safety

## 2.6 엔티티 개발 - 연관관계 있음

> 29, 강의자료 Experience 오탈자 문의 필요(startYear, startMonth),

- `@OneToMany`, `@ManyToOne`
- `mutableList`
- 연관관계, 단방향
- 트랜잭션 스크립트
- [Null Safety](https://kotlinlang.org/docs/null-safety.html): `null` 체크, 현재 코드가 최선인가?
