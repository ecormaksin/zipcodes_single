#!/bin/sh

cd `dirname $0`

. ./setenv.sh

java -jar ./zipcodes.jar
