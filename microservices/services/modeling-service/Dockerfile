FROM openjdk:8u191-jre-alpine

RUN apk add --no-cache mariadb-client=10.3.13-r0

ADD target/modeling-service-1.0-SNAPSHOT.jar modeling-service-1.0-SNAPSHOT.jar
ADD entrypoint.sh /entrypoint.sh

EXPOSE 18092
ENTRYPOINT ["/entrypoint.sh"]
