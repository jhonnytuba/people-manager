#!/bin/bash

DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" > /dev/null && pwd)"

bash $DIR/build-image.sh

docker run -p 8081:80 jhonnytuba/people-manager
