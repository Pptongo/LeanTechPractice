# Lean Tech Practice
This project is used as a test practice only for Lean Tech. The content of the project will not be used for other purpose.

## Dependencies
To run the project its necesary to have installe the following dependencies:

1. [Maven](https://maven.apache.org/)
2. [Java 11](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)

## Run project
To run and test the project, it's necesary follow the next steps:

1. Clone the project in your local machine
2. Run the command `cmd mvn install` To download and install all the dependencies
3. Run the command `mvn spring-boot:run` This will be initialize the embebed Tomcat and enable to test the project.

The project is using an H2 in memory database, that means that everytime you launch the application the database will created again. You don't need to worry about the database creation and connection, all are defined in `src/main/resources/application.properties` file.

## Run Unit Test methods
The project come with some unit test methods to test the application and validate the functionality of all methods.

To run the UnitTest methods it's necesary run the command `mvn test`, once the methods finished you will get the results and you will be able to validate the functionlity of the project.

If you want to run a single test method, you can run the following command: `mvn -Dtest=PracticeExerciseApplicationTests#methodname test` replace methodname with one of the following:

> |Test methods|Description|
> |-|-|
> |testGetAllEmployees|Check the WebServices to get the full list of employees|
> |testEmployeesFilteredByName|Check the WebService to get a filtered by name employees list|
> |testEmployeesFilteredByPosition|Check the WebService to get a filtered by position name employees list|
> |testEmployeesFilteredByNameAndPosition|Check the WebService to get a filtered by position and name employees list|
> |testSaveNewEmployee|Check the WebService to save a new Employee|
> |testUpdateEmployee|Check the WebService to updated an Employee|
> |testDeleteUser|Check the WebService to delete an Employee|
> |testDeleteNonExistingUser|Check the WebService to delete a non exists Employee|
> |testSaveEmployeeWithBadPosition|Check the WebService to save a new Employee with a non existing Position|