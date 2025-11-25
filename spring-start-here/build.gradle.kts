val springCloudVersion by extra("2025.0.0")

plugins {
    java
    id("org.springframework.boot") version "3.5.7"
    id("io.spring.dependency-management") version "1.1.7"
}

group = "io.github.devmeeple"
version = "0.0.1-SNAPSHOT"
description = "spring-start-here"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(17)
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter")

    // AOP(Aspect-Oriented Programming)
    implementation("org.springframework.boot:spring-boot-starter-aop")

    // Web MVC
    implementation("org.springframework.boot:spring-boot-starter-web")

    // Template Engine - Thymelaf
    implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
    implementation("org.springframework.cloud:spring-cloud-starter-openfeign")

    // 테스트
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}
dependencyManagement {
    imports {
        // Spring Cloud Open Feign
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:$springCloudVersion")
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
