package org.gerardo.springboot.di.app.repositories;

import java.util.Collections;
import java.util.List;

import org.gerardo.springboot.di.app.models.Product;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

// @Primary
@Repository("productFoo")
public class ProductRepositoryFoo implements ProductRepository{

    @Override
    public List<Product> findAll() {
        return Collections.singletonList(new Product(1L,"Monitro Asus 27", 600L));
    }

    @Override
    public Product findById(Long id) {
        return new Product(1L,"Monitro Asus 27", 600L);
    }
    
}
