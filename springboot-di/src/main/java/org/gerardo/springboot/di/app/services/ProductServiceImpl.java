package org.gerardo.springboot.di.app.services;

import java.util.List;
import java.util.stream.Collectors;

import org.gerardo.springboot.di.app.models.Product;
import org.gerardo.springboot.di.app.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository repository;
    
    @Override
    public List<Product> findAll(){
        return repository.findAll().stream().map(p -> {
            Double priceImp = p.getPrice() * 1.25d;
            Product newProd = new Product(p.getId(),p.getName(),priceImp.longValue());
            return newProd;
        }).collect(Collectors.toList());
    }

        // con clone()
        public List<Product> findAll2(){
        return repository.findAll().stream().map(p -> {
            Double priceTax = p.getPrice() * 1.25d;
            // Product newProd = new Product(p.getId(),p.getName(),priceImp.longValue());
            Product newProd = (Product) p.clone();
            newProd.setPrice(priceTax.longValue());
            return newProd;
        }).collect(Collectors.toList());
    }

    @Override
    public Product findById(Long id){
        return repository.findById(id);
    }
}
