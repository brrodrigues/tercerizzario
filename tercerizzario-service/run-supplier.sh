#!/bin/bash

NUM_ARGS=$#

LOG_RUN=~/app/logs/$1.log
LOG_COMPILE_MAVEN=~/app/logs/"${1}_mvn_compile.log"


if [ "${NUM_ARGS}" = "0" ]; then
	echo 'The first argument expected is log filename'
	exit -1
fi

#echo 'FINDING LOG FILE'

#if [ ! -f ~/app/logs ]; then
#	mkdir -p ~/app/logs
#fi

#echo 'CREATING FILE LOG...'

#touch ${LOG}

#echo "${LOG}"

echo "COMPILING PROJECT W/ MAVEN"

rm $LOG_COMPILE_MAVEN >> /dev/null

cd tercerizzario-service-server

mvn clean package >> $LOG_COMPILE_MAVEN

COMPILED=$(grep 'BUILD SUCCESS' $LOG_COMPILE_MAVEN | wc -l)

if [ "${COMPILED}" != "1"  ]; then
	echo "OCURR ERROR DURING COMPILATION.."
	echo "SEE MORE IN ${LOG_COMPILE_MAVEN}"
	exit -1
fi

echo 'STARTING EUREKA SERVER...'

rm $LOG_RUN

mvn spring-boot:run >> $LOG_RUN

echo 'DONE'

sleep 1

echo "TYPE "tail -f -n1000 ${LOG_RUN}" IN THE TERMINAL"
