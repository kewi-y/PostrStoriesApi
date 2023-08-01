FROM openjdk:17-jdk-alpine
COPY target/PostrStoriesApi-0.0.1.jar PostrStoriesApi-0.0.1.jar
ENTRYPOINT ["java","-jar","/PostrStoriesApi-0.0.1.jar"]