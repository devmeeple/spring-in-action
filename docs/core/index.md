# 스프링 핵심 원리 - 기본편

## 1. 강의 소개

- 스프링의 핵심 원리는 어떻게 객체지향을 녹여냈을까?
- 스프링 기술 도입 이유와 핵심원리 파악하기
    - 스프링의 기본 기능
    - 스프링의 본질
    - 객체 지향 설계

## 4. 이야기 - 자바 진영의 추운 겨울과 스프링의 탄생

- Java 진영의 표준 기술, EJB의 독점
    - 비싼 기술
    - 어렵고 복잡하고 느렸다
- 로드 존슨, 개빈 킹 "의존성이 높은 EJB 지옥에서 벗어나, POJO한 코드를 작성하자!"
    - expert one-on-one J2EE Design and Development
    - Hibernate, JPA(Java Persistence API) 새로운 표준 정의(EJB 엔티티빈 기술을 대체)

### 4.1 스프링 역사

> Spring, 겨울을 넘어 새로운 시작

- Rod Johnson 'BeanFactory, ApplicationContext, POJO, 제어의 역전, 의존관계 주입등 다양한 개념 고안'
- Juergen Hoeller, 책에서 시작한 개념이 오픈 소스 프로젝트로 변환되었다.
- 2014년, 스프링 부트가 등장했다. 어려웠던 설정을 더욱 간소화 시키는 데 큰 공헌을 했다.

## 5. 스프링이란?

- 스프링은 여러 기술의 모음이다. 프레임워크 시큐리티, 세션, Rest Docs, 배치, 클라우드 등
- **스프링 부트는 여러 기술을 더욱 편리하게 사용할 수 있도록 지원하는 기술이다.**
    - 애플리케이션에서 Tomcat 같은 웹 서버를 설치하지 않고 단독으로 실행할 수 있다.
    - 외부 라이브러리(3rd parth)를 자동 구성한다.
- 스프링이라는 단어는 문맥에 따라 다르게 사용한다.
    - DI 컨테이너 기술
    - 프레임워크
    - 스프링 생태계

### 5.1 스프링을 왜 만들었을까?

- 스프링은 좋은 객체 지향 애플리케이션을 개발할 수 있도록 도와주는 프레임워크다.
