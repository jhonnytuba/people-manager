#!/bin/bash

DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" > /dev/null && pwd)"

bash $DIR/build-image.sh

docker login
docker push jhonnytuba/people-manager:latest
