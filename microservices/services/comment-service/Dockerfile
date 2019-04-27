FROM openjdk:8u191-jre-alpine

RUN apk add --no-cache mariadb-client=10.3.13-r0

ADD target/comment-service-1.0-SNAPSHOT.jar comment-service-1.0-SNAPSHOT.jar
ADD entrypoint.sh /entrypoint.sh

EXPOSE 18086
ENTRYPOINT ["/entrypoint.sh"]
