[![Build Status](https://travis-ci.org/Brest-Java-Course-2019/Sergey-Nigerish.svg?branch=master)](https://travis-ci.org/Brest-Java-Course-2019/Sergey-Nigerish)
[![Coverage Status](https://coveralls.io/repos/github/Brest-Java-Course-2019/Sergey-Nigerish/badge.svg?branch=master)](https://coveralls.io/github/Brest-Java-Course-2019/Sergey-Nigerish?branch=master)

# Delivery Cost by Sergey Nigerish

Test Java project to study technologies such as git, maven, continuous integration, continuous delivery and others.

## Getting Started

### Prerequisites

install jdk8+
install git
install maven3+

### Installing

download project from github

```
git clone https://github.com/Brest-Java-Course-2019/Sergey-Nigerish.git
mvn clean install
```

## Running the tests

```
mvn test
```

## Deployment

```
mvn site

mvn site:stage

check: <project>/target/staging/index.html
```

## Built With

* [Maven](https://maven.apache.org/) - Dependency Management

## License

This project is licensed under the MIT License
