FROM openjdk:17-alpine
EXPOSE 8081
COPY ./build/libs/stateless-any-api-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
