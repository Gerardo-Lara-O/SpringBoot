package com.gerardo.curso.springboot.jpa.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.gerardo.curso.springboot.jpa.entities.Person;
import java.util.List;


public interface PersonRepository extends CrudRepository<Person, Long>{
    
    // Usando la nomenclatura Query method
    List<Person> findByProgrammingLanguage(String programmingLanguage);
    List<Person> findByProgrammingLanguageAndName(String programmingLanguage, String name);

    // Usando query personalizados con la anotacion @Query
    @Query("select p from Person p where p.programmingLanguage=?1")
    List<Person> buscarByProgrammingLanguage(String programmingLanguage);
}
