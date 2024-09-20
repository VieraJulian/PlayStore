FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY target/games-0.0.1.jar games-app.jar
ENTRYPOINT ["java", "-jar", "games-app.jar"]