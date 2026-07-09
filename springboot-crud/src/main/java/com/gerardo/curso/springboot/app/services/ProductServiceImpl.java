package com.gerardo.curso.springboot.app.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gerardo.curso.springboot.app.models.Product;
import com.gerardo.curso.springboot.app.repositories.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository productRepository;

    @Transactional(readOnly = true)
    @Override
    public List<Product> findAll() {
        return  (List<Product>) productRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
        
    }

    @Transactional
    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Transactional
    @Override
    public Optional<Product> delete(Long id) {
        Optional<Product> producOptional = productRepository.findById(id);
        producOptional.ifPresent(productDb -> {
            productRepository.delete(productDb);
        });
        return producOptional;
  
    }

    @Transactional
    @Override
    public Optional<Product> update(Long id, Product product) {
        Optional<Product> producOptional = productRepository.findById(id);
        if(producOptional.isPresent()){
            Product productDb = producOptional.orElseThrow();
            
            productDb.setName(product.getName());
            productDb.setDescription(product.getDescription());
            productDb.setPrice(product.getPrice());
            return Optional.of(productRepository.save(productDb));
        }
        return producOptional;
    }

    
    
}
