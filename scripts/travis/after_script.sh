#!/bin/bash

if [ $BUILD_TYPE == 'deploy' ]; then

    echo "Building Docker images"
    docker build -t mikirockerful/activities-service-xcolab microservices/services/activities-service/
    docker build -t mikirockerful/admin-service-xcolab microservices/services/admin-service/
    docker build -t mikirockerful/comment-service-xcolab microservices/services/comment-service/
    docker build -t mikirockerful/content-service-xcolab microservices/services/content-service/
    docker build -t mikirockerful/contest-service-xcolab microservices/services/contest-service/
    docker build -t mikirockerful/emails-service-xcolab microservices/services/emails-service/
    docker build -t mikirockerful/eureka-server-xcolab microservices/cloud/eureka-server/
    docker build -t mikirockerful/members-service-xcolab microservices/services/members-service/
    docker build -t mikirockerful/modeling-service-xcolab microservices/services/modeling-service/
    docker build -t mikirockerful/moderation-service-xcolab microservices/services/moderation-service/
    docker build -t mikirockerful/search-service-xcolab microservices/services/search-service/
    docker build -t mikirockerful/tracking-service-xcolab microservices/services/tracking-service/
    docker build -t mikirockerful/view-xcolab view/


    echo "Creating binary directories"
    mkdir -p binaries/view
    mkdir -p binaries/cloud
    mkdir -p binaries/services

    echo "Moving view binary"
    mv view/target/xcolab-view-1.0-SNAPSHOT.war binaries/view/

    echo "Moving cloud binaries"
    mv microservices/cloud/eureka-server/target/eureka-server-1.0-SNAPSHOT.jar binaries/cloud/
    mv microservices/cloud/service-proxy/target/service-proxy-1.0-SNAPSHOT.jar binaries/cloud/

    echo "Moving service binaries"
    mv microservices/services/*-service/target/*-1.0-SNAPSHOT.jar binaries/services/
else
    echo "Skipping deployment"
fi
