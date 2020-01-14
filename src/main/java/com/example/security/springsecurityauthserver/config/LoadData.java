package com.example.security.springsecurityauthserver.config;

import com.example.security.springsecurityauthserver.model.Student;
import com.example.security.springsecurityauthserver.model.UserRole;
import com.example.security.springsecurityauthserver.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
@Component
public class LoadData implements CommandLineRunner {
    @Autowired
    StudentRepository studentRepository;

    @Override
    public void run(String... args) {
        System.out.println("*******************************CommandLineRunner");

        if (studentRepository.count() == 0) {
            studentRepository.save(new Student(2, "admin2",
                    "admin2",
                    Arrays.asList(new UserRole("USER"), new UserRole("ADMIN"))));
        }
    }
}
