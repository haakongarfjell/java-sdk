import java.net.URL

plugins {
    id("java")
    `java-library`
    checkstyle
    id("org.hidetake.swagger.generator") version "2.19.2"
    id("io.freefair.lombok") version "6.6.2"
    id("com.jaredsburrows.license") version "0.9.0"
}

group = "no.vipps"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.projectlombok:lombok:1.18.22")
    implementation("org.jetbrains:annotations:23.0.0")
    implementation("com.puppycrawl.tools:checkstyle:10.8.0")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.1")
    implementation("org.apache.commons:commons-lang3:3.5")
    implementation("com.fasterxml.jackson.core:jackson-core:2.14.2")
    implementation("com.fasterxml.jackson.core:jackson-annotations:2.14.2")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.14.2")
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.14.2")
    implementation("org.openapitools:jackson-databind-nullable:0.2.6")
    implementation("org.json:json:20220924")
    implementation("org.hibernate.validator:hibernate-validator:8.0.0.Final")
    implementation("io.jsonwebtoken:jjwt-api:0.11.5")
    implementation(platform("com.squareup.okhttp3:okhttp-bom:4.10.0"))
    implementation("com.squareup.okhttp3:okhttp")
    implementation("com.squareup.okhttp3:logging-interceptor")
    implementation("org.slf4j:slf4j-api:2.0.6")
    swaggerCodegen("org.openapitools:openapi-generator-cli:6.4.0")
    implementation("jakarta.ws.rs:jakarta.ws.rs-api:3.1.0")
    implementation("jakarta.annotation:jakarta.annotation-api:2.1.1")
    implementation("org.openapitools:openapi-generator-gradle-plugin:6.4.0")
}


tasks.withType<Test> {
    useJUnitPlatform()
}

checkstyle {
    toolVersion = "10.8.0"
}

swaggerSources {
    create("checkout").apply {
        val apiName = this.name
        val url = URL("https://vippsas.github.io/vipps-developer-docs/redocusaurus/${apiName}-swagger-id.yaml")
        val yamlString = url.readText()
        val yamlFile = File("./api-specs/${apiName}-swagger-id.yaml")
        yamlFile.writeText(yamlString)
        setInputFile(yamlFile)
        code(closureOf<org.hidetake.gradle.swagger.generator.GenerateSwaggerCode> {
            language = "java"
            configFile = file("./config/swagger/${apiName}.json")
            components = listOf("models")
            library = "jersey3"
        })
    }
    create("epayment").apply {
        val apiName = this.name
        val url = URL("https://vippsas.github.io/vipps-developer-docs/redocusaurus/${apiName}-swagger-id.yaml")
        val yamlString = url.readText()
        val yamlFile = File("./api-specs/${apiName}-swagger-id.yaml")
        yamlFile.writeText(yamlString)
        setInputFile(yamlFile)
        code(closureOf<org.hidetake.gradle.swagger.generator.GenerateSwaggerCode> {
            language = "java"
            configFile = file("./config/swagger/${apiName}.json")
            components = listOf("models")
            library = "jersey3"
        })
    }
licenseReport {
    generateCsvReport = false
    generateHtmlReport = true
    generateJsonReport = false
    generateTextReport = false
}
