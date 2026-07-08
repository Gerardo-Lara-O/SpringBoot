package com.gerardo.curso.springboot.jpa.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.gerardo.curso.springboot.jpa.models.ClientDetail;

@Repository
public interface ClientDetailRepository extends CrudRepository<ClientDetail, Long>{
    
}
