package com.gerardo.curso.webapp.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
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

    // valores
    @Value("${config.username}")
    private String username;

    // @Value("${config.message}")
    // private String message;

    @Value("${config.listOfValues}")
    private List<String> listOfValues;

    @Value("${config.code}")
    private Integer code;

    @Value("#{'${config.listOfValues}'.split(',')}")
    private List<String> valueList;

    @Value("#{'${config.listOfValues}'.toUpperCase()}")
    private String valueString;

    @Value("#{${config.valuesMap}}")
    private Map<String, Object> valuesMap;

    @Value("#{${config.valuesMap}.product}")
    private String product;

    @Autowired
    private Environment environment;

      // Handlers
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

    @GetMapping("/values")
    public Map<String,Object> values(@Value("${config.message}") String message){
        Map<String, Object> json = new HashMap<>();
        json.put("username", username);
        // json.put("code",code);
        // Usando environment
        json.put("message2", environment.getProperty("config.message"));
        // podemos hacer el cast de esta forma a un Integer
        // json.put("code2", Integer.valueOf(environment.getProperty("config.code")) );
        // O de esta forma para pasarlo a un Long
        json.put("code2", environment.getProperty("config.code",Long.class));

        json.put("listOfValues", listOfValues);
        json.put("valueList", valueList);
        json.put("valueString", valueString);
        json.put("valuesMap", valuesMap);
        json.put("product", product);
        return json;
    }
    
}
