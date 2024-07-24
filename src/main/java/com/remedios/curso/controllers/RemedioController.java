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
        var remedio = new Remedio(dados);
        repository.save(remedio);
        System.out.println(dados);

        var uri = uriBuilder.path("/api/v1/remedios/{id}").buildAndExpand(remedio.getId()).toUri();

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
    public ResponseEntity<DadosDetalhamentoRemedio> detalharRemedio(@PathVariable Long id){
        var remediosId = repository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoRemedio(remediosId));
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