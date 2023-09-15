#!/usr/bin/env bash
dockerpath=jwpickup/udacity:udaroute

echo "Docker ID and Image: $dockerpath"

docker tag udaroute $dockerpath

docker push $dockerpath