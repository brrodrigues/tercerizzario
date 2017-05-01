#!/bin/bash

echo "RUNNING EUREKA SERVER..."
./run-server &

echo "RUNNING GATEWAY..."
.run-gateway.sh &

echo "RUNNING SUPPLIERS APPLICATION..."
./run-supplier.sh &

echo "RUNNING SUPPLIERS LOCATOR APLICATION..."
./run-locator.sh &

echo "RUNNING PROFESSIONS APPLICATION..."
./run-profession.sh &
