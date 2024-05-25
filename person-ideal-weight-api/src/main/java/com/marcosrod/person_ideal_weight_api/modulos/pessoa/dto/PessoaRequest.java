package com.marcosrod.person_ideal_weight_api.modulos.pessoa.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.marcosrod.person_ideal_weight_api.modulos.pessoa.enums.Sexo;

import java.time.LocalDate;

public record PessoaRequest(String nome, @JsonFormat(pattern = "yyyy-MM-dd") LocalDate dataNascimento,
							String cpf, Sexo sexo, Double altura, Double peso) {

}
