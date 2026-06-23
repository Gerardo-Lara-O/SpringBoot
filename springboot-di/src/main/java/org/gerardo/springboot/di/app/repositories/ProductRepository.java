package org.gerardo.springboot.di.app.repositories;

import java.util.List;

import org.gerardo.springboot.di.app.models.Product;

public interface ProductRepository {
    List<Product> findAll();
    Product findById(Long id);
}
