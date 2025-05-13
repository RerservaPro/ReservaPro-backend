FROM openjdk:21-jdk-slim

RUN apt-get update

WORKDIR /app

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} /app.jar

COPY . .

ENTRYPOINT ["java", "-jar", "/app.jar"]