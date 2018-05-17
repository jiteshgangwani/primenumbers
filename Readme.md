# Prime Number Web Service

This rest web service generates prime number based on GET request.
It uses two algorithm for generating them
1. Default which check every number and checks if its a prime number.
2. [Sieve of Eratosthenes](https://en.wikipedia.org/wiki/Sieve_of_Eratosthenes) method which is a faster method as it uses array and elimnates checking non prime numbers.



### Swagger Api documentation

Once you run application on localhost

http://localhost:8080/swagger-ui.html


### Running application in browser/postman

**Default method**

http://localhost:8080/rest/prime/{number}

**Example:**
http://localhost:8080/rest/prime/110

**Sieve of Erathosthenes method**
http://localhost:8080/rest/prime/{number}/soe
**Example:**
http://localhost:8080/rest/prime/112/soe


### Prerequisites

Following software should already be installed on workstation for testing

```
JDK1.8 or higher
Maven

Docker(optional) - for creating independent docker image
```

### Buidling project

After downloading project from git hub move to primenumbers folder

```
mvn clean install
```

Once the command is sucessful it will generate **target/primenumber-demo-0.0.1-SNAPSHOT.war** file

### Running the tests

Following command will run both Unit and Integaration tests

```
mvn clean test
```

### Running the Appication

Following command will run application from project root folder

```
java -jar target/primenumber-demo-0.0.1-SNAPSHOT.war
```




## Optional Docker Deployment
Building docker image
```
docker build -f Dockerfile -t demo-docker .
```
Running docker image
```
  docker run -p 8080:8080 demo-docker
```

### Improvements can be made

1. Adding a Exception handling framwork for customized messages
2. Caching can be implemented in SOE method