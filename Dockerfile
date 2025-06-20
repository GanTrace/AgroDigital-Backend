# Etapa de construcción
FROM openjdk:17-jdk-slim as builder

WORKDIR /app

# Copiar archivos de configuración de Maven
COPY .mvn .mvn
COPY mvnw .
COPY mvnw.cmd .
COPY pom.xml .

# Dar permisos de ejecución al maven wrapper
RUN chmod +x ./mvnw

# Descargar dependencias (esto mejora el cache de Docker)
RUN ./mvnw dependency:go-offline -B

# Copiar el código fuente
COPY src src

# Construir la aplicación
RUN ./mvnw clean package -DskipTests

# Etapa de ejecución
FROM openjdk:17-jre-slim

WORKDIR /app

# Crear usuario no-root para seguridad
RUN addgroup --system spring && adduser --system spring --ingroup spring
USER spring:spring

# Copiar el JAR construido desde la etapa anterior
COPY --from=builder /app/target/*.jar app.jar

# Exponer el puerto que usa Spring Boot por defecto
EXPOSE 8080

# Variables de entorno para optimización
ENV JAVA_OPTS="-Xmx512m -Xms256m"

# Comando para ejecutar la aplicación
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]