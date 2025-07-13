plugins {
    kotlin("jvm") version "1.9.25"
    kotlin("plugin.spring") version "1.9.25"
    id("org.springframework.boot") version "3.1.2"
    id("io.spring.dependency-management") version "1.1.7"
}

group = "br.com.jrpbjr"
version = "0.0.1-SNAPSHOT"

extra["mongockVersion"] = "5.3.2"
extra["awsspringVersion"] = "3.0.2"


java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(17)
    }
}

repositories {
    mavenCentral()
}

dependencyManagement {
    imports {
        mavenBom("io.awspring.cloud:spring-cloud-aws-dependencies:${property("awsspringVersion")}")
        mavenBom("io.mongock:mongock-bom:${property("mongockVersion")}")
    }
}


dependencies {
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-mongodb")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")

    implementation("io.awspring.cloud:spring-cloud-aws-starter-sns") {
        exclude(group = "commons-logging", module = "commons-logging")
    }

    implementation("io.mongock:mongock-springboot-v3")
    implementation("io.mongock:mongodb-springdata-v4-driver")

    developmentOnly("org.springframework.boot:spring-boot-devtools")
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
    testImplementation("org.springframework.boot:spring-boot-starter-test") {
        exclude(module = "mockito-core")
    }
    testImplementation("io.mockk:mockk:1.13.9")
    testImplementation("com.ninja-squad:springmockk:4.0.2")
}




kotlin {
    compilerOptions {
        freeCompilerArgs.addAll("-Xjsr305=strict")
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
