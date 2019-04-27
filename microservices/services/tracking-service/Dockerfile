FROM openjdk:8u191-jre-alpine

RUN apk add --no-cache mariadb-client=10.3.13-r0

ADD target/tracking-service-1.0-SNAPSHOT.jar tracking-service-1.0-SNAPSHOT.jar
ADD entrypoint.sh /entrypoint.sh

EXPOSE 18094
ENTRYPOINT ["/entrypoint.sh"]
