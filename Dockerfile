FROM openjdk:17-alpine
ARG JAR_FILE=target/cake-manager.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]