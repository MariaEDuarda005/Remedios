package com.remedios.curso.remedio;

import com.remedios.curso.remedio.enums.Laboratorio;
import com.remedios.curso.remedio.enums.Via;
import jakarta.validation.constraints.NotNull;

// somente esses dados podem ser atualizados
public record DadosAtualizarRemedio(
        @NotNull
        Long id,
        String nome,
        Via via,
        String lote,
        Laboratorio laboratorio) {
}
