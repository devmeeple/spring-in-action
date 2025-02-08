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
