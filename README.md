# Getting Started
## Setup

* Create a GCP Project 
* Setup Firestore in the Datastore Mode.
* Create a JSON key for local testing

## Build 
```
mvn clean package 

docker build . -t profile-service

docker run -p 8080:8080 profile-service
```
## Deploy
``` 
gcloud app deploy
``` 

## API
  ```
  GET https://8080-dot-2972582-dot-devshell.appspot.com/profiles

curl -v \
  -d '{"name":"Neil Ghosh", "country":"India", "birthYear":1990}' \
  -H "Content-Type: application/json" -X POST \
  https://cloud-on-air-2019.appspot.com/profiles

  ```

## Generate Load
  ```
  ab -p data.txt -T application/json -H  \
  "Content-Type: application/json" -c 100 -n 20000 \
  https://cloud-on-air-2019.appspot.com/profiles
  ```
## Clean Up
  ```
  curl -v -X DELETE  \
  http://localhost:8080/profiles

  curl -v -X DELETE  \
  https://cloud-on-air-2019.appspot.com/profiles
  ```

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.1.1.RELEASE/maven-plugin/)
* [GCP Support](https://cloud.spring.io/spring-cloud-gcp/reference/html/)

### Guides
The following guides illustrate how to use some features concretely:

* [GCP Samples](https://github.com/spring-cloud/spring-cloud-gcp/tree/master/spring-cloud-gcp-samples)

### Note 
This skeleton of the project was originally generated using the following 

```
curl https://start.spring.io/starter.tgz \
  -d packaging=war \
  -d dependencies=cloud-gcp \
  -d baseDir=profile-service \
  -d bootVersion=2.1.1.RELEASE | tar -xzvf -
```