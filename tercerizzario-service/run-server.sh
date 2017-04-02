#!/bin/bash

NUM_ARGS=$#

PROJECT_NAME="server"
SERVICE="tercerizzario-service-${PROJECT_NAME}"
LOG_RUN="~/app/logs/$SERVICE.log"
LOG_COMPILE_MAVEN="~/app/logs/${SERVICE}_mvn_compile.log"

echo 'FINDING LOCATION PATH'
if [ ! -f ~/app/logs ]; then
   echo 'CREATING...'
   mkdir -p ~/app/logs
fi

echo "COMPILING PROJECT WITH MAVEN"

rm $LOG_COMPILE_MAVEN

cd ${SERVICE}

mvn clean package >> $LOG_COMPILE_MAVEN

COMPILED=$(grep 'BUILD SUCCESS' $LOG_COMPILE_MAVEN | wc -l)

if [ "${COMPILED}" != "1"  ]; then
	echo "OCURR ERROR DURING COMPILATION.."
	echo "SEE MORE IN ${LOG_COMPILE_MAVEN}"
	exit -1
fi

echo 'STARTING EUREKA SERVER...'

rm $LOG_RUN

mvn spring-boot:run >> $LOG_RUN&

sleep 1

echo "TYPE "tail -f -n1000 ${LOG_RUN}" IN THE TERMINAL"
