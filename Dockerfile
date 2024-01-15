FROM maven:3.9-amazoncorretto-21-al2023 AS MAVEN_BUILD

MAINTAINER Brian Hannaway

COPY pom.xml /build/
COPY src /build/src/

WORKDIR /build/
RUN mvn package -q

FROM amazoncorretto:21-alpine-jdk

WORKDIR /app

COPY --from=MAVEN_BUILD /build/target/schedule-0.0.1-SNAPSHOT.jar /app/

ENTRYPOINT ["java", "-jar", "schedule-0.0.1-SNAPSHOT.jar"]