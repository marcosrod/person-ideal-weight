package com.marcosrod.person_ideal_weight_api.modulos.pessoa.dto;

import com.marcosrod.person_ideal_weight_api.modulos.pessoa.enums.Sexo;

import java.time.LocalDate;

public record PessoaResponse(Long id, String nome, LocalDate dataNascimento,
                             String cpf, Sexo sexo, Double altura, Double peso) {
}
