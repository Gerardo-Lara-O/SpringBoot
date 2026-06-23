package org.gerardo.springboot.di.app;

import org.gerardo.springboot.di.app.repositories.ProductRepository;
import org.gerardo.springboot.di.app.repositories.ProductRepositoryJson;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:config.properties")
public class AppConfig {

    @Bean("productJson")
    ProductRepository productRepositoryJson(){
        return new ProductRepositoryJson();
    }
    
}
