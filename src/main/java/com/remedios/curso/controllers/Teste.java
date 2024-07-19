package com.remedios.curso.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello") // o controller vai ser acessado por esse endereço na url
public class Teste {

    @GetMapping
    public String hello(){
        return "Hello World";
    }
}
