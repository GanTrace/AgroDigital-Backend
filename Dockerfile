FROM eclipse-temurin:22-jdk AS build

COPY ./ /app
WORKDIR /app

RUN mvn clean package -DskipTests

FROM eclipse-temurin:22-jre

COPY --from=build /app/target/AgroDigital-Backend-0.0.1-SNAPSHOT.jar /app.jar

CMD ["java", "-jar", "/app.jar"]