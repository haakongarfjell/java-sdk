import java.net.URL
import java.nio.file.Files
import java.nio.file.Path
import java.util.regex.Pattern

plugins {
    id("java")
    `java-library`
    id("org.hidetake.swagger.generator") version "2.19.2"
    id("io.freefair.lombok") version "8.0.0-rc2"
    id("com.diffplug.spotless") version "6.4.0"
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
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.1")
    implementation("org.apache.commons:commons-lang3:3.5")
    implementation("com.fasterxml.jackson.core:jackson-core:2.14.2")
    implementation("com.fasterxml.jackson.core:jackson-annotations:2.14.2")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.14.2")
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.14.2")
    implementation("org.openapitools:jackson-databind-nullable:0.2.6")
    implementation("org.json:json:20230227")
    implementation("org.hibernate.validator:hibernate-validator:8.0.0.Final")
    implementation("io.jsonwebtoken:jjwt-api:0.11.5")
    implementation(platform("com.squareup.okhttp3:okhttp-bom:4.10.0"))
    implementation("com.squareup.okhttp3:okhttp")
    implementation("com.squareup.okhttp3:logging-interceptor")
    implementation("org.slf4j:slf4j-api:2.0.6")
    swaggerCodegen("org.openapitools:openapi-generator-cli:6.4.0")
    implementation("jakarta.ws.rs:jakarta.ws.rs-api:3.1.0")
    implementation("jakarta.annotation:jakarta.annotation-api:2.1.1")
    implementation("io.swagger:swagger-annotations:1.6.9")
    implementation("org.openapitools:openapi-generator-gradle-plugin:6.4.0")
    implementation("com.azure:azure-core:1.37.0")
    implementation("com.azure:azure-identity:1.8.1")
    implementation("com.azure:azure-security-keyvault-secrets:4.6.0")
    api("io.jsonwebtoken:jjwt-api:0.11.5")
    runtimeOnly("io.jsonwebtoken:jjwt-impl:0.11.5")
    runtimeOnly("io.jsonwebtoken:jjwt-orgjson:0.11.5")
}

tasks.withType<Test> {
    useJUnitPlatform()
    systemProperty("CLIENT_ID", System.getenv("CLIENT_ID"))
    systemProperty("CLIENT_SECRET", System.getenv("CLIENT_SECRET"))
    systemProperty("MERCHANT_SERIAL_NUMBER", System.getenv("MERCHANT_SERIAL_NUMBER"))
    systemProperty("SUBSCRIPTION_KEY", System.getenv("SUBSCRIPTION_KEY"))
}

spotless {
    java {
        googleJavaFormat("1.13.0") // 1.7 is the last version that supports Java 8
        removeUnusedImports()
    }
}

swaggerSources {
    create("checkout").apply {
        val apiName = this.name
        val url = URL("https://vippsas.github.io/vipps-developer-docs/redocusaurus/${apiName}-swagger-id.yaml")
        val yamlString = url.readText()
        val yamlFile = File("./api-specs/${apiName}-swagger-id.yaml")
        yamlFile.parentFile.mkdirs()
        yamlFile.createNewFile()
        yamlFile.writeText(yamlString)
        setInputFile(yamlFile)
        code(closureOf<org.hidetake.gradle.swagger.generator.GenerateSwaggerCode> {
            language = "java"
            configFile = file("./config/swagger/${apiName}.json")
            library = "jersey3"
        })
    }
    create("epayment").apply {
        val apiName = this.name
        val url = URL("https://vippsas.github.io/vipps-developer-docs/redocusaurus/${apiName}-swagger-id.yaml")
        val yamlString = url.readText()
        val yamlFile = File("./api-specs/${apiName}-swagger-id.yaml")
        yamlFile.parentFile.mkdirs()
        yamlFile.createNewFile()
        yamlFile.writeText(yamlString)
        setInputFile(yamlFile)
        code(closureOf<org.hidetake.gradle.swagger.generator.GenerateSwaggerCode> {
            language = "java"
            configFile = file("./config/swagger/${apiName}.json")
            library = "jersey3"
        })
    }

}

licenseReport {
    generateCsvReport = false
    generateHtmlReport = true
    generateJsonReport = false
    generateTextReport = false
}

tasks.register("postProcessSwaggerCode") {
    dependsOn("generateSwaggerCode")
    doLast {
        val outputDirectory = "./build"
        val enumSuffixPattern = Pattern.compile("(private\\s+)(\\w+)Enum(\\s+\\w+(\\s*=\\s*.+)?;)")

        fun removeEnumSuffix(file: Path) {
            val fileContent = file.toFile().readText()
            val matcher = enumSuffixPattern.matcher(fileContent)
            if (matcher.find()) {
                val modifiedContent = matcher.replaceAll("$1$2$3")
                file.toFile().writeText(modifiedContent)
            }
        }

        Files.walk(Path.of(outputDirectory)).use { paths ->
            paths.filter { it.toFile().isFile && it.fileName.toString().endsWith(".java") }.forEach { removeEnumSuffix(it) }
        }
    }
}
