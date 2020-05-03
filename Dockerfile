# For Java 8, try this
# FROM openjdk:8-jdk-alpine




FROM maven:3.6.0-jdk-11-slim AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package

#
# Package stage V2
#
FROM openjdk:11-jre-slim
COPY --from=build /home/app/target/jeeback.jar /usr/local/lib/demo.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/usr/local/lib/demo.jar"]
