package com.gerardo.curso.springboot.jpa.repositories;

import org.springframework.data.repository.CrudRepository;

import com.gerardo.curso.springboot.jpa.models.Course;

public interface CourseRepository extends CrudRepository<Course, Long>{
    
}
