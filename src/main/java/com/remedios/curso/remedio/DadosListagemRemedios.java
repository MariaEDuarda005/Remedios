package com.remedios.curso.remedio;

import com.remedios.curso.remedio.enums.Laboratorio;
import com.remedios.curso.remedio.enums.Via;

import java.time.LocalDate;

public record DadosListagemRemedios(Long id, String nome, Via via, String lote, Laboratorio laboratorio, LocalDate validade) {

    public DadosListagemRemedios(Remedio remedio){
        this(remedio.getId(), remedio.getNome(), remedio.getVia(), remedio.getLote(), remedio.getLaboratorio(), remedio.getValidade());
    }
}
