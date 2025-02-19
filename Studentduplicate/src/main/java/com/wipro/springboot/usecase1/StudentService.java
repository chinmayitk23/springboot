package com.wipro.springboot.usecase1;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepository repository;

    public StudentService(StudentRepository repository) {
        this.repository = repository;
    }

    public String addStudent(Student student) {
        List<Student> students = repository.findAll();

        if (students.size() >= 10) {
            return "Cannot add more students. Maximum limit of 10 reached.";
        }

        Optional<Student> existingStudent = repository.findByRollNumber(student.getRollNumber());
        if (existingStudent.isPresent()) {
            return "Roll number must be unique. Student with this roll number already exists.";
        }

        repository.save(student);
        return "Student added successfully!";
    }

    public List<Student> getStudents() {
        return repository.findAll();
    }
}
