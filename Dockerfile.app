FROM openjdk:11
COPY ./target/sodium-consumption-calc-0.0.1-SNAPSHOT.jar /app/
WORKDIR /app
CMD ["java", "-jar", "sodium-consumption-calc-0.0.1-SNAPSHOT.jar"]