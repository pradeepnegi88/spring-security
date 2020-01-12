package com.example.security.springsecurityauthserver.repository;

import com.example.security.springsecurityauthserver.model.Student;
import org.springframework.data.repository.CrudRepository;

public interface StudentRepository extends CrudRepository<Student,Integer> {
    Student findStudentByName(String name);
}
