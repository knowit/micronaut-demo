FROM openjdk:8u171-alpine3.7
RUN apk --no-cache add curl
COPY target/micronaut-tasklist*.jar micronaut-tasklist.jar
CMD java ${JAVA_OPTS} -jar micronaut-tasklist.jar