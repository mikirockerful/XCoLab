#!/bin/sh

# Stop execution if any command returns nonzero exit
set -e

JAVA_OPTS="-Dserver.port=18092 -Xmx1G -Xms256M -XX:-OmitStackTraceInFastThrow -Dspring.profiles.active=docker"

exec java -jar $JAVA_OPTS /modeling-service-1.0-SNAPSHOT.jar
