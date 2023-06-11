# Challenge Maximilian

## Introduction
This readme file is supposed to give inside into my thought process throughout the challenge.

## Database
To get the same database that I used, just call docker-compose up in this folder. 
An init script will be called automatically and spin up the necessary data.

## Structure
For this project I have decided to choose a package structure where all the entities reside in a separate package with the corresponding repositories, services, dtos and Exceptions.

For each request and response I have created individual DTOs as I think this is the best way to decouple the software from what is exposed to the public.

Furthermore, I have created specific exceptions to adapt to the requested domain.

## Tests
The business logic is tested with unit tests, which for this example cover enough edge cases to demonstrate my testing ability.

For a bigger project more layers of tests should be implemented. For example integration tests.


## Further Implementation or Ideas
I have also some suggestions for further development.
### 1. Api versioning 
I used the specified api mapping, but it would be better if it was for example api/v1/bookings

### 2. Enums instead of Strings
From the insert statements, i kind of reverse engineered the database and i opted for having the container type as a plane varchar/String. In further development this should be an enum.

### 3. Integration Tests
As mentioned above the next step in testing would be integration tests.

### 4. Id and ContainerNumber
It seems as Id and ContainerNumber are both unique keys. Maybe they could be unified and the usage of one could be sufficient.
In the booking of a container the request object contains both fields. As both are unique I have used the id to identify the container. 
It seems redundant to me to use both in this case. 

## Improvements for the challenge
I have also some ideas to improve the challenge.

### 1. Usage of newer Versions
It is specifically requested to use Java 11 with Spring Boot 2.7
Spring Boot 2.7 will only be OSS supported for some other months, so I think it would make more sense to use the most up to date version of each.

### 2. Provide Database container ready
The setting up of the database is a bit time-consuming if you only get the insert statements. 
It is not difficult, but a bit tedious. I would have preferred to get the database completely ready and build my application from there. 
It also guarantees compatibility with the exact database you have.

### 3. Specify usage of DTOs
In the response object specification the entity itself is named. I opted for using DTOs, because it is better practice. 
Maybe it was better to specify somewhere in the text to use DTOs or feel free to use them. 

### 4. Use GitHub instead of zip
Another thing that gives a more modern experience would be to share the code over GitHub. 
It further enables to show the thought and work process based on comments.
