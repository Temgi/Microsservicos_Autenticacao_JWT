FROM openjdk:17-alpine
EXPOSE 8082
COPY ./build/libs/statefull-auth-api-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
