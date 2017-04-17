#!/bin/bash

NUM_ARGS=$#

PROJECT_NAME="gateway"
UPPER_PROJECT_NAME=${PROJECT_NAME^^}
EUREKA_SERVER_PATH=/home/appuser/app/tercerizzario/tercerizzario-service
APPLICATION_NAME="tercerizzario-${PROJECT_NAME}"
LOG_APPLICATION="$APPLICATION_NAME.log"
LOG_COMPILE_MAVEN="${APPLICATION_NAME}_mvn_compile.log"

echo 'FINDING LOCATION PATH'

if [ ! -f ~/app/logs ]; then
   echo 'CREATING LOG PATH...'
   mkdir -p ~/app/logs
fi

echo "COMPILING PROJECT WITH MAVEN"

cd ${EUREKA_SERVER_PATH}/$APPLICATION_NAME

mvn clean package >> $LOG_COMPILE_MAVEN

COMPILED=$(grep -i 'BUILD FAILED' $LOG_COMPILE_MAVEN | wc -l)

if [ "${COMPILED}x" != "0x"  ]; then
	echo "OCURR ERROR DURING COMPILATION.."
	echo "SEE MORE IN ${LOG_COMPILE_MAVEN}"
	exit -1
fi

echo "LOCATING ${UPPER_PROJECT_NAME} APPLICATION PROCESS ID..."

PID=$(ps -ef | grep "${APPLICATION_NAME}" | grep -v grep | awk '{print $2}')

if [ "${PID}x" = "x" ]; then
  echo "DO NOT EXIST ${UPPER_PROJECT_NAME} APPLICATION SERVER RUNNING" 
else
  echo "KILLING THE PROCESS ${PID} REFERENCE ${UPPER_PROJECT_NAME}"
  kill -9 ${PID}
  echo "DONE"
fi

#TARGET=$(ls -a ${EUREKA_SERVER_PATH}/$APPLICATION_NAME/target/)
#
#if [ ! -f ${TARGET} ]; then
#  echo "${UPPER_PROJECT_NAME} APPLICATION NOT FOUND"
#  exit -1
#fi

echo "STARTING ${UPPER_PROJECT_NAME}"

mvn spring-boot:run >> ${LOG_APPLICATION} &

sleep 1

echo "TYPE tail -f -n1000 ${LOG_APPLICATION} AT TERMINAL"