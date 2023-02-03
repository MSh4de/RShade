FROM openjdk:8-alpine
MAINTAINER RealAlphaUA
WORKDIR . /rshade
COPY /target/RShade-1.8.jar RShade-1.8.jar
EXPOSE 25565

ENTRYPOINT ["java","-jar","RShade-1.8.jar"]
