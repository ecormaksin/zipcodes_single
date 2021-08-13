#!/bin/sh

cd `dirname $0`

export JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64
./gradlew -q clean build bootRun -x test
