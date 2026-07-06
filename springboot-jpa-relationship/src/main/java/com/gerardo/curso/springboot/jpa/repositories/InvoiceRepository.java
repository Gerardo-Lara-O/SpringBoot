package com.gerardo.curso.springboot.jpa.repositories;

import org.springframework.data.repository.CrudRepository;

import com.gerardo.curso.springboot.jpa.models.Invoice;

public interface InvoiceRepository extends CrudRepository<Invoice,Long>{
    
}
