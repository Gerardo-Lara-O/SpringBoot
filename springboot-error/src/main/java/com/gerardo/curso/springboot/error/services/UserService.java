package com.gerardo.curso.springboot.error.services;

import java.util.List;

import com.gerardo.curso.springboot.error.models.domain.User;

public interface UserService {
    
    List<User> findAll();
    User findById(Long id);
}
