FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY target/config-server-0.0.1.jar config-server.jar
ENTRYPOINT ["java", "-jar", "config-server.jar"]