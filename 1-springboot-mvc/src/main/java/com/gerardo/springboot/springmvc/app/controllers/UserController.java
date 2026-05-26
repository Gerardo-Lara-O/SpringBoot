package com.gerardo.springboot.springmvc.app.controllers;

import com.gerardo.springboot.springmvc.app.entities.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {

    @GetMapping({"/view","/","/another"})
//    @RequestMapping("/app")
    public String view(Model model){
        model.addAttribute("title","Hola Mundo Spring Boot!!!");
        model.addAttribute("message","Esta es una aplicacion de ejemplo usando Spring!");
        model.addAttribute("user",new User("Gerardo","Lara"));
        return "view"; // aqui va el nombre de la plantilla, tiene que ser el mismo
    }
}
