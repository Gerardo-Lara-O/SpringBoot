package com.gerardo.curso.springboot.jpa.repositories;

import org.springframework.data.repository.CrudRepository;

import com.gerardo.curso.springboot.jpa.models.Student;

public interface StudentRepository extends CrudRepository<Student,Long>{
    
}
