package org.gerardo.curso.springboot.di.factura.models;

import org.springframework.stereotype.Component;

@Component
public class Client {

    private String name;
    private String lastname;
    
    // Constructor
    public Client(String name, String lastname) {
        this.name = name;
        this.lastname = lastname;
    }

    // Getters y Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

}
