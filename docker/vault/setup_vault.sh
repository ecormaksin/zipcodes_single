#!/bin/sh

cd `dirname $0`

export VAULT_DEV_ROOT_TOKEN_ID=`cat ./vault_passwd`
rm -f ./vault_passwd

PS_RESULT=`ps aux | grep "vault server" | grep -v grep`
if [ -n "${PS_RESULT}" ]; then
	exit 0
fi

export VAULT_API_ADDR='http://0.0.0.0:8200'
exec vault server -dev -dev-root-token-id="${VAULT_DEV_ROOT_TOKEN_ID}" -dev-listen-address="0.0.0.0:8200"
