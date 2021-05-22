#!/bin/sh

cd `dirname $0`

if [ -n "${SPRING_DATASOURCE_URL}"]; then
    echo "環境変数: SPRING_DATASOURCE_URL を指定してください。"
    exit 1
fi

if [ -n "${SPRING_DATASOURCE_USERNAME}"]; then
    echo "環境変数: SPRING_DATASOURCE_USERNAME を指定してください。"
    exit 1
fi

if [ -n "${SPRING_DATASOURCE_PASSWORD}"]; then
    echo "環境変数: SPRING_DATASOURCE_PASSWORD を指定してください。"
    exit 1
fi

if [ -n "${LOGGING_FILE_NAME}"]; then
    echo "環境変数: LOGGING_FILE_NAME を指定してください。"
    exit 1
fi

java -jar ./zipcodes.jar
