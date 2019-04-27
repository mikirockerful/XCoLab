FROM openjdk:8u191-jre-alpine

RUN apk add --no-cache mariadb-client=10.3.13-r0

ADD target/members-service-1.0-SNAPSHOT.jar members-service-1.0-SNAPSHOT.jar
ADD entrypoint.sh /entrypoint.sh

EXPOSE 18091
ENTRYPOINT ["/entrypoint.sh"]
