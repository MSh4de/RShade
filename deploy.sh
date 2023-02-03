#!/bin/bash

function prop {
    grep "${1}" target/maven-archiver/pom.properties|cut -d'=' -f2
}

docker build -t $REGISTRY_HOST/mshade-production/$(prop 'artifactId'):$(prop 'version') .
docker images
docker push $REGISTRY_HOST/mshade-production/$(prop 'artifactId'):$(prop 'version')
