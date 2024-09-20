FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY target/users-0.0.1.jar users-app.jar
ENTRYPOINT ["java", "-jar", "users-app.jar"]