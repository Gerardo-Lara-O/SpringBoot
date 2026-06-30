package com.gerardo.curso.springboot.jpa.repositories;

import org.springframework.data.repository.CrudRepository;

import com.gerardo.curso.springboot.jpa.entities.Person;

public interface PersonRepository extends CrudRepository<Person, Long>{
    
}
