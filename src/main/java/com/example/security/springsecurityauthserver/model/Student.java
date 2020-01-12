package com.example.security.springsecurityauthserver.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Student {
    @Id
    private Integer id;
    private String name;
    private String password;
    @OneToMany(fetch = FetchType.EAGER, cascade= CascadeType.ALL)
    private List<UserRole> roles;

    public Student(Integer id,String name, String password, List<UserRole> roles) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.roles = roles;
    }
    public Student(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<UserRole> getRoles() {
        return roles;
    }

    public void setRoles(List<UserRole> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
