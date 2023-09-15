#!/usr/bin/env bash

dockerpath=jwpickup/udacity:udaroute

kubectl create deployment udaroute --image=$dockerpath

kubectl get pod

kubectl port-forward deployment/udaroute 8081:8081