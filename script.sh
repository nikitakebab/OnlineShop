#!/bin/bash

mvn clean package

sudo docker build -t registry.heroku.com/desolate-dawn-04274/web -f target/docker/Dockerfile

sudo docker push registry.heroku.com/desolate-dawn-04274/web

heroku container:release web --app desolate-dawn-04274