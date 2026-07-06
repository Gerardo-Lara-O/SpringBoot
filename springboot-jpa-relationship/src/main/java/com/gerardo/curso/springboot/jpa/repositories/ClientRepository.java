package com.gerardo.curso.springboot.jpa.repositories;

import org.springframework.data.repository.CrudRepository;

import com.gerardo.curso.springboot.jpa.models.Client;

public interface ClientRepository extends CrudRepository<Client, Long> {
    
}
