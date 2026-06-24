package org.gerardo.springboot.di.app.repositories;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.gerardo.springboot.di.app.models.Product;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import tools.jackson.databind.ObjectMapper;

public class ProductRepositoryJson implements ProductRepository{

    private List<Product> list;

    // No funcionaria por que no es un componente
    // @Value("classpath:json/product.json")
    // private Resource resource;

    public ProductRepositoryJson(Resource resource) {
        readValueJson(resource);
    }

    public ProductRepositoryJson() {
    // 1. Buscamos el archivo en la carpeta src/main/resources/json
        Resource resource = new ClassPathResource("json/product.json");
        readValueJson(resource);
    }

    private void readValueJson(Resource resource) {
        ObjectMapper objectMapper = new ObjectMapper();
        
        try {
            // 3. Usamos getInputStream() en lugar de getFile() para que no explote en el JAR
            Product[] productArray = objectMapper.readValue(resource.getInputStream(), Product[].class);
            this.list = Arrays.asList(productArray);
            
        } catch (IOException e) {
            e.printStackTrace();
            
        }
    }

    @Override
    public List<Product> findAll() {
        return list;
    }

    @Override
    public Product findById(Long id) {
        return list.stream().filter(p -> {
            return p.getId().equals(id);
        }).findFirst().orElseThrow();
    }
    
}
