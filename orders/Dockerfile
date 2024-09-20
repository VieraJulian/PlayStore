FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY target/orders-0.0.1.jar orders-app.jar
ENTRYPOINT ["java", "-jar", "orders-app.jar"]