package com.remedios.curso.infra;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;


// o RestControllerAdvice responsavel por sempre que acontecer alguma exception essa classe vai ser buscada
@RestControllerAdvice
public class TratadorDeErros {

    // tratando quando voce vai fazer um get por id, e adiciona um que não existe
    @ExceptionHandler(EntityNotFoundException.class)
    // <?> é como um coringa, qualquer tipo vai funcionar
    public ResponseEntity<?> tratador404(){
        return ResponseEntity.notFound().build();
    }

    // qualquer lugar da aplicação que acontecer algum erro de validação
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> tratador400(MethodArgumentNotValidException ex){
        var erros = ex.getFieldError();
        return ResponseEntity.badRequest().body(erros.stream().map(DadosErros::new).toList);
    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<?> tratador404Post(){

        return ResponseEntity.badRequest().build();
    }

    public record DadosErros(String message, String field){
        // criando um dto dentro da classe
    }
}
