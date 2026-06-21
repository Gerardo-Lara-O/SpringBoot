package com.gerardo.curso.webapp.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gerardo.curso.webapp.models.dto.ParamDto;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/params")
public class RequestParamsController {

    @GetMapping("/foo")
    public ParamDto foo(@RequestParam(required = false, defaultValue = "Hola que tal") String message) {
        ParamDto param = new ParamDto();
        param.setMessage(message);
        return param;
    }

    @GetMapping("/bar")
    public ParamDto bar(@RequestParam String text, @RequestParam Integer code){

        ParamDto params = new ParamDto();
        params.setMessage(text);
        params.setCode(code);
        return params;
    }

    @GetMapping("/request") 
    public ParamDto request(HttpServletRequest request){
        Integer code = 10;
        try {
            code = Integer.parseInt(request.getParameter("code"));
        } catch (NumberFormatException e) {
            // TODO: handle exception
            e.getMessage();
        }
        ParamDto params = new ParamDto();
        params.setCode(code);
        params.setMessage(request.getParameter("message"));
        return params;
    }

}
