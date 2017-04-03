#!/bin/bash

NUM_ARGS=$#

PROJECT_NAME="supplier"
EUREKA_SERVER=/home/appuser/app/tercerizzario/tercerizzario-service
APPLICATION_NAME="tercerizzario-service-${PROJECT_NAME}"
LOG_APPLICATION="$APPLICATION_NAME.log"
LOG_COMPILE_MAVEN="${APPLICATION_NAME}_mvn_compile.log"

echo 'FINDING LOCATION PATH'

if [ ! -d ~/app/logs ]; then
   echo 'CREATING LOG PATH...'
   mkdir -p ~/app/logs
fi

echo "COMPILING PROJECT WITH MAVEN"

EUREKA_SERVER_PATH=$(pwd)

if [ -f $LOG_APPLICATION  ]; then
 rm $LOG_APPLICATION
fi

if [ -f $LOG_COMPILE_MAVEN ]; then
  rm $LOG_COMPILE_MAVEN
fi

mvn clean package >> $LOG_COMPILE_MAVEN

COMPILED=$(grep -i 'BUILD FAILED' $LOG_COMPILE_MAVEN | wc -l)

if [ "${COMPILED}x" != "0x"  ]; then
	echo "OCURR ERROR DURING COMPILATION.."
	echo "SEE MORE IN ${LOG_COMPILE_MAVEN}"
	exit -1
fi

echo 'LOCATING APPLICATION PROCESS ID...'

PID=$(ps -ef | grep "${APPLICATION_NAME}" | grep -v grep | awk '{print $2}')

if [ "${PID}x" = "x" ]; then
  echo 'DO NOT EXIST APPLICATION SERVICE' 
else
  echo "KILLING THE PROCESS ${PID} REFERENCE APPLICATION"
  kill -9 ${PID}
  echo "DONE"
fi

TARGET=$(ls -a ${EUREKA_SERVER_PATH}/$APPLICATION_NAME/target/${APPLICATION_NAME}*.jar)

if [ ! -f ${TARGET} ]; then
  echo 'SERVER APPLICATION NOT FOUND'
  exit -1
fi

echo 'STARTING ...'

nohup java -Xmx256m -Xss256k -jar ${TARGET} >> ${LOG_APPLICATION} &

sleep 1

echo "TYPE "tail -f -n1000 ${LOG_APPLICATION}" AT TERMINAL"
