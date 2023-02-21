plugins {
    id("java")
    `java-library`
    checkstyle
    id("io.freefair.lombok") version "6.6.2"
}

group = "org.vipps"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.projectlombok:lombok:1.18.22")
    implementation("org.jetbrains:annotations:23.0.0")
    implementation("checkstyle:checkstyle:5.0")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.1")
    api("org.apache.httpcomponents:httpclient:4.5.14")
    implementation("org.apache.commons:commons-lang3:3.5")
    implementation("com.fasterxml.jackson.core:jackson-core:2.14.2")
    implementation("com.fasterxml.jackson.core:jackson-annotations:2.14.2")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.14.2")
    implementation("org.json:json:20220924")
    implementation("org.hibernate.validator:hibernate-validator:8.0.0.Final")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}

checkstyle {
    toolVersion = "10.7.0"
}
