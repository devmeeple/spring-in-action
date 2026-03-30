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

## 2. 도메인 분석 설계

### 엔티티 클래스 개발

#### JPA Entity Annotation 작성 규칙

> [!NOTE]
> JPA Entity의 Annotation 순서는 관례가 없다. 하지만 프로젝트에서 일관되게 유지하면 가독성과 유지보수성을 높인다.

```java
@Entity
@Table(name = "member")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    // === PK ===
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    // === 기본 필드 ===
    @Column(nullable = false)
    private String name;

    // === 연관 관계 ===
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;
}
```

- Annotation은 의미 순서 기준으로 작성한다.
- Annotation은 한 줄에 하나씩 작성한다.
- 클래스/필드/관계별로 블록을 나누어 작성한다.
- Annotation은 한 줄에 하나만 작성한다

**클래스 레벨 Annotation 순서**

```text
@Entity
@Table(...)
@Inheritance(...)
@DiscriminatorColumn(...)
@DiscriminatorValue(...)
@Getter
@Setter
```

- JPA 핵심 -> 상속/매핑 -> 보조(Lombok)
- Lombok은 항상 마지막에 작성한다.

**필드 Annotation 순서**

PK 필드

```text
@Id
@GeneratedValue(...)
@Column(...)
```

연관 관계

```text
@ManyToOne(...)
@JoinColumn(...)
```
