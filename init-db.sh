#!/usr/bin/env bash

function usage() {
    echo "Usage: ./init-db.sh <DB username>"
}

function create() {
    echo "Creating Tables.."
    echo "****************************************"
}

function finish() {
    echo "****************************************"
    echo "Finished!"
}

if [ -z $1 ];
then
    usage
else
    create
    psql -v ON_ERROR_STOP=1 -1 -h localhost -U $1 -f create_tables.sql -d soren_subscriber
    finish
fi


