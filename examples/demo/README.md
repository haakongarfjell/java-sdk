# To run

To run:

./gradlew bootRun

# To test SDK changes locally using this demo application

Go to the base folder of this repository and use the following command to generate a .jar for the SDK:

./gradlew jar

Then copy and paste it from build/lib to examples/demo/libs. Replace existing reference to the Java SDK in examples/demo/build.gradle/dependencies to:
implementation files('libs/vipps-java-1.0.0.jar')
Finally, add all the dependencies of the SDK to the dependencies of the demo application.
