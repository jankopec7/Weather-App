FROM maven:3.8.1-openjdk-17 AS build
WORKDIR /app
COPY . /app
RUN mvn clean package

FROM openjdk:17
COPY --from=build /app/target/codibly-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]