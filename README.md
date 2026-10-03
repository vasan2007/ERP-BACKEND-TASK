# Student Management REST API

## Project Description

A Student Management REST API developed using Java Spring Boot.

## Technologies

- Java
- Spring Boot
- Spring Web
- Spring Data JPA
- MySQL Driver
- Validation
- Exception Handling

## Features

Add student  
Get all students  
Get student by ID  
Update student  
Delete student  
Search students  
Filter students  
Validation  
Duplicate register number handling  
Exception handling  
Department management  
Student statistics

## Student APIs

GET `/api/students`

GET `/api/students/{id}`

POST `/api/students`

PUT `/api/students/{id}`

DELETE `/api/students/{id}`

GET `/api/students/search?name=Arun`

GET `/api/students/search?registerNo=23IT001`

GET `/api/students?department=IT`

GET `/api/students?year=3`

GET `/api/students?semester=5`

GET `/api/students?department=IT&year=3`

GET `/api/students/statistics`

## Department APIs

GET `/api/departments`

GET `/api/departments/{id}`

POST `/api/departments`

PUT `/api/departments/{id}`

DELETE `/api/departments/{id}`

GET `/api/departments/{id}/students`

## Database

MySQL is used as the database.

## Validation

Student details are validated before storing them in the database.

## Exception Handling

The project handles:
Student not found  
Duplicate register number  
Invalid request

## How to Run

1. Configure MySQL.
2. Update `application.properties`.
3. Tables are automatically created or updated using `ddl-auto=update`.
4. Run the Spring Boot application.
5. Test the APIs using Postman.

## Screenshots

API screenshots are available in the `screenshots` folder.
