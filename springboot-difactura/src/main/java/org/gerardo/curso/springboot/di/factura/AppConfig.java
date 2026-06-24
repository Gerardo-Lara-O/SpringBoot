package org.gerardo.curso.springboot.di.factura;

import java.util.Arrays;
import java.util.List;

import org.gerardo.curso.springboot.di.factura.models.Item;
import org.gerardo.curso.springboot.di.factura.models.Product;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:data.properties")
public class AppConfig {

    @Bean
    List<Item> itemsInvoice(){

        //Productos
        Product p1 = new Product("Camara Sony",800);
        Product p2 = new Product("Bicicleta Nianchi 26", 1200);
        
        //Item
        return Arrays.asList(new Item(p1,2), new Item(p2,4));
        
    }
    
}
