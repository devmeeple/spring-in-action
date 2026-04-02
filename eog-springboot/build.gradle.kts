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

    // Lombok, 반복 코드 자동 생성 -> 컴파일 시 처리
    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")

    // Spring MVC 테스트 지원
    testImplementation("org.springframework.boot:spring-boot-starter-webmvc-test")

    // JUnit 테스트 실행 환경 제공
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.withType<Test> {
    useJUnitPlatform()
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

