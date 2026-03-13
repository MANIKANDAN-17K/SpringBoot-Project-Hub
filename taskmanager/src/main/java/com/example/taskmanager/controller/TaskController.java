package com.example.taskmanager.controller;

import com.example.taskmanager.model.Task;
import com.example.taskmanager.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/tasks")
@CrossOrigin(origins = "*")  
public class TaskController {

  
    private final TaskService taskService;

    
    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

        @GetMapping
    public ResponseEntity<List<Task>> getAllTasks() {
        List<Task> tasks = taskService.getAllTasks();
        return ResponseEntity.ok(tasks);  // 200 OK + JSON body
    }

    
    @GetMapping("/{id}")
    public ResponseEntity<?> getTaskById(@PathVariable Long id) {
        Optional<Task> task = taskService.getTaskById(id);

        if (task.isPresent()) {
            return ResponseEntity.ok(task.get());  // 200 OK
        } else {
            // Return 404 with a helpful error message
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", "Task not found with id: " + id));
        }
    }

    
    @PostMapping
    public ResponseEntity<?> createTask(@RequestBody Task task) {
        try {
            Task created = taskService.createTask(task);
            return ResponseEntity
                    .status(HttpStatus.CREATED)  // 201 Created
                    .body(created);
        } catch (IllegalArgumentException e) {
            // Validation failed (e.g., empty title)
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)  // 400 Bad Request
                    .body(Map.of("error", e.getMessage()));
        }
    }

    
    @PutMapping("/{id}")
    public ResponseEntity<?> updateTask(@PathVariable Long id, @RequestBody Task updatedTask) {
        Optional<Task> result = taskService.updateTask(id, updatedTask);

        if (result.isPresent()) {
            return ResponseEntity.ok(result.get());  // 200 OK
        } else {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)   // 404 Not Found
                    .body(Map.of("error", "Task not found with id: " + id));
        }
    }

    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable Long id) {
        boolean deleted = taskService.deleteTask(id);

        if (deleted) {
            return ResponseEntity.ok(Map.of("message", "Task deleted successfully"));  // 200 OK
        } else {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)   // 404 Not Found
                    .body(Map.of("error", "Task not found with id: " + id));
        }
    }
}