🎓 Student Management REST API

A simple Spring Boot REST API for managing students.
This project demonstrates core Spring Boot concepts and real-world layered architecture without using a database.

🚀 Tech Stack

Java 17

Spring Boot

Spring Web (Spring MVC)

Maven

Embedded Tomcat

Jackson (JSON serialization)

📁 Project Structure
student-api
│
├── controller
│     └── StudentController.java
│
├── service
│     └── StudentService.java
│
├── model
│     └── Student.java
│
├── exception
│     └── ResourceNotFoundException.java
│
└── StudentApiApplication.java
Layer Responsibilities

Controller → Handles HTTP requests & responses

Service → Contains business logic

Model → Represents data structure

Exception → Custom error handling

📌 API Endpoints
1️⃣ Get All Students
GET /api/students

Response:

[
  {
    "id": 1,
    "name": "John",
    "email": "john@mail.com",
    "age": 20
  }
]
2️⃣ Get Student By ID
GET /api/students/{id}

Example:

GET /api/students/1
3️⃣ Search Students By Name
GET /api/students/search?name=John
4️⃣ Add New Student
POST /api/students

Request Body:

{
  "name": "Alice",
  "email": "alice@mail.com",
  "age": 23
}
5️⃣ Delete Student
DELETE /api/students/{id}
🧠 Concepts Covered

Spring Boot Auto Configuration

REST Architecture

@RestController

@RequestMapping

@GetMapping, @PostMapping, @DeleteMapping

@PathVariable

@RequestParam

@RequestBody

JSON Serialization & Deserialization (Jackson)

Layered Architecture

Dependency Injection (Constructor Injection)

Spring Beans

Custom Exception Handling

ResponseEntity

In-Memory Data Storage

⚙️ How to Run the Project

Clone the repository

Open in Eclipse

Right-click project → Run As → Spring Boot App

Access API at:

http://localhost:8080/api/students
📚 Learning Purpose

This project is designed to:

Understand Spring MVC flow

Practice clean layered architecture

Learn REST API development fundamentals

Prepare foundation before integrating database (JPA)

🔜 Future Improvements

Add Global Exception Handling (@ControllerAdvice)

Add Validation (@Valid)

Integrate H2 / MySQL database

Add JPA & Repository layer

Implement Logging

Add Unit Testing

Add Swagger Documentation

Add Spring Security

🎯 Project Level

Beginner to Early Backend Developer Level

This project builds a strong foundation for:

Real-world backend development

Interview preparation

Advanced Spring Boot concepts