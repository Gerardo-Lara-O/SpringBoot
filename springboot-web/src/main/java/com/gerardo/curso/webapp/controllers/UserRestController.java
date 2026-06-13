package com.gerardo.curso.webapp.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gerardo.curso.webapp.models.User;
import com.gerardo.curso.webapp.models.dto.UserDto;

@RestController
@RequestMapping("/api")
public class UserRestController {

    // Usando Model
    @GetMapping("/details")
    // Tambien podemos usar
    // @RequestMapping(path = "/details", method = RequestMethod.GET)
    public UserDto details(){
        User user = new User("Gerardo","Lara");
        UserDto userDto = new UserDto("Hola mundo",user);

        return userDto;
    }

      // Usando Model
    @GetMapping("/details-map")
    // Tambien podemos usar
    // @RequestMapping(path = "/details", method = RequestMethod.GET)
    public Map<String, Object> detailsMap(){
        Map<String, Object> body = new HashMap<>();
        User user = new User("Gerardo","Lara");

        body.put("title", "Hola mundo Spring con Map");
        body.put("user",user);
        return body;
    }
}
