package com.gerardo.curso.webapp.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserRestController {

    // Usando Model
    @GetMapping("/details2")
    public Map<String, Object> details(){
        Map<String, Object> body = new HashMap<>();
        body.put("title", "Hola mundo Spring");
        body.put("name","Gerardo");
        body.put("lastname","Lara");
        return body;
    }
}
