FROM maven:3.8.5-openjdk-21-slim as build

WORKDIR /app

COPY . /app/

RUN mvn clean package -DskipTests

FROM openjdk:21-slim as run

WORKDIR /app

COPY --from=build /app/target/dummyjson-client-1.0-SNAPSHOT.jar .

EXPOSE 8080/tcp

CMD ["java","-jar","dummyjson-client-1.0-SNAPSHOT.jar"]
