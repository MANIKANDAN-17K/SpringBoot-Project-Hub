package com.example.taskmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TaskmanagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaskmanagerApplication.class, args);
		System.out.println("\nTask Manager API started!");
        System.out.println(" API Base URL: http://localhost:8080/api/tasks");
        System.out.println(" Open index.html in your browser to use the frontend\n");
	}

}
