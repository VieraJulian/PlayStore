FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY target/eureka-0.0.1.jar eureka-app.jar
ENTRYPOINT ["java", "-jar", "eureka-app.jar"]