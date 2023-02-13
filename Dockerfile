FROM maven:3.8.5-jdk-11 as buildapi
WORKDIR /app
COPY . .
RUN mvn clean package

FROM openjdk:11
WORKDIR /app
COPY --from=buildapi ./app/target/*.jar ./app.jar
EXPOSE 8080
ENTRYPOINT java -jar app.jar