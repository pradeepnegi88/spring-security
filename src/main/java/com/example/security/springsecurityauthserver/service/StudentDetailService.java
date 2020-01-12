package com.example.security.springsecurityauthserver.service;

import com.example.security.springsecurityauthserver.model.Student;
import com.example.security.springsecurityauthserver.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class StudentDetailService implements UserDetailsService {
    @Autowired
    StudentRepository studentRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Student student = studentRepository.findStudentByName(userName);
        System.out.println("*******************************" + student+" "+userName );

        if (student == null) {
            throw new UsernameNotFoundException("UserName " + userName + " not found");
        }
        return new StudentDetail(student);
    }
}
