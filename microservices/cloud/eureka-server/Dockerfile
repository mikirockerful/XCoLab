FROM openjdk:8u191-jre-alpine

ADD target/eureka-server-1.0-SNAPSHOT.jar eureka-server-1.0-SNAPSHOT.jar
ADD entrypoint.sh /entrypoint.sh

EXPOSE 8761
ENTRYPOINT ["/entrypoint.sh"]
