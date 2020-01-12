package com.example.security.springsecurityauthserver.controller;

import com.example.security.springsecurityauthserver.model.Student;
import com.example.security.springsecurityauthserver.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping("student")
    public ResponseEntity<?> createStudent(@RequestBody Student student) {
        studentService.addStudent(student);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("student")
    public ResponseEntity<Iterable<Student>> getStudent() {
        return ResponseEntity.ok(studentService.getAllStudents());
    }
}
