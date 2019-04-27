FROM openjdk:8u191-jre-alpine

RUN apk add --no-cache mariadb-client=10.3.13-r0

ADD target/admin-service-1.0-SNAPSHOT.jar admin-service-1.0-SNAPSHOT.jar
ADD entrypoint.sh /entrypoint.sh

EXPOSE 18085
ENTRYPOINT ["/entrypoint.sh"]
