package com.remedios.curso.remedio;

import com.remedios.curso.remedio.enums.Laboratorio;
import com.remedios.curso.remedio.enums.Via;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity(name = "remedios")
@Table(name = "remedio")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Remedio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    @Enumerated(EnumType.STRING)
    private Via via;
    private String lote;
    private int quantidade;
    private LocalDate validade;
    @Enumerated(EnumType.STRING)
    private Laboratorio laboratorio;

    // criar um atributo responsavel por dizer que determinado item esta ativo ou inativo
    private Boolean ativo;

    public Remedio(DadosCadastroRemedio dados) {
      // construtor da nossa entidade remedio
        this.nome = dados.nome();
        this.via = dados.via();
        this.lote = dados.lote();
        this.quantidade = dados.quantidade();
        this.validade = dados.validade();
        this.laboratorio = dados.laboratorio();
        this.ativo = true; // toda a vez que criar um remedio ele vai ser automaticamente true
    }

    public void atualizarInformacoes(@Valid DadosAtualizarRemedio dados){
        if (dados.nome() != null) {
            this.nome = dados.nome(); // se não for nulo, pode salvar
        }

        if (dados.via() != null){
            this.via = dados.via();
        }

        if (dados.lote() != null){
            this.lote = dados.lote();
        }

        if (dados.laboratorio() != null){
            this.laboratorio = dados.laboratorio();
        }
    }
}

