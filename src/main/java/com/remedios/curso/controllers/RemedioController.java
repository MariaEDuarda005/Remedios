package com.remedios.curso.controllers;

import com.remedios.curso.remedio.DadosAtualizarRemedio;
import com.remedios.curso.remedio.DadosCadastroRemedio;
import com.remedios.curso.remedio.DadosListagemRemedios;
import com.remedios.curso.remedio.Remedio;
import com.remedios.curso.repositories.RemedioRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/remedios")
public class RemedioController {

    @Autowired
    private RemedioRepository repository;

    @PostMapping
    @Transactional
    // @RequestBody -> para ele saber de onde esta vindo o parametro
    public void cadastrar(@RequestBody @Valid DadosCadastroRemedio dados){
        // esse repository vai estar usando o metodo save que esta criando um novo remedio com os dados
        repository.save(new Remedio(dados));
        System.out.println(dados);
    }

    @GetMapping
    // não podemos trabalhar diretamente com as entidades, tudo que entra e sai é DTO
    public List<DadosListagemRemedios> listar(){
        // DadosListagemRemedios::new -> essa sintaxe serve para chamar o construtor
        return repository.findAll()
                .stream()
                .map(DadosListagemRemedios::new).toList();
    }

    @GetMapping("/{id}")
    public List<DadosListagemRemedios> listarId(@PathVariable Long id){
        return repository.findById(id)
                .stream()
                .map(DadosListagemRemedios::new)
                .toList();
    }

    @PutMapping
    @Transactional // se acontecer algum problema durante a requisição vai restaurar os dados, se der certo salva automaticamente
    public void atualizar(@RequestBody @Valid DadosAtualizarRemedio dados){
        // getReferenceById -> vai pegar por referencia um objeto pelo id
        var remedio = repository.getReferenceById(dados.id()); // pegando o objeto no banco de dados
        // atualizar as informações
        remedio.atualizarInformacoes(dados);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void excluir(@PathVariable Long id){
        repository.deleteById(id);
    }

}
