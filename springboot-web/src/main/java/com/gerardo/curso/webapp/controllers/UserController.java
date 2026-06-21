package com.gerardo.curso.webapp.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gerardo.curso.webapp.models.User;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/web")
public class UserController {

    // Usando Model
    @GetMapping("/details")
    public String details(Model model){
        User user = new User();
        user.setName("Gerardo");
        user.setLastname("Lara");
        user.setEmail("gerardo@email.com");
        model.addAttribute("title", "Hola mundo Spring");
        model.addAttribute("user",user);

        return "details";
    }

    @GetMapping("/list")
    public String listString(Model model) {
        List<User> users = Arrays.asList(
            new User("Pepa","Gonzales","pepa@email.com"), 
            new User("Lalo", "Perez","lalo@email.com"),
            new User("Juanita","Roe","juana@email.com"),
            new User("Andres","Doe")
        );

        model.addAttribute("title","Listado de usuarios");
        model.addAttribute("users",users);

        return "list";
    }
    
}
