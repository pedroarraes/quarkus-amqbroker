# A Guide to Creating and Consuming JMS Queues with Quarkus and AQM-Broker
This insightful tutorial provides step-by-step guidance on initializing the Red Hat AQM-Broker, complemented by a comprehensive Quarkus source code demonstration. Learn the essentials of creating and efficiently consuming a JMS Queue with ease. #quarkus-amqbroker

# Requeriments
* OpenJDK 17
* Apache Maven 3.8.6
* podman 4.5.1
* Red Hat AMQ Broker Image, version 7.10 


# Sumary



# Downloading Image and Initiating Container: Streamlining the Process for Seamless Deployment
The initial step involves logging in to the Red Hat Registry. If you don't have access to the Red Hat Registry, you have the option to use a community image. Following this, execute the subsequent steps: pull the image and initiate it in the foreground. 

1. Log in to the Red Hat Registry.
```bash
podman login -u <your_user> registry.redhat.io
```
```console
Login Succeeded!
```

2. Pulling the container image to localhost.
```bash
podman pull registry.redhat.io/amq7/amq-broker-rhel8:7.10.4
```
```console
Trying to pull registry.redhat.io/amq7/amq-broker-rhel8:7.10.4...
Getting image source signatures
Checking if image destination supports signatures
Copying blob d65979adae7a skipped: already exists  
Copying blob 44b6ad158ea3 skipped: already exists  
Copying blob c4024a5c52fc skipped: already exists  
Copying blob 395bceae1ad3 skipped: already exists  
Copying config 2900fc79c4 done   | 
Writing manifest to image destination
Storing signatures
2900fc79c491b2d800fda5ed27a939d2b1a8cd705005829b823fc904d8f9e420
```

3. Initiating the AQM Broker container while exposing ports.
```bash
podman run -d --name amq-broker -e AMQ_USER=admin -e AMQ_PASSWORD=admin -p 8161:8161 -p 5672:5672 registry.redhat.io/amq7/amq-broker-rhel8:7.10.4
```
```console
293c4a913f1ec19f65631821d5adbe54adc1a84224d76c9214c91e142463ffc6
```

4. Verifying the execution of the container
```bash
podman ps
```
```console
CONTAINER ID  IMAGE                                            COMMAND               CREATED             STATUS             PORTS                                           NAMES
293c4a913f1e  registry.redhat.io/amq7/amq-broker-rhel8:7.10.4  /opt/amq/bin/laun...  About a minute ago  Up About a minute  0.0.0.0:5672->5672/tcp, 0.0.0.0:8161->8161/tcp  amq-broker
```
Explore the AQM Broker by accessing it through your web browser. Utilize the credentials "admin" for the username and "admin" as the password, as specified in the parameters of the Podman command.

![Alt text](appendices/image01.png)

![Alt text](appendices/image02.png)

mvn clean package -P native

podman build -f src/main/docker/Dockerfile.native -t quarkus/quarkus-amqbroker .

podman run -i --rm -p 8080:8080 quarkus/quarkus-amqbroker



# quarkus-amqbroker

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: https://quarkus.io/ .

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:
```shell script
./mvnw compile quarkus:dev
```

> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at http://localhost:8080/q/dev/.

## Packaging and running the application

The application can be packaged using:
```shell script
./mvnw package
```
It produces the `quarkus-run.jar` file in the `target/quarkus-app/` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/quarkus-app/lib/` directory.

The application is now runnable using `java -jar target/quarkus-app/quarkus-run.jar`.

If you want to build an _über-jar_, execute the following command:
```shell script
./mvnw package -Dquarkus.package.type=uber-jar
```

The application, packaged as an _über-jar_, is now runnable using `java -jar target/*-runner.jar`.

## Creating a native executable

You can create a native executable using: 
```shell script
./mvnw package -Dnative
```

Or, if you don't have GraalVM installed, you can run the native executable build in a container using: 
```shell script
./mvnw package -Dnative -Dquarkus.native.container-build=true
```

You can then execute your native executable with: `./target/quarkus-amqbroker-1.0.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult https://quarkus.io/guides/maven-tooling.

## Related Guides

- SmallRye OpenAPI ([guide](https://quarkus.io/guides/openapi-swaggerui)): Document your REST APIs with OpenAPI - comes with Swagger UI
- RESTEasy Reactive ([guide](https://quarkus.io/guides/resteasy-reactive)): A Jakarta REST implementation utilizing build time processing and Vert.x. This extension is not compatible with the quarkus-resteasy extension, or any of the extensions that depend on it.
- Camel Simple JMS2 ([guide](https://camel.apache.org/camel-quarkus/latest/reference/extensions/sjms2.html)): Send and receive messages to/from a JMS Queue or Topic using plain JMS 2.x API

## Provided Code

### RESTEasy Reactive

Easily start your Reactive RESTful Web Services

[Related guide section...](https://quarkus.io/guides/getting-started-reactive#reactive-jax-rs-resources)
