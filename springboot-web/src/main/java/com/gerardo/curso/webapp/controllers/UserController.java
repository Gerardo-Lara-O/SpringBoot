package com.gerardo.curso.webapp.controllers;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gerardo.curso.webapp.models.User;



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
        model.addAttribute("title","Listado de usuarios");

        return "list";
    }

        // Ahora users ya no lo cargo en el model
    @ModelAttribute("users")
    public List<User> usersModel(){
        return Arrays.asList(
            new User("Pepa","Gonzales","pepa@email.com"), 
            new User("Lalo", "Perez","lalo@email.com"),
            new User("Juanita","Roe","juana@email.com"),
            new User("Andres","Doe")
        );
    }
    
}
