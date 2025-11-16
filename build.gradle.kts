buildscript {
    repositories {
        mavenCentral()
    }
    dependencies {
        // 예: Spring Boot 프로젝트를 위한 플러그인 정의
        // 각 서브 프로젝트의 build.gradle.kts에서 이 버전을 사용하게 됩니다.
    }
}

// 2. 모든 서브 프로젝트에 대한 공통 설정
subprojects {
    repositories {
        mavenCentral()
    }
}
