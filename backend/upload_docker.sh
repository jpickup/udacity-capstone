#!/usr/bin/env bash
# Create a docker image
docker build --tag=udaroute .

# DockerHub
dockerpath=jwpickup/udacity:udaroute
echo "Docker ID and Image: $dockerpath"
docker tag udaroute $dockerpath
docker push $dockerpath

#ECR
# 073796236635.dkr.ecr.us-east-1.amazonaws.com/udaroute
ecrpath=073796236635.dkr.ecr.us-east-1.amazonaws.com/udaroute:latest
echo "ERC ID and Image: $ecrpath"
docker tag udaroute:latest $ecrpath
docker push $ecrpath