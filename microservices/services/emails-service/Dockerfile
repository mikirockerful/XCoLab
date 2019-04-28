FROM openjdk:8u181-jre-slim

RUN apt-get update && apt-get install -y \
    mariadb-client-10.1 \
    wget

RUN wget -qO- https://repo1.maven.org/maven2/org/flywaydb/flyway-commandline/5.2.4/flyway-commandline-5.2.4-linux-x64.tar.gz | tar xvz && ln -s /flyway-5.2.4/flyway /usr/local/bin

ADD target/emails-service-1.0-SNAPSHOT.jar emails-service-1.0-SNAPSHOT.jar
ADD entrypoint.sh /entrypoint.sh

EXPOSE 18089
ENTRYPOINT ["/entrypoint.sh"]
