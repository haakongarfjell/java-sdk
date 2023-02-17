plugins {
    id("java")
    `java-library`
}

group = "org.vipps"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.projectlombok:lombok:1.18.22")
    implementation("org.jetbrains:annotations:23.0.0")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.1")
    api("org.apache.httpcomponents:httpclient:4.5.14")
    implementation("org.apache.commons:commons-lang3:3.5")
    implementation("com.fasterxml.jackson.core:jackson-core:2.14.2")
    implementation("com.fasterxml.jackson.core:jackson-annotations:2.14.2")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}