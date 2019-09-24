FROM gradle:jdk12 as build
WORKDIR /app

COPY --chown=gradle:gradle . /app
RUN gradle build
RUN gradle test && cd build && tar -czf /app/test-results.tar.gz test-results/test/TEST-*.xml

FROM openjdk:11-jre-slim
WORKDIR /app

RUN groupadd -r app && useradd -r -g app app

EXPOSE 8080
EXPOSE 9102

ENV DEBUG_LEVEL "DEBUG"

COPY --from=build /app/build/libs/app-1.0.0.jar /app/test-results.tar.gz /app/

USER app
ENTRYPOINT ["java", "-jar", "/app/app-1.0.0.jar"]
CMD []
