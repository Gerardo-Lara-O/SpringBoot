package com.gerardo.curso.springboot.app.Validation;

import org.springframework.beans.factory.annotation.Autowired;

import com.gerardo.curso.springboot.app.services.ProductService;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class IsExistValidation implements ConstraintValidator<IsExistDb,String>{

    @Autowired
    private ProductService service;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return !service.existsBySku(value);
    }
    
}
