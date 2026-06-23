package org.gerardo.springboot.di.app.services;

import java.util.List;

import org.gerardo.springboot.di.app.models.Product;

public interface ProductService{
    List<Product> findAll();
    Product findById(Long id);
}
