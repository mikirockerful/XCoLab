#!/bin/sh

# Wait for database to be accessible
database_host=$(grep db\.url\.base /root/.xcolab.application.properties | awk -F '//' '{print $2}' | awk -F ':' '{print $1}')
database_port=$(grep db\.url\.base /root/.xcolab.application.properties | awk -F '//' '{print $2}' | awk -F ':' '{print $2}')
database_user=$(grep db\.user /root/.xcolab.application.properties | awk -F '=' '{print $2}')
database_password=$(grep db\.password /root/.xcolab.application.properties | awk -F '=' '{print $2}')

database_accessible=0
while [[ "$database_accessible" == "0" ]]; do
    mysqladmin -h $database_host -P $database_port -u $database_user --password=$database_password status > /dev/null 2&>1
    if [ $? -eq 0 ]
    then
        database_accessible=1
        echo "Database connection test OK"
    else
        echo "Database connection test NOK. Retrying."
        sleep 3
    fi
done

JAVA_OPTS="-Dserver.port=18091 -Xmx1G -Xms256M -XX:-OmitStackTraceInFastThrow -Dspring.profiles.active=docker"

exec java -jar $JAVA_OPTS /members-service-1.0-SNAPSHOT.jar
