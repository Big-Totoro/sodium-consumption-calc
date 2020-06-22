FROM openjdk:11
WORKDIR /app
COPY ./target/sodium-consumption-calc-0.0.3-SNAPSHOT.jar .
CMD ["java", "-jar", "/app/sodium-consumption-calc-0.0.3-SNAPSHOT.jar"]