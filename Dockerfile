FROM gradle:8.13-jdk21 AS java-build

ARG APP_HOME=/home/gradle/src
WORKDIR $APP_HOME
COPY --chown=gradle:gradle . .
RUN gradle build -x test --no-daemon

ARG JAR_FILE=budget-tracker.jar
ARG JAR_TARGET_DIR=/build/libs
ARG JAR=$APP_HOME$JAR_TARGET_DIR/$JAR_FILE

WORKDIR $APP_HOME/layers
RUN java -Djarmode=layertools -jar $JAR extract

FROM amazoncorretto:21-alpine AS runtime

RUN mkdir /app
WORKDIR /app

RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring

COPY --from=java-build /home/gradle/src/layers/dependencies/ ./
COPY --from=java-build /home/gradle/src/layers/spring-boot-loader/ ./
COPY --from=java-build /home/gradle/src/layers/snapshot-dependencies/ ./
COPY --from=java-build /home/gradle/src/layers/application/ ./

EXPOSE 8080

ENTRYPOINT ["java", "org.springframework.boot.loader.launch.JarLauncher"]
