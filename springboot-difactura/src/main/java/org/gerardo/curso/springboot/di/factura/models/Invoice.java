package org.gerardo.curso.springboot.di.factura.models;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Invoice {

    @Autowired
    private Client client;

    @Value("${invoice.description.office}")
    private String description;

    @Autowired
    @Qualifier("itemsInvoiceOficina")
    private List<Item> items;

    // Getters y Setters
    public Client getClient() {
        return client;
    }
    public void setClient(Client client) {
        this.client = client;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public List<Item> getItems() {
        return items;
    }
    public void setItems(List<Item> items) {
        this.items = items;
    }

    //Metodos
    public int getTotal(){
        // int total = 0;
        // for (Item item : items) {
        //     total = total + item.getImporte();
        // }

        // segunda forma con api stream
        int total = items.stream()
        .map(item -> item.getImporte())
        .reduce(0, (sum,importe)-> sum + importe);
        return total;
    }

    
    
}
