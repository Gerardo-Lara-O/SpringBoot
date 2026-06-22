package com.gerardo.curso.webapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping({"","/","/home"})
    public String home(){

        // redirige
        // return "redirect:/web/details";
        
        // redirige un servlet a otro servlet (un dispatcher)
        return "forward:/web/details";
    }

}
