package com.gerardo.springboot.springmvc.app.controllers;

import com.gerardo.springboot.springmvc.app.entities.User;
import com.gerardo.springboot.springmvc.app.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
    @RequestMapping("/users")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping({"/view","/another"})
    public String view(Model model){
        model.addAttribute("title","Hola Mundo Spring Boot!!!");
        model.addAttribute("message","Esta es una aplicacion de ejemplo usando Spring!");
        model.addAttribute("user",new User("Gerardo","Lara"));
        return "view"; // aqui va el nombre de la plantilla, tiene que ser el mismo
    }

    @GetMapping("/")
    public String list(Model model){
        model.addAttribute("title","Listado de Usuarios");
        model.addAttribute("users",service.findAll());
        return "list";
    }

    @GetMapping("/form")
    public String form(Model model){
        model.addAttribute("title","Crear Usuario");
        model.addAttribute("user",new User());
        return "form";
    }

    // path variable
    @GetMapping("/form/{id}")
    public String form(@PathVariable Long id, Model model, RedirectAttributes redirect){
        Optional<User> optionalUser = service.findById(id);
        if (optionalUser.isPresent()){
            model.addAttribute("title","Editar Usuario");
            model.addAttribute("user",optionalUser.get());
            return "form";
        }else{
            redirect.addFlashAttribute("error","El usuario cin id " + id +
                    " no existe en la base de datos!");
            return "redirect:/users";
        }
    }

    @PostMapping
    public String form(User user, Model model,RedirectAttributes redirect){
        String message = "";
        if (user.getId() > 0){
            message = "El usuario " + user.getName() +
                    " se ha actualizado con exito";

        }else {
            message = "El usuario " + user.getName() +
                    " se ha creado con exito";
        }
        service.save(user);
        redirect.addFlashAttribute("success", message);
        return "redirect:/users";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes redirect){
        Optional<User> optionalUser = service.findById(id);
        if (optionalUser.isPresent()){
        redirect.addFlashAttribute("success","El usuario " + optionalUser.get().getName() +
                " se ha eliminado con exito");
            service.remove(id);
            return "redirect:/users";
        }
        redirect.addFlashAttribute("error","Error el usuario con el id " + id +
                " no existe en el sistema");
        return "redirect:/users";

    }
}
