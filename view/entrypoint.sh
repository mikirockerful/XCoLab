#!/bin/sh

# Stop execution if any command returns nonzero exit
set -e

JAVA_OPTS="-Dserver.port=18082 -Xmx2G -Xms1G -XX:-OmitStackTraceInFastThrow -Dspring.profiles.active=docker"

exec java -jar $JAVA_OPTS /xcolab-view-1.0-SNAPSHOT.war
