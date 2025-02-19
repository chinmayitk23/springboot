package com.wipro.springboot.usecase1;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {
    private final StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
    }

    @PostMapping("/add")
    public ResponseEntity<String> addStudent(@RequestBody Student student) {
        String response = service.addStudent(student);

        if (response.startsWith("Cannot") || response.startsWith("Roll number must be unique")) {
            return ResponseEntity.badRequest().body(response);
        }

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public List<Student> getAllStudents() {
        return service.getStudents();
    }
}
