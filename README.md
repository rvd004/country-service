# country-service

Country service using [Spring Boot](http://projects.spring.io/spring-boot/)

## Requirements

For building and running the application you need:

- [JDK 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [Maven 3](https://maven.apache.org)

## Running the application locally

You can use [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html):

```shell
mvn clean install && spring-boot:run
```

## URLs to test the application:
- [Swagger](http://localhost:8080/swagger-ui.html)
- [Countries](http://localhost:8080/countries)
- [Country by Code](http://localhost:8080/countries/FIN)

## Copyright

Released under the Apache License 2.0. See the [LICENSE](https://github.com/codecentric/springboot-sample-app/blob/master/LICENSE) file.
