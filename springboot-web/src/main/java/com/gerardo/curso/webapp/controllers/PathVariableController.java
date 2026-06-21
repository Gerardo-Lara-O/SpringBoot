package com.gerardo.curso.webapp.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gerardo.curso.webapp.models.User;
import com.gerardo.curso.webapp.models.dto.ParamDtoMix;

@RestController
@RequestMapping("/api/var")
public class PathVariableController {

    @GetMapping("/baz/{message}")
    public ParamDtoMix baz(@PathVariable String message){
        ParamDtoMix param = new ParamDtoMix();
        param.setMessage(message);
        return param;
    }

    @GetMapping("/mix/{product}/{id}")
    public Map<String, Object> mixPathVar(@PathVariable String product, @PathVariable Long id){
        Map<String,Object> json = new HashMap<>();
        json.put("product", product);
        json.put("id",id);

        return json;
    }

    @PostMapping("/create")
    public User create(@RequestBody User user){

        return user;
    }
    
}
