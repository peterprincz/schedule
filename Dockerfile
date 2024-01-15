FROM maven:3.5.2-jdk-8-alpine AS MAVEN_BUILD

MAINTAINER Brian Hannaway

COPY pom.xml /build/
COPY src /build/src/

WORKDIR /build/
RUN mvn package

FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

COPY --from=MAVEN_BUILD /build/target/schedule-0.0.1-SNAPSHOT.jar /app/

ENTRYPOINT ["java", "-jar", "schedule-0.0.1-SNAPSHOT.jar"]