#!/bin/sh

cd `dirname $0`

VAULT_PASSWORD_FILE_NAME=vault_passwd
export VAULT_TOKEN=`cat "${VAULT_PASSWORD_FILE_NAME}"`
rm -f "${VAULT_PASSWORD_FILE_NAME}"

export VAULT_ADDR='http://zipcodes-vault:8200'

LOOP_COUNTER=1

while :
    do
        VAULT_STATUS_RESULT=`vault status 2>/dev/null | grep "Initialized     true"`
        if [ -n "${VAULT_STATUS_RESULT}" ] ; then
            break
        fi
        LOOP_COUNTER=$((LOOP_COUNTER+1))
        if [ $LOOP_COUNTER -gt 5 ]; then
            echo "Vaultとの接続に失敗しました。"
            exit 1
        fi
        sleep 5
    done

vault login "${VAULT_TOKEN}"

vault kv put secret/zipcodes/docker_vault @secrets.json

# exec java ${JAVA_OPTS} -cp /app:/app/lib/* com.example.zipcodes.ZipcodesApplication ${@}
exec java ${JAVA_OPTS} org.springframework.boot.loader.JarLauncher ${@}
