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
    implementation("org.springframework.boot:spring-boot-starter-webmvc")
    testImplementation("org.springframework.boot:spring-boot-starter-webmvc-test")
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

