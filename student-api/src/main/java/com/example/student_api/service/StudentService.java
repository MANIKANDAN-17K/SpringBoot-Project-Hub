package com.example.student_api.service;


import org.springframework.stereotype.Service;
import com.example.student_api.model.Student;
import com.example.student_api.exception.ResourceNotFoundException;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class StudentService {

    private List<Student> students = new ArrayList<>();
    private AtomicLong counter = new AtomicLong();

    public StudentService() {
        students.add(new Student(counter.incrementAndGet(), "John", "john@mail.com", 20));
        students.add(new Student(counter.incrementAndGet(), "Emma", "emma@mail.com", 22));
    }

    public List<Student> getAllStudents() {
        return students;
    }

    public Student getStudentById(Long id) {
        return students.stream()
                .filter(student -> student.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + id));
    }

    public Student addStudent(Student student) {
        student.setId(counter.incrementAndGet());
        students.add(student);
        return student;
    }

    public void deleteStudent(Long id) {
        Student student = getStudentById(id);
        students.remove(student);
    }

    public List<Student> searchByName(String name) {
        return students.stream()
                .filter(student -> student.getName().equalsIgnoreCase(name))
                .toList();
    }
}
