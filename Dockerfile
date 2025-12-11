FROM openjdk:25

WORKDIR /app

COPY target/security-0.0.1-SNAPSHOT.jar /app/security-0.0.1-SNAPSHOT.jar

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "security-0.0.1-SNAPSHOT.jar"]