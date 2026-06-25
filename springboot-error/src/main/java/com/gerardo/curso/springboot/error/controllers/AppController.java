package com.gerardo.curso.springboot.error.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gerardo.curso.springboot.error.exceptions.UserNotFoundException;
import com.gerardo.curso.springboot.error.models.domain.User;
import com.gerardo.curso.springboot.error.services.UserService;


@RestController
@RequestMapping("/app")
public class AppController {

    private final UserService service;

    AppController(UserService service) {
        this.service = service;
    }

    @GetMapping
    public String index(){
        // int value = 100/0;
        int value = Integer.parseInt("10x");
        System.out.println(value);
        return "ok 200";
    }

    @GetMapping("/show/{id}")
    public ResponseEntity<?> show(@PathVariable Long id){
        // User user = service.findById(id).orElseThrow(() -> new UserNotFoundException("Error el usuario no existe!"));
        Optional<User> optionalUser = service.findById(id);
        if (optionalUser.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        // System.out.println(user.getLastName());
        return ResponseEntity.ok(optionalUser.get());
    }
}
