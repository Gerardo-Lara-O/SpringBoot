package com.gerardo.curso.webapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/web")
public class UserController {

    // Usando Model
    @GetMapping("/details")
    public String details(Model model){
        model.addAttribute("title", "Hola mundo Spring");
        model.addAttribute("name","Gerardo");
        model.addAttribute("lastname","Lara");
        return "details";
    }
}
