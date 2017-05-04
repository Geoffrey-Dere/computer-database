#!/bin/bash

#Geoffrey Deremetz
#Create images & containers


#images
for variable in 'mysql' 'tomcat' 'jenkins' 'maven'	
do
if [[ "$(docker images -q $variable:latest)" == "" ]]; then
docker build -t $variable $variable/
fi
done


#containers
if [ ! "$(docker ps -q -f name=mysqlprod)" ]; then
    if [ "$(docker ps -aq -f status=exited -f name=mysqlprod)" ]; then
      docker rm mysqlprod
    fi
   docker run -d --name=mysqlprod mysql:latest
   docker network connect my-network mysqlprod
fi

if [ ! "$(docker ps -q -f name=tomcatprod)" ]; then
    if [ "$(docker ps -aq -f status=exited -f name=tomcatprod)" ]; then
      docker rm tomcatprod
    fi
   docker run -d --name=tomcatprod tomcat:latest
   docker network connect my-network tomcatprod
fi

#if [ ! "$(docker ps -q -f name=jenkins)" ]; then
 #   if [ "$(docker ps -aq -f status=exited -f name=jenkins)" ]; then
 #     docker rm jenkins
 #   fi
 #  docker run -d -p 8080:8080 --name=myjenkins jenkins:latest
#   docker network connect my-network myjenkins
#fi
