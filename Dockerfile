FROM gcr.io/google-appengine/openjdk:8
RUN mvn package
COPY target/profile-service-0.0.1-SNAPSHOT.jar $APP_DESTINATION

#The following lines would be required while running locally as
#spring needs to reach out to cloud datastore and other services.
# TODO make it uniform by trying pass the following from outside of this docker file.e.g. during cloud run?
# COPY cloud-on-air-2019-dd836edc2ecc.json /key.json
# ENV GOOGLE_APPLICATION_CREDENTIALS=/key.json