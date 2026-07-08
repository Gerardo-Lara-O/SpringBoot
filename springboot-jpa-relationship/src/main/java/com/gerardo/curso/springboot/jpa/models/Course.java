package com.gerardo.curso.springboot.jpa.models;

import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "courses")
public class Course {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String instructor;

    
    // private Set<Student> students; lo guardamos para la relacion inversa
    // Constructor
    public Course(){}

    public Course(String name, String instructor) {
        this.name = name;
        this.instructor = instructor;
    }

    // Get y Set
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    @Override
    public String toString() {
        return "{id=" + id + ", name=" + name + ", instructor=" + instructor + "}";
    }

    
    


}
