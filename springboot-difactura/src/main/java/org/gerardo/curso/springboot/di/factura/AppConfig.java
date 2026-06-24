package org.gerardo.curso.springboot.di.factura;

import java.util.Arrays;
import java.util.List;

import org.gerardo.curso.springboot.di.factura.models.Item;
import org.gerardo.curso.springboot.di.factura.models.Product;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(value = "classpath:data.properties", encoding="UTF-8")
public class AppConfig {

    @Bean
    List<Item> itemsInvoice(){

        //Productos
        Product p1 = new Product("Camara Sony",800);
        Product p2 = new Product("Bicicleta Nianchi 26", 1200);
        
        //Item
        return Arrays.asList(new Item(p1,2), new Item(p2,4));
        
    }

    @Bean
    // @Primary
    List<Item> itemsInvoiceOficina(){

        //Productos
        Product p1 = new Product("Monitor asus",700);
        Product p2 = new Product("Notebook Razer", 2400);
        Product p3 = new Product("Impresora HP", 800);
        Product p4 = new Product("Escritorio Oficina", 900);
        
        //Item
        return Arrays.asList(new Item(p1,4), new Item(p2,6), new Item(p3,1), new Item(p4,4));
        
    }
    
}
