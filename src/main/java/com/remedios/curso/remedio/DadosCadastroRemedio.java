package com.remedios.curso.remedio;

import com.remedios.curso.remedio.enums.Laboratorio;
import com.remedios.curso.remedio.enums.Via;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

// DTO
public record DadosCadastroRemedio(

        @NotBlank // para evitar que seja escrito de forma errada
        String nome,
        @Enumerated
        Via via,
        @NotBlank
        String lote,
        int quantidade,
        @Future // tem que ser uma validade que vai ser aceita no futuro
        LocalDate validade,
        @Enumerated
        Laboratorio laboratorio) {
}
