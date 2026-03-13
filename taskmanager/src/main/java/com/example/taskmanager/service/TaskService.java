package com.example.taskmanager.service;

import com.example.taskmanager.model.Task;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class TaskService {

    
    private final List<Task> tasks = new ArrayList<>();

    
    private final AtomicLong idCounter = new AtomicLong(1);

    public TaskService() {
        tasks.add(new Task(idCounter.getAndIncrement(), "Set up Spring Boot project",
                "Initialize project with Spring Initializr and configure pom.xml", true));

        tasks.add(new Task(idCounter.getAndIncrement(), "Build REST API endpoints",
                "Implement GET, POST, PUT, DELETE for /api/tasks", false));

        tasks.add(new Task(idCounter.getAndIncrement(), "Enable CORS for frontend",
                "Add @CrossOrigin so our HTML frontend can call the API", false));

        tasks.add(new Task(idCounter.getAndIncrement(), "Test with Postman",
                "Verify all endpoints return correct status codes", false));

        tasks.add(new Task(idCounter.getAndIncrement(), "Build frontend UI",
                "Create a simple HTML/JS frontend to interact with the API", false));
    }

    
    public List<Task> getAllTasks() {
        return new ArrayList<>(tasks);
    }

    
    public Optional<Task> getTaskById(Long id) {
        return tasks.stream()
                .filter(task -> task.getId().equals(id))
                .findFirst();
    }

    
    public Task createTask(Task task) {
        
        if (task.getTitle() == null || task.getTitle().trim().isEmpty()) {
            throw new IllegalArgumentException("Task title cannot be empty");
        }

        task.setId(idCounter.getAndIncrement());
        task.setCreatedAt(java.time.LocalDateTime.now());
        tasks.add(task);
        return task;
    }

    
    public Optional<Task> updateTask(Long id, Task updatedTask) {
        
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getId().equals(id)) {
                Task existing = tasks.get(i);

                
                if (updatedTask.getTitle() != null && !updatedTask.getTitle().trim().isEmpty()) {
                    existing.setTitle(updatedTask.getTitle());
                }
                if (updatedTask.getDescription() != null) {
                    existing.setDescription(updatedTask.getDescription());
                }
                existing.setCompleted(updatedTask.isCompleted());

                return Optional.of(existing);
            }
        }
        return Optional.empty(); 
    }

   
    public boolean deleteTask(Long id) {
        return tasks.removeIf(task -> task.getId().equals(id));
    }
}