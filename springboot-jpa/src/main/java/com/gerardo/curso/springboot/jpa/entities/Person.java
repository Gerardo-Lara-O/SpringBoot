package com.gerardo.curso.springboot.jpa.entities;


import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

@Entity
@Table(name = "persons")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String lastname;

    @Column(name = "programming_language")
    private String programmingLanguage;

    @Embedded
    private Audit audit = new Audit();



    
    // Constructors
    public Person(){}

    public Person(String name, String lastname){
        this.name = name;
        this.lastname = lastname;
    }

    public Person(Long id, String name, String lastname, String programmingLanguage) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.programmingLanguage = programmingLanguage;
    }

    // 1. Aquí SÍ es legal poner la anotación porque Person es un @Entity
    @PrePersist
    public void prePersist() {
        // Le delegamos el trabajo a nuestro "estuche" de auditoría
        this.audit.prePersist();
    }

    @PreUpdate
    public void preUpdate() {
        // Le delegamos el trabajo a nuestro "estuche" de auditoría
        this.audit.preUpdate();
    }




    // Getters y Setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getLastName() {
        return lastname;
    }
    public void setLastName(String lastname) {
        this.lastname = lastname;
    }
    public String getProgrammingLanguage() {
        return programmingLanguage;
    }
    public void setProgrammingLanguage(String programmingLanguage) {
        this.programmingLanguage = programmingLanguage;
    }

    // toString
    @Override
    public String toString() {
        return "[id=" + id + ", name=" + name + ", lastname=" + lastname + ", programmingLanguage="
                + programmingLanguage + ". createAt=" + audit.getCreatAt() + ", updatedAt=" + audit.getUpdatedAt() + "]";
    }

    

    
}
