package com.remedios.curso.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/remedios")
public class RemedioController {

    @PostMapping
    // @RequestBody -> para ele saber de onde esta vindo o parametro
    public void cadastrar(@RequestBody String json){
        System.out.println(json);
    }

}
