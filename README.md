# Template for micro-service in Java #
[![Build Status](https://dev.azure.com/butzist/DevOpsDemo/_apis/build/status/DevOpsDemoTF.DevOpsDemo-template-Java?branchName=master)](https://dev.azure.com/butzist/DevOpsDemo/_build/latest?definitionId=8&branchName=master)

### Description ###
Micro-service template to use with my [DevOpsDemo](https://github.com/DevOpsDemoTF/DevOpsDemo)

### Features ###
* Build in multi-stage Docker container
* Configuration via environment variables (as far as supported by Spring)
* Logging in JSON
* State passed to API handlers (via dependency injection)
* Health-check endpoint
* Prometheus metrics
* Unit tests with JUnit-compatible output
* API/integration tests with docker-compose

### Links ###
* [Java multi-stage Docker build](http://paulbakker.io/java/docker-gradle-multistage/)
* [Structured logging with JSON](https://stackoverflow.com/questions/54934658/how-to-write-slf4j-over-logback-logs-as-json)
* [Spring RESTful services](https://spring.io/guides/gs/rest-service/)
* [Prometheus metrics actor](https://docs.spring.io/spring-boot/docs/current/reference/html/production-ready-metrics.html#production-ready-metrics-export-prometheus)
* [Custom health-check](https://pivotal.io/application-modernization-recipes/observability/custom-health-checks-when-using-spring-boot-actuator)
