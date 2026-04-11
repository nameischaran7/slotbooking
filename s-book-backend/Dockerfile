# Step 1: Use a Java 24 image (matching your local JDK)
FROM openjdk:24-jdk-slim
# Step 2: Copy your built JAR file to the cloud
COPY target/*.jar app.jar
# Step 3: Tell the cloud to run your app
ENTRYPOINT ["java", "-jar", "/app.jar"]