# Etapa 1: Construcción
FROM gradle:8.5-jdk21 AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
# Construimos saltando tests para agilizar el deploy
RUN gradle build --no-daemon -x test

# Etapa 2: Ejecución (Usamos Eclipse Temurin, la imagen oficial actual)
FROM eclipse-temurin:21-jdk
EXPOSE 8080
COPY --from=build /home/gradle/src/build/libs/*.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]