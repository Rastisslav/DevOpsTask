FROM maven:3.8.6-eclipse-temurin-18

WORKDIR /untitled

COPY pom.xml ./
COPY src ./src

RUN mvn package
RUN mvn install




EXPOSE 8080