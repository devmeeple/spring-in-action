plugins {
    java
    id("org.springframework.boot") version "4.0.5"
    id("io.spring.dependency-management") version "1.1.7"

    // 다양한 언어 코드 포맷팅과 관련 태스크 제공(spotlessApply)
    id("com.diffplug.spotless") version "8.4.0"
}

group = "io.github.devmeeple"
version = "0.0.1-SNAPSHOT"
description = "eog-springboot"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

repositories {
    mavenCentral()
}

dependencies {
    // 웹 애플리케이션 개발에 필요한 기본적인 의존성
    implementation("org.springframework.boot:spring-boot-starter-webmvc")

    // Swagger UI(웹 서비스 명세), 대안: Spring REST Docs
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.6.0")

    // Spring Data JPA 스타터 의존성
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")

    // MySQL JDBC 드라이버 의존성 추가, 버전을 생략하면 Spring Boot BOM이 CVE 없는 안전한 버전(현재 9.x)를 자동 지정
    runtimeOnly("com.mysql:mysql-connector-j")

    // Lombok, 반복 코드 자동 생성 -> 컴파일 시 처리
    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")

    // Spring MVC 테스트 지원
    testImplementation("org.springframework.boot:spring-boot-starter-webmvc-test")

    // Test Container, 임시 MySQL 컨테이너를 생성하여 유닛테스트를 수행하기 위함
    testImplementation("org.testcontainers:testcontainers-junit-jupiter")
    testImplementation("org.springframework.boot:spring-boot-testcontainers")
    testImplementation("org.testcontainers:testcontainers-mysql")

    // JUnit 테스트 실행 환경 제공
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.withType<Test> {
    useJUnitPlatform()

    // 테스트 실행 시 상세한 결과 출력 활성화
    testLogging {
        // 테스트 이벤트 중 어떤 것을 로그로 출력할지 설정
        events("passed", "skipped", "failed")
    }

    // 테스트 케이스 별 결과를 항상 출력하도록 설정
    outputs.upToDateWhen { false }

    // Java Agent 경고 메시지 억제용 JVM 옵션
    jvmArgs("-XX:+EnableDynamicAgentLoading")
}

// Spotless 플러그인 설정
spotless {
    java {
        // Google Java Format 적용
        googleJavaFormat("1.35.0").aosp().reflowLongStrings()
    }
}

tasks.named("build") {
    dependsOn("spotlessApply")
}

