---
sidebar_label: Java
pagination_next: null
pagination_prev: null
draft: true
---

# Java SDK

## Installation

Add the Maven repository for this package.

### Gradle

For Gradle, add it in build.gradle:

```
repositories {
	...
    maven {
        url = "https://maven.pkg.github.com/vippsas/java-sdk"
    }
}
...
dependencies {
    ...
    implementation 'no.vipps:vipps-java'
}
```

### Maven

For Maven, add it in pom.xml:

```
<repositories>
    ...
    <repository>
        <id>github-vippsas-java-sdk</id>
        <name>Github vippsas java-sdk</name>
        <url>https://maven.pkg.github.com/vippsas/java-sdk</url>
    </repository>
</repositories>
...
<dependencies>
    ...
    <dependency>
        <groupId>no.vipps</groupId>
        <artifactId>vipps-java</artifactId>
    </dependency>
</dependencies>
```

## Usage

```
VippsConfigurationOptions config = VippsConfigurationOptions.builder()
    .clientId("your-client-id-from-portal.vipps.no")
    .clientSecret("your-client-secret-from-portal.vipps.no")
    .subscriptionKey("your-subscription-key-from-portal.vipps.no")
    .merchantSerialNumber("your-merchant-serial-number-from-portal.vipps.no")
    .pluginName("Java-Sdk-Demo")
    .pluginVersion("1.0.0")
    .isUseTestMode(false)
    .build();
VippsConfiguration.getInstance().configureVipps(config, null);

String reference = UUID.randomUUID().toString();
InitiateSessionRequest sessionInitiationRequest = InitiateSessionRequest.builder()
    .transaction(
        InitiateSessionRequestTransaction.builder()
            .amount(Amount.builder().currency("NOK").value(10000)
                .build())
            .reference(reference)
            .paymentDescription("CheckoutSession from Java Demo")
            .build())
    .merchantInfo(
        InitiateSessionRequestMerchantInfo.builder()
            .callbackAuthorizationToken(
                "your-secret-callback-token")
            .callbackUrl("https://no.where.com/callback")
            .returnUrl("http://127.0.0.1:8080/ordercomplete?reference="
                + reference)
            .build())
    .build();

InitiateSessionResponse sessionResponse = CheckoutService.initiateSession(sessionInitiationRequest);
```

Check out the Spring Boot demo in examples/demo for more example code.

## Building

The language level of the project is Java 8. The project uses Gradle as a build tool.
To build the project, run `./gradlew build`, or use the Gradle wrapper in your IDE.

## Publishing

To publish a new version of the SDK, update the VERSION_NAME property in gradle.properties and then run the "Test and publish package" Github action. To run from CLI:

`gh workflow run "Test and publish package" --ref main`

## Formatting

The project uses Google's Java code style with Spotless. The project can be formatted by running `./gradlew spotlessApply`.

## Models

Auto-generated models were used as a base. They were then manually edited to fit the specifications of the APIs.

To generate new models, run `./gradlew generateSwaggerCode`. This should generally not be done as the auto-generated models have been modified extensively by hand.

## Licensing

Run `./gradlew licenseReport` to generate a report of the dependencies and their licenses.
