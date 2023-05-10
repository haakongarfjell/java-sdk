---
sidebar_label: Java
pagination_next: null
pagination_prev: null
draft: true
---

# Java SDK

## How to install

Add the Maven repository for this package.

For Gradle, add it to the repositories section of build.gradle:

```
repositories {
	...
    maven {
        url = "https://maven.pkg.github.com/vippsas/java-sdk"
    }
}
```

For Maven, add it inside the <project> tag of pom.xml:

```
<repositories>
    ...
    <repository>
        <id>github-vippsas-java-sdk</id>
        <name>Github vippsas java-sdk</name>
        <url>https://maven.pkg.github.com/vippsas/java-sdk</url>
    </repository>
</repositories>
```

## Prerequisites

Only Java 17 has been tested for building the project.
Enable Gradle and Lombok support in your IDE.

## Building

The language level of the project is Java 8. The project uses Gradle as a build tool.
To build the project, run `./gradlew build`, or use the Gradle wrapper in your IDE.

## Publishing

To publish a new version of the SDK, update the VERSION_NAME property in gradle.properties and then run the "Test and publish package" Github action. To run from CLI:

```
gh workflow run "Test and publish package" --ref main
```

## Formatting

The project uses Google's Java code style with Spotless.

## Models

Auto-generated models were used as a base. They were then manually edited to fit the specifications of the APIs.

To generate new models, run the `generateSwaggerCode` and `postProcessSwaggerCode` Gradle tasks.

This should generally not be done as the auto-generated models have been modified extensively by hand.

## Licensing

Run the `licenseReport` task to generate a report of the dependencies and their licenses.
