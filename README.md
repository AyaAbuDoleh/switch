# switch
spring boot demo
The project is based on a small web service which uses the following technologies:
● Java 1.8, or later..
● Spring Boot
● MySQL
● Maven
● Hibernate
● JPA
You should be aware of the following conventions while you are working on this exercise:
All new entities should have an ID with type of Long and a date_created with type of
ZonedDateTime.
The architecture of the Restful service is built with the following components:
Request Model: Objects which are used for outside communication via the API.
Controller: Implements the processing logic of the web service, parsing of parameters and
validation of in- and outputs.
Service (Interface): Implements the business logic and handles the access to the
DataAccessObjects.
Service Implementation: Actual implementation of the business logic.
DataAccessObjects: Interface for the database. Inserts, updates, deletes and reads objects
from the database.
Domain Object [Repo]: Functional Objects which might be persisted in the database.
TestDrivenDevelopment is a good choice, but it’s up to you how you are testing your code.
**You should commit into your local git repository and include the commit history into the
final result.

Task#1:
Write a new Controller for maintaining cars (CRUD).
- Decide on your own how the methods should look like.
- Entity Car: Should have at least the following characteristics: license_plate,
seat_count, convertible, rating, engine_type (electric, gas, ...)
- Entity Manufacturer: Decide on your own if you will use a new table or just a string
column in the car table.
- Extend the DriverController to enable drivers to select a car they are driving
with.
- Extend the DriverController to enable drivers to deselect a car.
- Extend the DriverDo to map the selected car to the driver.
Add example data to resources/data.sql

Task#2
First come first serve: A car can be selected by exactly one ONLINE Driver. If a second
driver tries to select an already used car you should throw a CarAlreadyInUseException.

Task #3:
Make use of the filter pattern to implement an endpoint in the DriverController to get a list of
drivers with specific characteristics. Reuse the characteristics you implemented in task 1.

Task #4
Security: secure the API. It’s up to you how you are going to implement the security (Spring
Security or JWT)

NOTE: Please make sure to not submit any personal data with your test result. Personal
data is for example your name, your birthdate, email address etc.
