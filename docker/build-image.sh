#!/bin/bash

DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" > /dev/null && pwd)"

cd $DIR/../backend/ && mvn clean install
cd $DIR/../frontend/ && npm run build

cd $DIR/../ && docker build -t jhonnytuba/people-manager -f $DIR/Dockerfile .
