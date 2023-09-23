#!/usr/bin/env bash

# Create a docker image
docker build --tag=udaroute .

# Check it's present
docker image ls

# Run it
docker run -p 8081:8081 udaroute
