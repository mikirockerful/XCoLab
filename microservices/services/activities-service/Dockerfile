FROM openjdk:8u191-jre-alpine

ADD target/activities-service-1.0-SNAPSHOT.jar activities-service-1.0-SNAPSHOT.jar
ADD entrypoint.sh /entrypoint.sh

EXPOSE 18084
ENTRYPOINT ["/entrypoint.sh"]
