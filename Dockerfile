FROM openjdk:11.0.16-jdk
ARG JAR_FILE=target/*.jar
COPY ./target/superhero-api-1.0.0.jar superhero-app.jar
ENTRYPOINT ["java","-jar","/superhero-app.jar"]