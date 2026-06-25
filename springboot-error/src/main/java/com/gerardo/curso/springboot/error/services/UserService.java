package com.gerardo.curso.springboot.error.services;

import java.util.List;
import java.util.Optional;

import com.gerardo.curso.springboot.error.models.domain.User;

public interface UserService {
    
    List<User> findAll();
    Optional<User> findById(Long id);
}
