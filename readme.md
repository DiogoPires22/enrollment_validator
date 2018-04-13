# Enrollment Validator

[![Build Status](https://travis-ci.org/DiogoPires22/enrollment_validator.svg?branch=master)](https://travis-ci.org/DiogoPires22/enrollment_validator)

This is my solution for the [problem](/problem.txt)

### Building and Installing

This project requires [Maven](https://maven.apache.org/) and [JavaSDK](http://www.oracle.com/technetwork/pt/java/javase/downloads/index.html) to run.


Clone project and go to project directory
```sh
$ git clone https://github.com/DiogoPires22/enrollment_validator.git

$ cd enrollment_validator
```
To compile the project use these commands
```sh
$ mvn compile
$ mvn package
```
The target directory will be created
### Run Project


To run the project after compiled use this command
```sh
cd /target/
java -jar enrollment_validator-1.0.jar {path where the files are}
```

Example
```sh
java -jar /target/enrollment_validator-1.0.jar /Users/diogosilva/Desktop/resources/
```

### Run Test
To run unit test use

```sh
$ mvn test
```



MIT


*Free Software, Hell Yeah!*
