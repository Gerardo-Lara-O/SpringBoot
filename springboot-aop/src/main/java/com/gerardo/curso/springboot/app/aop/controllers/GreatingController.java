package com.gerardo.curso.springboot.app.aop.controllers;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gerardo.curso.springboot.app.aop.services.GreetingService;

@RestController
public class GreatingController {

    @Autowired
    private GreetingService greeting;
    
    @GetMapping("/greeting")
    public ResponseEntity<?> greeting(){

        return ResponseEntity.ok(Collections.singletonMap("greedting", greeting.sayHello("Pepe", "Hola que tal!")));
    }

    @GetMapping("/greetingError")
    public ResponseEntity<?> greetingError(){

        return ResponseEntity.ok(Collections.singletonMap("greedting", greeting.sayHelloError("Pepe", "Hola que tal!")));
    }
}
