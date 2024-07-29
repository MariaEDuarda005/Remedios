package com.remedios.curso.controllers;

import com.remedios.curso.usuarios.AutenticacaoService;
import com.remedios.curso.usuarios.DadosAutenticacao;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/login")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager manager; // atributo que vai instanciar a classe AutenticacaoService

    // RequestBody -> pois o corpo da requisição vai ser lido
    @PostMapping
    public ResponseEntity<?> efetuarLogin(@RequestBody @Valid DadosAutenticacao dados){
        var token = new UsernamePasswordAuthenticationToken(dados.login(), dados.senha());
        System.out.print(token);
        var autenticacao = manager.authenticate(token); // verifica no banco de dados
        System.out.print(autenticacao);
        return ResponseEntity.ok().build(); // fechando o controle de autenticação
    }
}
