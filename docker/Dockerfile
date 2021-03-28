FROM adoptopenjdk/openjdk11:alpine

RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring

ARG APP_DIR=frontend/dist/people-manager-app
ARG JAR_FILE=backend/target/people-manager*.jar

COPY ${APP_DIR} app
COPY ${JAR_FILE} api.jar

ENTRYPOINT ["java","-Dserver.port=80","-Dspring.web.resources.static-locations=file:/app","-jar","/api.jar"]
