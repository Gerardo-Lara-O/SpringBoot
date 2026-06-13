package com.gerardo.curso.webapp.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
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

    @GetMapping("/list")
    public List<User> list(){
        User user1 = new User("Gerardo","Lara");
        User user2 = new User("Andres","Garcia");
        User user3 = new User("Gaby","Ortiz");

        List<User> users = Arrays.asList(user1,user2,user3);

        // List<User> users = new ArrayList<>();
        // users.add(user1);
        // users.add(user2);
        // users.add(user3);

        return users;
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
