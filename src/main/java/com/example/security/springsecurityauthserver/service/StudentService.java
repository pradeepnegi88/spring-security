package com.example.security.springsecurityauthserver.service;

import com.example.security.springsecurityauthserver.model.Student;
import com.example.security.springsecurityauthserver.model.UserRole;
import com.example.security.springsecurityauthserver.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Arrays;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    public Student addStudent(Student student) {
        student.setRoles(Arrays.asList(new UserRole("USER")));
//        student.setPassword(passwordEncoder.encode(student.getPassword()));
        return studentRepository.save(student);
    }

    public Iterable<Student> getAllStudents() {
          Student student = studentRepository.findStudentByName("admin2");
        System.out.println("*******************************" + student+" " );
             return    studentRepository.findAll();
    }

    /**
     * set up a default user with two roles USER and ADMIN
     */
    @PostConstruct
    private void setupDefaultUser() {
        System.out.println("*******************************setupDefaultUser"  );

        if (studentRepository.count() == 0) {
            studentRepository.save(new Student(2,"admin2",
                 "admin2",
                    Arrays.asList(new UserRole("USER"), new UserRole("ADMIN"))));
        }
    }

}
