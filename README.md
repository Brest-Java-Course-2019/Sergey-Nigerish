[![Build Status](https://travis-ci.org/Brest-Java-Course-2019/Sergey-Nigerish.svg?branch=master)](https://travis-ci.org/Brest-Java-Course-2019/Sergey-Nigerish)
[![Coverage Status](https://coveralls.io/repos/github/Brest-Java-Course-2019/Sergey-Nigerish/badge.svg?branch=master)](https://coveralls.io/github/Brest-Java-Course-2019/Sergey-Nigerish?branch=master)

# Phone Station by Sergey Nigerish

Test Java project to study technologies such as git, maven, spring, JUnit, Thymeleaf, Bootstrap and others.

## Getting Started

### Prerequisites

install jdk8+

install git

install maven3+

### Installing

download project from github

```
git clone https://github.com/Brest-Java-Course-2019/Sergey-Nigerish.git
cd PhoneStation/
mvn clean install
```

## Running the tests

```
mvn test
```

## Deployment (with Jetty Server)
Start Rest server:

```
cd rest-app/
mvn jetty:run
```
Open new Terminal tab (Ctrl+Alt+T) and start Client:
```
cd PhoneStation/webb-app/
mvn jetty:run
```
If you don't change settings, open url: [localhost:8089](localhost:8089) or your external address and port 8089.

## Built With

* [Maven](https://maven.apache.org/) - Dependency Management

## License

This project is licensed under the MIT License
