#!/bin/bash

# Build the Java application
mvn clean package

# Build the Docker image
docker build -t weather-app .