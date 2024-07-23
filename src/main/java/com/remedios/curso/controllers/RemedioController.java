package com.remedios.curso.controllers;

import com.remedios.curso.remedio.*;
import com.remedios.curso.repositories.RemedioRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/api/v1/remedios")
public class RemedioController {

    @Autowired
    private RemedioRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroRemedio dados, UriComponentsBuilder uriBuilder){
        var remedio = new Remedio(dados); // criou uma variavel, que recebeu um novo remedio com parametro dos dados do DTO, deixando salvo na requisição
        repository.save(remedio); // salvo no banco de dados
        System.out.println(dados);

        // utilizando uma classe já do spring UriComponents dentro da uri
        // dentro da variavel chamamos o parametro, depois dar um ponto path que busca o caminho da url e complementar pegando o id do novo remedio criado
        // depois de tudo isso vai estar tranformando em uma url com o toUri
        var uri = uriBuilder.path("/api/v1/remedios/{id}").buildAndExpand(remedio.getId()).toUri();

        // retorna um responseEntity com o metodo create e como parametro vai ter a uri e dentro do body o detalhamento do registro que vai pegar como
        // parametro a variavel que criamos
        return ResponseEntity.created(uri).body(new DadosDetalhamentoRemedio(remedio));
    }

    @GetMapping
    public ResponseEntity<List<DadosListagemRemedios>> listar(){
        var lista = repository.findAllByAtivoTrue()
                .stream()
                .map(DadosListagemRemedios::new).toList();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<DadosListagemRemedios>> remedioId(@PathVariable Long id){
        var remediosId = repository.findById(id)
                .stream()
                .map(DadosListagemRemedios::new)
                .toList();
        return ResponseEntity.ok(remediosId);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoRemedio> atualizar(@RequestBody @Valid DadosAtualizarRemedio dados){
        var remedio = repository.getReferenceById(dados.id());
        remedio.atualizarInformacoes(dados);

        return ResponseEntity.ok(new DadosDetalhamentoRemedio(remedio));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> excluir(@PathVariable Long id){
        repository.deleteById(id);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/inativar/{id}")
    @Transactional
    public ResponseEntity<Void> inativar(@PathVariable Long id){
        var remedio = repository.getReferenceById(id);
        remedio.inativar();

        return ResponseEntity.noContent().build(); // retornar 204
    }

    @PutMapping("/reativar/{id}")
    @Transactional
    public ResponseEntity<Void> Reativar(@PathVariable Long id){
        var remedio = repository.getReferenceById(id);
        remedio.reativar();

        return ResponseEntity.noContent().build();
    }

}
