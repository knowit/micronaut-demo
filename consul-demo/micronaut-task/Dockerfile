FROM openjdk:8u171-alpine3.7
RUN apk --no-cache add curl
COPY target/micronaut-task*.jar micronaut-task.jar
CMD java ${JAVA_OPTS} -jar micronaut-task.jar