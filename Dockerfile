# Etapa de build
FROM gradle:8.5.0-jdk21 AS builder

WORKDIR /app

COPY . .

RUN gradle --no-daemon clean bootJar

# Etapa de execução
FROM eclipse-temurin:21-jre-alpine AS runner
ARG JARFILE=*.jar

COPY --from=builder /app/build/libs/${JARFILE} application.jar

ENTRYPOINT ["java", "-jar", "application.jar"]
