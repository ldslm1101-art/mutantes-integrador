# Etapa 1: Construcción (Usamos una imagen con Gradle y Java 21)
FROM gradle:8.5-jdk21 AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
# Construimos el proyecto (saltando los tests para ir rápido)
RUN gradle build --no-daemon -x test

# Etapa 2: Ejecución (Imagen ligera solo con Java 21)
FROM openjdk:21-jdk-slim
EXPOSE 8080
COPY --from=build /home/gradle/src/build/libs/*.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]