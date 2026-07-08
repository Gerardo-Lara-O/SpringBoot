package com.gerardo.curso.springboot.app.services;

import java.util.List;
import java.util.Optional;

import com.gerardo.curso.springboot.app.models.Product;

public interface ProductService {

    List<Product> findAll();

    Optional<Product> findById(Long id);

    Product save(Product product);

    Optional<Product> delete(Product product);
    
}
