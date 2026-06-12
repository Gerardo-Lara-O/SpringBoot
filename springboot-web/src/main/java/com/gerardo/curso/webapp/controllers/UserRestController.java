package com.gerardo.curso.webapp.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gerardo.curso.webapp.models.User;

@RestController
@RequestMapping("/api")
public class UserRestController {

    // Usando Model
    @GetMapping("/details")
    // Tambien podemos usar
    // @RequestMapping(path = "/details", method = RequestMethod.GET)
    public Map<String, Object> details(){
        Map<String, Object> body = new HashMap<>();
        User user = new User("Gerardo","Lara");

        body.put("title", "Hola mundo Spring");
        body.put("user",user);
        return body;
    }
}
