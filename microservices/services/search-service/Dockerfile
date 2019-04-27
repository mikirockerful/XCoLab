FROM openjdk:8u191-jre-alpine

RUN apk add --no-cache mariadb-client=10.3.13-r0

ADD target/search-service-1.0-SNAPSHOT.jar search-service-1.0-SNAPSHOT.jar
ADD entrypoint.sh /entrypoint.sh

EXPOSE 18093
ENTRYPOINT ["/entrypoint.sh"]
