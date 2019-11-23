### BUILD image

FROM maven:3-jdk-11 as builder

# create app folder for sources

RUN mkdir -p /build

WORKDIR /build

COPY pom.xml /build

#Download all required dependencies into one layer

RUN mvn -B dependency:resolve dependency:resolve-plugins

#Copy source code

COPY src /build/src

# Build application

RUN mvn package

FROM gcr.io/google-appengine/openjdk:8
COPY target/profile-service-0.0.1-SNAPSHOT.jar $APP_DESTINATION

#The following lines would be required while running locally as
#spring needs to reach out to cloud datastore and other services.
# TODO make it uniform by trying pass the following from outside of this docker file.e.g. during cloud run?
# COPY cloud-on-air-2019-dd836edc2ecc.json /key.json
# ENV GOOGLE_APPLICATION_CREDENTIALS=/key.json