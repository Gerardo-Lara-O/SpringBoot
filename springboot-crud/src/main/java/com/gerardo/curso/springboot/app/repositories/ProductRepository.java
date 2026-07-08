package com.gerardo.curso.springboot.app.repositories;

import org.springframework.data.repository.CrudRepository;

import com.gerardo.curso.springboot.app.models.Product;

public interface ProductRepository extends CrudRepository<Product, Long> {
    
}
