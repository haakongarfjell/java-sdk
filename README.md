---
sidebar_label: Java
pagination_next: null
pagination_prev: null
---

# Java SDK

💥 DRAFT! Work in progress. 💥

## Prerequisites

Only Java 17 has been tested for building the project.
Enable Gradle and Lombok support in your IDE.

## Building

The language level of the project is Java 8. The project uses Gradle as a build tool.
To build the project, run `./gradlew build`, or use the Gradle wrapper in your IDE.

## Formatting

The project uses Google's Java code style with Spotless.

## Models

Auto-generated models were used as a base. They were then manually edited to fit the specifications of the APIs.

To generate new models, run the `generateSwaggerCode` and `postProcessSwaggerCode` Gradle tasks.

This shouldn't be done unless the API specifications get a new major version. The generated model will require manual clean up, depending on the specification.

For minor/patch versions, the models should be edited manually.

## Licensing

Run the `licenseReport` task to generate a report of the dependencies and their licenses.
