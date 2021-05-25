#!/bin/sh

cd `dirname $0`

# exec java ${JAVA_OPTS} -cp /app:/app/lib/* com.example.zipcodes.ZipcodesApplication ${@}
exec java ${JAVA_OPTS} org.springframework.boot.loader.JarLauncher ${@}
