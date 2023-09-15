#!/usr/bin/env bash

# Step 1:
docker build --tag=udaroute .

# Step 2: 
docker image ls

# Step 3: 
docker run -p 8081:8081 udaroute
