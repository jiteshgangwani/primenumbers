FROM openjdk:8
ADD target/primenumber-demo-0.0.1-SNAPSHOT.jar primenumber-demo-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "primenumber-demo-0.0.1-SNAPSHOT.jar"]
