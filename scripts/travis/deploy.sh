#!/bin/bash

# Fail if any steps in the deployment fail
set -e

if [ ${BUILD_TYPE} == 'deploy' ]; then

    #TODO: This should push the built containers to the DockerHub
    echo "$DOCKER_PASSWORD" | docker login -u "$DOCKER_USERNAME" --password-stdin
    docker push mikirockerful/activities-service-xcolab
    docker push mikirockerful/admin-service-xcolab
    docker push mikirockerful/comment-service-xcolab
    docker push mikirockerful/content-service-xcolab
    docker push mikirockerful/contest-service-xcolab
    docker push mikirockerful/emails-service-xcolab
    docker push mikirockerful/eureka-server-xcolab
    docker push mikirockerful/members-service-xcolab
    docker push mikirockerful/modeling-service-xcolab
    docker push mikirockerful/moderation-service-xcolab
    docker push mikirockerful/search-service-xcolab
    docker push mikirockerful/tracking-service-xcolab
    docker push mikirockerful/view-xcolab

    #eval "$(ssh-agent -s)"
    #ssh-add deploy_rsa
    #cat scripts/travis/cognosis2.known_host >> $HOME/.ssh/known_hosts

    #WORKER_IP="$(dig +short myip.opendns.com @resolver1.opendns.com)"

    #DEPLOY_FOLDER=xcolab/${TRAVIS_BRANCH}
    #DEPLOY_SERVER=binaries@cognosis2.mit.edu
    #echo "Copying binaries from ${WORKER_IP} to ${DEPLOY_SERVER}:${DEPLOY_FOLDER} ..."
    #rsync -r --delete-after --quiet binaries ${DEPLOY_SERVER}:${DEPLOY_FOLDER}

    ## Workaround for hanging builds
    ## taken from https://github.com/travis-ci/travis-ci/issues/8082#issuecomment-315147561
    #ssh-agent -k
else
    echo "Skipping deploy step on branch ${TRAVIS_BRANCH}"
fi
