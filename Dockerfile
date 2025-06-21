FROM amazoncorretto:22

COPY ./compile/AgroDigital-Backend-0.0.1-SNAPSHOT.jar /app.jar

CMD ["java","-jar","/app.jar"]
