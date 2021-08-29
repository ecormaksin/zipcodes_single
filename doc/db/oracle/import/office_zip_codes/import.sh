#!/bin/sh

cd `dirname $0`

#ACCOUNT_STRING=ZIP_CODE/Password1234

sqlldr USERID="${ACCOUNT_STRING}" CONTROL='import.ctl'

#sqlplus ${ACCOUNT_STRING} @truncate_office_zip_codes.sql
#sqlplus ${ACCOUNT_STRING} @reset_sequence.sql
#sqlplus ${ACCOUNT_STRING} @insert_into_office_zip_codes.sql
