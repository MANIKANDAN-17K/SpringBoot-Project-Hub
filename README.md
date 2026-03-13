# Task Manager REST API

A Spring Boot REST API assignment implementing full CRUD operations for task management, with an in-memory ArrayList as the data store and a simple HTML/JS frontend.

---

## Tech Stack

- **Java 17+**
- **Spring Boot 3.2.x**
- **Spring Web** (only dependency required)
- **Maven**
- **Embedded Tomcat** (no server setup needed)

---

## Project Structure

```
taskmanager/
├── pom.xml
├── index.html                          ← Frontend UI (open in browser)
└── src/
    └── main/
        ├── java/com/example/taskmanager/
        │   ├── TaskManagerApplication.java     ← Entry point
        │   ├── model/
        │   │   └── Task.java                   ← Task data model
        │   ├── service/
        │   │   └── TaskService.java            ← Business logic + dummy data
        │   └── controller/
        │       └── TaskController.java         ← REST endpoints
        └── resources/
            └── application.properties          ← Server config
```

---

## Getting Started

### 1. Prerequisites

- Java 17 or 21 installed
- Eclipse IDE with Spring Tools (or any Java IDE)
- Maven (bundled with Eclipse)

### 2. Import into Eclipse

1. Unzip the project folder
2. Open Eclipse → **File → Import → Existing Maven Project**
3. Browse to the `taskmanager` folder → Click **Finish**
4. Wait for Maven to download dependencies (first time only)

### 3. Run the Application

Right-click `TaskManagerApplication.java` → **Run As → Spring Boot App**

You should see in the console:
```
✅ Task Manager API started!
📋 API Base URL: http://localhost:8080/api/tasks
```

### 4. Open the Frontend

Double-click `index.html` to open it in your browser. Make sure the Spring Boot server is running first.

---

## API Endpoints

Base URL: `http://localhost:8080/api/tasks`

| Method | Endpoint | Description | Status Codes |
|--------|----------|-------------|--------------|
| GET | `/api/tasks` | Get all tasks | 200 OK |
| GET | `/api/tasks/{id}` | Get task by ID | 200 OK / 404 Not Found |
| POST | `/api/tasks` | Create new task | 201 Created / 400 Bad Request |
| PUT | `/api/tasks/{id}` | Update existing task | 200 OK / 404 Not Found |
| DELETE | `/api/tasks/{id}` | Delete task | 200 OK / 404 Not Found |

---

## Task Model

```json
{
  "id": 1,
  "title": "Buy groceries",
  "description": "Milk, eggs, bread",
  "completed": false,
  "createdAt": "2024-01-15T10:30:00"
}
```

| Field | Type | Required | Notes |
|-------|------|----------|-------|
| `id` | Long | Auto | Set by server, do not send in POST |
| `title` | String | Yes | Cannot be empty |
| `description` | String | No | Optional details |
| `completed` | boolean | No | Defaults to false |
| `createdAt` | LocalDateTime | Auto | Set by server on creation |

---

## Example Requests

### Create a Task (POST)
```bash
curl -X POST http://localhost:8080/api/tasks \
  -H "Content-Type: application/json" \
  -d '{"title": "Study Spring Boot", "description": "Learn REST APIs", "completed": false}'
```

Response `201 Created`:
```json
{
  "id": 6,
  "title": "Study Spring Boot",
  "description": "Learn REST APIs",
  "completed": false,
  "createdAt": "2024-01-15T10:30:00"
}
```

### Get All Tasks (GET)
```bash
curl http://localhost:8080/api/tasks
```

### Update a Task (PUT)
```bash
curl -X PUT http://localhost:8080/api/tasks/1 \
  -H "Content-Type: application/json" \
  -d '{"title": "Updated title", "completed": true}'
```

### Delete a Task (DELETE)
```bash
curl -X DELETE http://localhost:8080/api/tasks/1
```

---

## Key Annotations Explained

| Annotation | Used In | Purpose |
|------------|---------|---------|
| `@SpringBootApplication` | Main class | Enables auto-config, component scanning, bean registration |
| `@RestController` | TaskController | Combines `@Controller` + `@ResponseBody` — all methods return JSON |
| `@RequestMapping("/api/tasks")` | TaskController | Sets base URL for all endpoints in the class |
| `@CrossOrigin("*")` | TaskController | Enables CORS so the HTML frontend can call the API |
| `@Autowired` | Constructor | Spring auto-injects the `TaskService` dependency |
| `@GetMapping` | Methods | Maps HTTP GET requests |
| `@PostMapping` | Methods | Maps HTTP POST requests |
| `@PutMapping("/{id}")` | Methods | Maps HTTP PUT requests |
| `@DeleteMapping("/{id}")` | Methods | Maps HTTP DELETE requests |
| `@PathVariable` | Parameters | Extracts `{id}` from the URL path |
| `@RequestBody` | Parameters | Reads JSON body and converts it to a `Task` object |
| `@Service` | TaskService | Marks as a Spring-managed singleton bean |
| `ResponseEntity<>` | Return types | Full control over HTTP status code and response body |

---

## Architecture

```
HTTP Request
     │
     ▼
TaskController          ← Handles HTTP in/out (@RestController)
     │
     │ calls
     ▼
TaskService             ← Business logic (@Service)
     │
     │ reads/writes
     ▼
ArrayList<Task>         ← In-memory storage (resets on restart)
```

> **Note:** Data is stored in memory only. All tasks reset every time you restart the server. A real application would use a database (e.g. MySQL with Spring Data JPA).

---

## Dummy Data

The app pre-loads 5 sample tasks on startup (defined in `TaskService` constructor) so the API is not empty on first run.

---

## Dependency (Spring Initializr)

Only **one** dependency is needed when creating the project at [start.spring.io](https://start.spring.io):

```
Spring Web  →  spring-boot-starter-web
```

This single dependency bundles: Spring MVC, embedded Tomcat, Jackson (JSON), and Spring Core.

---

## HTTP Status Codes Used

| Code | Meaning | When returned |
|------|---------|---------------|
| `200 OK` | Success | GET, PUT, DELETE operations succeed |
| `201 Created` | Resource created | POST creates a new task |
| `400 Bad Request` | Invalid input | POST with empty title |
| `404 Not Found` | Resource missing | ID doesn't exist in GET, PUT, DELETE |

---

## Troubleshooting

**Port already in use**
Change the port in `src/main/resources/application.properties`:
```properties
server.port=8081
```
Then update the API URL in `index.html` from `8080` to `8081`.

**Frontend shows "Cannot connect to server"**
Make sure Spring Boot is running. Check the Eclipse console for errors.

**CORS error in browser**
`@CrossOrigin("*")` is already added to the controller. If issues persist, try opening `index.html` via a local server instead of directly from the filesystem.
