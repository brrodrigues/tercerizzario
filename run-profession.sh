#!/bin/bash

NUM_ARGS=$#

PROJECT_NAME="service-profession"
APPLICATION_PATH=/home/appuser/app/tercerizzario/tercerizzario-service
APPLICATION_NAME="tercerizzario-${PROJECT_NAME}"
LOG_APPLICATION="$APPLICATION_NAME.log"
LOG_COMPILE_MAVEN="${APPLICATION_NAME}_mvn_compile.log"

echo 'FINDING LOCATION PATH'

if [ ! -f ~/app/logs ]; then
   echo 'CREATING LOG PATH...'
   mkdir -p ~/app/logs
fi

echo "LOCATING ${APPLICATION_NAME} APPLICATION PROCESS ID..."

PID=$(ps -ef | grep "${APPLICATION_NAME}" | grep -v grep | awk '{print $2}')

if [ "${PID}x" = "x" ]; then
  echo "DO NOT EXIST ${APPLICATION_NAME} APPLICATION RUNNING" 
else
  echo "KILLING THE PROCESS ${PID} REFERENCE ${APPLICATION_NAME} APPLICATION"
  kill -9 ${PID}
  echo "DONE"
fi

#echo "COMPILING PROJECT WITH MAVEN"

#cd ${APPLICATION_PATH}/$APPLICATION_NAME

#mvn package >> $LOG_COMPILE_MAVEN

#COMPILED=$(grep -i 'BUILD FAILED' $LOG_COMPILE_MAVEN | wc -l)

#if [ "${COMPILED}x" != "0x"  ]; then
#	echo "OCURR ERROR DURING COMPILATION.."
#	echo "SEE MORE IN ${LOG_COMPILE_MAVEN}"
#	exit -1
#fi

TARGET=$(ls -a $APPLICATION_PATH/$APPLICATION_NAME/target/*.jar)

if [ ! -f ${TARGET} ]; then
  echo "${APPLICATION_NAME} APPLICATION NOT FOUND"
  exit -1
fi

echo "STARTING ${APPLICATION_NAME}..."

java -jar ${TARGET} >> ${LOG_APPLICATION} &
#mvn spring-boot:run >> ${LOG_APPLICATION} &

sleep 1

echo "TYPE tail -f -n1000 ${APPLICATION_PATH}/${APPLICATION_NAME}/${LOG_APPLICATION} AT TERMINAL"
